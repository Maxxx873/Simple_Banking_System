package banking.controller;

import banking.entitie.Account;
import banking.entitie.Card;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Account> selectAllAccounts() {

        ArrayList<Account> accounts = new ArrayList<>();

        try (Connection conn = this.connectionToDatabase()) {
            String sql = "SELECT * FROM card";
            try (Statement statement = conn.createStatement();
                 ResultSet rs = statement.executeQuery(sql)
            ) {
                while (rs.next()) {
                    Account account = new Account();
                    Card card = new Card();
                    card.setCardNumber(rs.getString("number"));
                    card.setPinCode(rs.getString("pin"));
                    card.setBalance(rs.getInt("balance"));
                    account.setCard(card);
                    accounts.add(account);
                }
                return accounts;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accounts;
    }

    public void updateBalance (Card card) {

        try (Connection conn = this.connectionToDatabase()) {
            String sql = "UPDATE card SET balance = ? WHERE number = ? AND pin = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
               preparedStatement.setInt(1, card.getBalance());
               preparedStatement.setString(2, card.getCardNumber());
               preparedStatement.setString(3, card.getPinCode());
               preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void transfer (Card cardFrom, Card cardTo) {

        try (Connection conn = this.connectionToDatabase()) {
            conn.setAutoCommit(false);
            updateBalance(cardFrom);
            updateBalance(cardTo);
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void deleteCard (Card card) {

        try (Connection conn = this.connectionToDatabase()) {
            String sql = "DELETE FROM card WHERE number = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, card.getCardNumber());
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
