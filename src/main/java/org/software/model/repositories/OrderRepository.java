package org.software.model.repositories;

import org.software.model.DatabaseManager;
import org.software.model.order.Order;
import org.software.model.menu.MenuItem;
import org.software.model.order.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Orders";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Orders WHERE order_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO Orders (type, table_id) VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Orders SET type = ?, table_id = ? WHERE order_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Orders WHERE order_id = ?";
    private static final String SELECT_ORDER_ITEMS_BY_ORDER_ID_QUERY = """
        SELECT MenuItems.*, OrderDetails.quantity 
        FROM Orders JOIN OrderDetails ON Orders.order_id = OrderDetails.order_id 
        inner join MenuItems 
        ON OrderDetails.item_id = MenuItems.item_id 
        WHERE OrderDetails.order_id = ?""";
    public static final String INSERT_ITEM_INTO_ORDER_DETAILS_QUERY = "INSERT INTO OrderDetails (order_id, item_id, quantity) VALUES (?, ?, ?)";

    private final Connection connection;

    public OrderRepository() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {

            while (resultSet.next()) {
                orders.add(mapResultSetToOrder(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order create(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setOrderPreparedStatementParams(statement, order);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order = new Order(
                            generatedKeys.getInt(1),
                            order.type(),
                            order.tableNumber(),
                            order.items()
                        );
                        createOrderItems(order);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Order update(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            setOrderPreparedStatementParams(statement, order);
            statement.setInt(3, order.orderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Order delete(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, order.orderId());
            statement.executeUpdate();
            // @todo delete the order items
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    private void setOrderPreparedStatementParams(PreparedStatement statement, Order order) throws SQLException {
        statement.setString(1, order.type());
        statement.setInt(2, order.tableNumber());
    }

    private Order mapResultSetToOrder(ResultSet resultSet) throws SQLException {
        return new Order(
            resultSet.getInt("order_id"),
            resultSet.getString("type"),
            resultSet.getInt("table_id"),
            getOrderItems(resultSet.getInt("order_id"))
        );
    }

    private List<OrderItem> getOrderItems(int orderId) throws SQLException {
        List<OrderItem> orderItems = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_ITEMS_BY_ORDER_ID_QUERY)) {
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderItems.add(mapResultSetToOrderItem(resultSet));
            }
        }
        return orderItems;
    }

    private OrderItem mapResultSetToOrderItem(ResultSet resultSet) throws SQLException {
        return new OrderItem(
            mapResultSetToMenuItem(resultSet),
            resultSet.getInt("quantity")
        );
    }

    private MenuItem mapResultSetToMenuItem(ResultSet resultSet) throws SQLException {
        return new MenuItem(
            resultSet.getInt("item_id"),
            resultSet.getString("name"),
            resultSet.getBigDecimal("price"),
            resultSet.getString("category")
        );
    }

    private void createOrderItems(Order order) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ITEM_INTO_ORDER_DETAILS_QUERY)) {
            for (OrderItem orderItem : order.items()) {
                statement.setInt(1, order.orderId());
                statement.setInt(2, orderItem.getItem().id());
                statement.setInt(3, orderItem.getQuantity());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}