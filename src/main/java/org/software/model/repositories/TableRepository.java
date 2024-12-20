package org.software.model.repositories;

import org.software.model.factories.TableFactory;
import org.software.model.table.Table;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableRepository implements ITableRepository {
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Tables";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM Tables WHERE table_id = ?";
    private static final String INSERT_QUERY = "INSERT INTO Tables (table_number, capacity, table_type, status) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE Tables SET table_number = ?, capacity = ?, table_type = ?, status = ? WHERE table_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM Tables WHERE table_id = ?";

    private Connection connection;

    public TableRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Table> getAll() {
        List<Table> tables = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY)) {

            while (resultSet.next()) {
                tables.add(mapResultSetToTable(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    @Override
    public Table getById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToTable(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Table create(Table table) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setTablePreparedStatementParams(statement, table);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return TableFactory.createTable(
                            table.getType(),
                            generatedKeys.getInt(1),
                            table.getTableNumber(),
                            table.getCapacity(),
                            table.getStatus()
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

    @Override
    public Table update(Table table) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            setTablePreparedStatementParams(statement, table);
            statement.setInt(5, table.getId());  // table_id for updating
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

    @Override
    public Table delete(Table table) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setInt(1, table.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return table;
    }

    private void setTablePreparedStatementParams(PreparedStatement statement, Table table) throws SQLException {
        statement.setInt(1, table.getTableNumber());
        statement.setInt(2, table.getCapacity());
        statement.setString(3, table.getType());
        statement.setString(4, table.getStatus());
    }

    private Table mapResultSetToTable(ResultSet resultSet) throws SQLException {
        return TableFactory.createTable(
            resultSet.getString("table_type"),
            resultSet.getInt("table_id"),
            resultSet.getInt("table_number"),
            resultSet.getInt("capacity"),
            resultSet.getString("status")
        );
    }
}
