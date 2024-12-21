package org.software.model.repositories;

import org.software.model.DatabaseManager;
import org.software.model.factories.MenuItemFactory;
import org.software.model.menu.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepository implements IMenuItemRepository {
    private Connection connection;

    public MenuItemRepository() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    @Override
    public List<MenuItem> getAll() {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM MenuItems";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                MenuItem menuItem = MenuItemFactory.getMenuItem(resultSet.getString("category"), resultSet.getInt("item_id"), resultSet.getString("name"), resultSet.getBigDecimal("price"));
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItems;
    }

    @Override
    public MenuItem getById(Integer id) {
        MenuItem menuItem = null;
        String query = "SELECT * FROM MenuItems WHERE item_id = ?"; // Table name 'MenuItems'

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                menuItem = MenuItemFactory.getMenuItem(
                    resultSet.getString("category"),
                    resultSet.getInt("item_id"),
                    resultSet.getString("name"),
                    resultSet.getBigDecimal("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItem;
    }

    @Override
    public MenuItem create(MenuItem menuItem) {
        String query = "INSERT INTO MenuItems (name, category, price) VALUES (?, ?, ?)";  // Table name 'MenuItems' and columns 'name', 'category', 'price'

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, menuItem.name());
            statement.setString(2, menuItem.category());
            statement.setBigDecimal(3, menuItem.price());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return MenuItemFactory.getMenuItem(
                        menuItem.category(),
                        generatedKeys.getInt(1),
                        menuItem.name(),
                        menuItem.price()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItem;
    }

    @Override
    public MenuItem update(MenuItem menuItem) {
        String query = "UPDATE MenuItems SET name = ?, category = ?, price = ? WHERE item_id = ?";  // Table name 'MenuItems' and columns 'name', 'category', 'price'

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, menuItem.name());      // Bind 'name'
            statement.setString(2, menuItem.category());  // Bind 'category'
            statement.setBigDecimal(3, menuItem.price());     // Bind 'price'
            statement.setInt(4, menuItem.id());           // Bind 'item_id' for the update condition

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItem;
    }

    @Override
    public MenuItem delete(MenuItem menuItem) {

        String query = "DELETE FROM MenuItems WHERE item_id = ?";  // Table name 'MenuItems' and column 'item_id'

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, menuItem.id());  // Bind 'item_id' for the delete condition
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return menuItem;
    }
}
