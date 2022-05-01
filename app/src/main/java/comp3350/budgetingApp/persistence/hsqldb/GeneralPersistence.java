package comp3350.budgetingApp.persistence.hsqldb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class GeneralPersistence implements comp3350.budgetingApp.persistence.GeneralPersistence {
    private final String path;

    public GeneralPersistence(String path) {
        this.path = path;
    }

    private Connection connection() throws SQLException { return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", ""); }

    @Override
    public void clearAllTables() {
        try(final Connection connect = connection()) {

            String q1 = "Delete from entries;";
            connect.createStatement().executeUpdate(q1);
            String q2 = "Delete from credentials;";
            connect.createStatement().executeUpdate(q2);
            String q3 = "Delete from users;";
            connect.createStatement().executeUpdate(q3);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void createTable() {
        System.out.println("Creating Tables....");
        String users = "create table if not exists users (" +
                "userID varchar(100) NOT NULL," +
                "name varchar(100) NOT NULL," +
                "email varchar(45)," +
                "phone varchar(100)," +
                "age integer," +
                "primary key(userID));";

        try(final Connection connect = connection()) {
            connect.createStatement().executeUpdate(users);
            String credentials = "create table if not exists credentials (" +
                    "userID varchar(10) NOT NULL," +
                    "password varchar(50)," +
                    "question varchar(100)," +
                    "answer varchar(100)," +
                    "primary key(userID)," +
                    "foreign key(userID) references users);";

            connect.createStatement().executeUpdate(credentials);
            String entries = "create table if not exists entries (" +
                    "userID varchar(10) NOT NULL," +
                    "entryID varchar(100) NOT NULL," +
                    "name varchar(100) NOT NULL," +
                    "amount decimal(10,2)," +
                    "type integer NOT NULL," +
                    "primary key(entryID)," +
                    "foreign key(userID) references users);";
            connect.createStatement().executeUpdate(entries);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    private void readData() throws IOException {
        BufferedReader in = null;

        try {
            in = new BufferedReader((new FileReader("../app/sql/database.script")));
            String line = in.readLine();
            while (line != null) {
//                connection.createStatement().executeUpdate(line);
                line = in.readLine();
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
