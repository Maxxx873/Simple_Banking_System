package banking.controller;

import banking.entitie.Card;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabase {

    String url;
    Connection connection;

    public SQLiteDatabase(String fileName) {
        this.url = "jdbc:sqlite:C:/Simple Banking System/Simple Banking System/task/" + fileName;;
    }

    public Connection connectionToDatabase () {
        String url = this.url;
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            try (Statement statement = conn.createStatement()) {

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS card(" +
                        "id INTEGER PRIMARY KEY," +
                        "number TEXT NOT NULL," +
                        "pin TEXT NOT NULL," +
                        "balance INTEGER DEFAULT 0)");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.connection = conn;

        return conn;

    }

    public void addToDatabase (Card card) {

        try (Connection conn = this.connectionToDatabase()) {

            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate("INSERT INTO card (number, pin) VALUES " + "('"+
                        card.getCardNumber() +"', '"+
                        card.getPinCode() + "')");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void closeConnection () {

            try {
                this.connectionToDatabase().close();
            } catch (SQLException se) {
                se.printStackTrace();
            }

    }

}
