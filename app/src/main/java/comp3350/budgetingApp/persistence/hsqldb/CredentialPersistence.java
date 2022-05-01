package comp3350.budgetingApp.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comp3350.budgetingApp.objects.User;

public class CredentialPersistence implements comp3350.budgetingApp.persistence.CredentialPersistence {

    private final String path;
    public CredentialPersistence(String path) {
        this.path = path;
    }
    private Connection connection() throws SQLException { return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", ""); }

    //Any Type of GET Request Include: Password, Question, Answer From Credentials Table
    @Override
    public String getSingleEntity(String userID, String type) throws SQLException {

        //validating Type
        assert(type.equals("password") || type.equals("question") || type.equals("answer"));

        String request = "";
        try(final Connection con = connection()) {
            final PreparedStatement query = con.prepareStatement("SELECT * FROM credentials WHERE userID=?;");
            query.setString(1, userID);

            final ResultSet set = query.executeQuery();
            if (!set.isBeforeFirst() ) {
                return null;
            }
            set.next();
            request = set.getString(type);
            set.close();
            query.close();
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
        return request;
    }

    //Post New User Credentials
    @Override
    public void postCredentials(User newUser) {
        //validating Type
        try (final Connection con = connection()) {

            final PreparedStatement st = con.prepareStatement("INSERT INTO credentials VALUES(?, ?, ?, ?);");
            st.setString(1, newUser.getUserName());
            st.setString(2, newUser.getPassword());
            st.setString(3, newUser.getSecurityQuestion());
            st.setString(4, newUser.getSecurityAnswer());

            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Any Type of SET Request Include:Name, Email, Age, Phone From User Table
    @Override
    public void setSingleValue(String userID, String type, String newValue) {
        assert (type.equals("password") || type.equals("question") || type.equals("answer"));
        try (final Connection c = connection()) {

            final PreparedStatement st = c.prepareStatement("UPDATE credentials SET " + type + " = ? WHERE userID = ?;");

            st.setString(1, newValue);
            st.setString(2, userID);
            st.executeUpdate();

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
