package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import ru.job4j.io.Config;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        Config config = new Config("./data/app.properties");
        config.load();
        String url = config.value("url");
        String login = config.value("login");
        String password = config.value("password");
        connection =  DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "create table if not exists " + tableName + " (%s, %s);",
                "id serial primary key",
                "name varchar(255)"
        );
        doTable(sql, tableName);
    }

    private void doTable(String command, String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(command);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "drop table if exists %s;",
                tableName
        );
        doTable(sql, tableName);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "alter table if exists " + tableName + "%s;",
                " add column " + columnName + " " + type
        );
        doTable(sql, tableName);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "alter table if exists " + tableName + "%s;",
                " drop column if exists " + columnName
        );
        doTable(sql, tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
            throws SQLException {
        String sql = String.format(
                "alter table if exists " + tableName + "%s;",
                " rename " + columnName + " to " + newColumnName
        );
        doTable(sql, tableName);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        StringBuilder sb = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            sb.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                sb.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        TableEditor tableEditor = new TableEditor(properties);
        tableEditor.dropTable("test_db");
        tableEditor.createTable("test_db");
        tableEditor.addColumn("test_db", "surname", "varchar(50)");
        tableEditor.addColumn("test_db", "height", "integer");
        tableEditor.addColumn("test_db", "weight", "integer");
        tableEditor.dropColumn("test_db", "weight");
        tableEditor.renameColumn("test_db", "height", "height2");
        System.out.println(TableEditor.getTableScheme(tableEditor.connection, "test_db"));
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
