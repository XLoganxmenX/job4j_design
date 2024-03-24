package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver_class"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password")
        );
    }

    public void createTable(String tableName) throws SQLException {
        String statement = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        connection.createStatement().execute(statement);
    }

    public void dropTable(String tableName) throws SQLException {
        String statement = String.format("DROP TABLE IF EXISTS %s;", tableName);
        connection.createStatement().execute(statement);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String statement = String.format(
                "ALTER TABLE IF EXISTS %s ADD %s %s;",
                tableName,
                columnName,
                type
        );
        connection.createStatement().execute(statement);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String statement = String.format(
                "ALTER TABLE IF EXISTS %s DROP COLUMN %s;",
                tableName,
                columnName
        );
        connection.createStatement().execute(statement);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String statement = String.format(
                "ALTER TABLE IF EXISTS %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        );
        connection.createStatement().execute(statement);
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            ResultSet existTable = statement.executeQuery(
                    String.format("SELECT * FROM pg_tables WHERE tablename = '%s';", tableName)
            );

            if (existTable.next()) {
                var selection = statement.executeQuery(String.format(
                        "SELECT * FROM %s LIMIT 1", tableName
                ));
                var metaData = selection.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    buffer.add(String.format("%-15s|%-15s%n",
                            metaData.getColumnName(i), metaData.getColumnTypeName(i))
                    );
                }
            } else {
                buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
                buffer.add(String.format("Таблицы не существует%n"));
            }

        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }

        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.dropTable("test_table");

            tableEditor.createTable("test_table");
            System.out.println(tableEditor.getTableScheme("test_table"));

            tableEditor.addColumn("test_table", "name", "VARCHAR(255)");
            System.out.println(tableEditor.getTableScheme("test_table"));

            tableEditor.renameColumn("test_table", "name", "newName");
            System.out.println(tableEditor.getTableScheme("test_table"));

            tableEditor.dropColumn("test_table", "newName");
            System.out.println(tableEditor.getTableScheme("test_table"));

            tableEditor.dropTable("test_table");
            System.out.println(tableEditor.getTableScheme("test_table"));


        }
    }
}