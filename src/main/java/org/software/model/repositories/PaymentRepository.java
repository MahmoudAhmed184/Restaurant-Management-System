package org.software.model.repositories;

import org.software.model.DatabaseManager;
import org.software.model.PaymentBill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements IPaymentRepository {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM Payments";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Payments WHERE payment_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO Payments (order_id, payment_method, total_price, status, payment_date) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Payments SET order_id = ?, payment_method = ?, total_price = ?, status = ?, payment_date = ? WHERE payment_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Payments WHERE payment_id = ?";

    private Connection connection;

    public PaymentRepository() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    @Override
    public List<PaymentBill> getAll() {
        List<PaymentBill> payments = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {

            while (resultSet.next()) {
                payments.add(mapResultSetToPayment(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public PaymentBill getById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToPayment(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PaymentBill create(PaymentBill paymentBill) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setPaymentPreparedStatementParams(statement, paymentBill);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return new PaymentBill(generatedKeys.getInt(1), paymentBill.getOrderId(), paymentBill.getPaymentMethod(), paymentBill.getTotalPrice(), paymentBill.getStatus(), paymentBill.getPaymentDate());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentBill;
    }

    @Override
    public PaymentBill update(PaymentBill paymentBill) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            setPaymentPreparedStatementParams(statement, paymentBill);
            statement.setInt(6, paymentBill.getPaymentId());  // payment_id for updating
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentBill;
    }

    @Override
    public PaymentBill delete(PaymentBill paymentBill) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, paymentBill.getPaymentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymentBill;
    }

    private void setPaymentPreparedStatementParams(PreparedStatement statement, PaymentBill paymentBill) throws SQLException {
        statement.setInt(1, paymentBill.getOrderId());
        statement.setString(2, paymentBill.getPaymentMethod());
        statement.setBigDecimal(3, paymentBill.getTotalPrice());
        statement.setString(4, paymentBill.getStatus());
        statement.setString(5, paymentBill.getPaymentDate());
    }

    private PaymentBill mapResultSetToPayment(ResultSet resultSet) throws SQLException {
        return new PaymentBill(
                resultSet.getInt("payment_id"),
                resultSet.getInt("order_id"),
                resultSet.getString("payment_method"),
                resultSet.getBigDecimal("total_price"),
                resultSet.getString("status"),
                resultSet.getString("payment_date")
        );
    }
}
