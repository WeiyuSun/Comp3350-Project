package comp3350.budgetingApp.Application;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static String scriptName = "SC";
    public static void main(String[] args) { }

    public static void setDatabasePath(String name) {
        try { Class.forName("org.hsqldb.jdbcDriver").newInstance(); }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) { e.printStackTrace(); }
        scriptName = name;
    }

    public static String getDatabaseName() { return  scriptName; }
}
