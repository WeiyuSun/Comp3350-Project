package comp3350.budgetingApp.persistence.hsqldb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import comp3350.budgetingApp.objects.Entry;
import comp3350.budgetingApp.objects.Expense;
import comp3350.budgetingApp.objects.Income;

public class EntriesPersistence implements comp3350.budgetingApp.persistence.EntriesPersistence {
    private final String path;

    public EntriesPersistence(String path) { this.path = path; }

    private Connection connection() throws SQLException { return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", ""); }

    //Any Type of GET Request Include: Expenses(0), Incomes(1), Both(2) From Credentials Table
    @Override
    public ArrayList<Entry> getEntries(String userID, int type) {
        ArrayList<Entry> entries = new ArrayList<>();
        //validating Type
        assert(type == 0 || type == 1 || type == 2);

        String request = "";
        try(final Connection con = connection()) {
            PreparedStatement query;
            if(type == 2) {
                query = con.prepareStatement("SELECT * FROM entries WHERE userID=?;");
                query.setString(1, userID);
            }
            else {
                query = con.prepareStatement("SELECT * FROM entries WHERE type=? and userID=?;");
                query.setInt(1, type);
                query.setString(2, userID);
            }

            ResultSet set = query.executeQuery();
            while(set.next()) {
                int value = set.getInt("type");
                String name = set.getString("name");
                String entryID = set.getString("entryID");
                int amount = set.getInt("amount");

                if( value == 0 ) {
                    Entry newE = new Expense(name, amount);
                    newE.setEntryID(entryID);
                    entries.add(newE);
                }
                if ( value == 1 ) {
                    Entry newE = new Income(name, amount);
                    newE.setEntryID(entryID);
                    entries.add(newE);
                }
            }
            set.close();
            query.close();
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return entries;
    }

    @Override
    public ArrayList<Entry> getEntriesFilter(String userID, float max, float min, int type) {

        ArrayList<Entry> entries = new ArrayList<>();
        //validating Type
        assert(type == 0 || type == 1 || type == 2);

        String request = "";
        try(final Connection con = connection()) {
            PreparedStatement query;
            if(type == 2) {
                query = con.prepareStatement("SELECT * FROM entries WHERE amount >= ? AND amount <= ? AND userID=?;");
                query.setBigDecimal(1, BigDecimal.valueOf(min));
                query.setBigDecimal(2, BigDecimal.valueOf(max));
                query.setString(3, userID);
            }
            else {
                query = con.prepareStatement("SELECT * FROM entries WHERE amount >= ? AND amount <= ? AND type=? AND userID=?;");
                query.setBigDecimal(1, BigDecimal.valueOf(min));
                query.setBigDecimal(2, BigDecimal.valueOf(max));
                query.setInt(3, type);
                query.setString(4, userID);
            }
            ResultSet set = query.executeQuery();
            while(set.next()) {
                int value = set.getInt("type");
                String name = set.getString("name");
                String entryID = set.getString("entryID");
                int amount = set.getInt("amount");

                if( value == 0 ) {
                    Entry newE = new Expense(name, amount);
                    newE.setEntryID(entryID);
                    entries.add(newE);
                }
                if ( value == 1 ) {
                    Entry newE = new Income(name, amount);
                    newE.setEntryID(entryID);
                    entries.add(newE);
                }
            }
            set.close();
            query.close();
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return entries;
    }

    //Post New User Credentials
    @Override
    public void postEntry(String userID, Entry newEntry) {
        //validating Type
        try (final Connection con = connection()) {

            PreparedStatement query = con.prepareStatement("INSERT INTO entries VALUES(?, ?, ?, ?, ?);");
            query.setString(1, userID);
            query.setString(2, newEntry.getEntryID());
            query.setString(3, newEntry.getName());
            query.setBigDecimal(4 , BigDecimal.valueOf(newEntry.getAmount()));
            if(newEntry instanceof Income)
                query.setInt(5, 1);

            if(newEntry instanceof Expense)
                query.setInt(5, 0);

            query.executeUpdate();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public void deleteEntry(String entryID) {
        try(final Connection con = connection()) {
            PreparedStatement query = con.prepareStatement("DELETE FROM entries WHERE entryID = ?;");
            query.setString(1, entryID);
            if(query.executeUpdate() == 0)
                System.out.println(entryID + "not found");
        } catch (SQLException e) { throw new PersistenceException(e); }
    }
}
