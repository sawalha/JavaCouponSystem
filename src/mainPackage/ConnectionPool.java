package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;


public class ConnectionPool {

    public static final int CONNECTIONS_COUNT = 5;

    private static ConnectionPool instance = null;
    private HashSet<Connection> connections = new HashSet<Connection>();

    public static synchronized ConnectionPool getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new ConnectionPool();
        }

        return instance;
    }


    private ConnectionPool() throws SQLException, ClassNotFoundException {

        for (int i = 0; i < CONNECTIONS_COUNT; ++i) {
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/CouponSystemDB");
            connections.add(connection);

        }
    }


    public Connection getConnection() {
        Connection connection = null;

        while (connection == null) {
            if (connections.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {

                }
            }

            synchronized (this) {
                if (connections.size() > 0) {
                    connection = connections.iterator().next();
                    connections.remove(connection);
                }
            }
        }

        return connection;


    }

    public synchronized void returnConnection(Connection connection) {
        connections.add(connection);
        notify();
    }

    public synchronized void closeAllConnections() throws SQLException {
        boolean full = false;
        do {
            if (connections.size() == 5) {
                full = true;
            }
        } while (full == false);

        for (Connection connection : connections) {
            connection.close();
            connections.remove(connection);
        }
    }
}