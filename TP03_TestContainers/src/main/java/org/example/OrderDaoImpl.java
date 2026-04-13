package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {

    private final Connection connection;

    // We inject a real SQL connection here
    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveOrder(Order order) {
        String sql = "INSERT INTO orders (id, amount) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, order.getOrderId());
            ps.setDouble(2, order.getAmount());
            ps.executeUpdate(); // Executes the real insert
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save order to database", e);
        }
    }
}