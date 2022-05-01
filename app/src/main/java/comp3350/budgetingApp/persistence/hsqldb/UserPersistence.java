package comp3350.budgetingApp.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import comp3350.budgetingApp.Application.Services;
import comp3350.budgetingApp.objects.User;

public class UserPersistence implements comp3350.budgetingApp.persistence.UserPersistence {
    private final String path;

    public UserPersistence(String path) { this.path = path; }
    private Connection connection() throws SQLException { return DriverManager.getConnection("jdbc:hsqldb:file:" + path + ";shutdown=true", "SA", ""); }

    //Any Type of GET Request Include:Name, Email, Age, Phone From User Table
    @Override
    public User getSingleUser(String userID) {

        User newUser = null;
        try(final Connection con = connection()) {
            final PreparedStatement query = con.prepareStatement("SELECT * FROM users WHERE userID=?;");
            query.setString(1, userID);

            final ResultSet set = query.executeQuery();
            if (!set.isBeforeFirst() ) {
                return null;
            }
            set.next();
            newUser = createUser(set);

            set.close();
            query.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newUser;
    }

    //Any Type of GET Request Include:Name, Email, Age, Phone From User Table
    @Override
    public void setSingleEntity(String userID, String type, String newValue) {

        //validating Type
        assert(type.equals("name") || type.equals("email") || type.equals("age") || type.equals("phone"));

        try(final Connection con = connection()) {
            final PreparedStatement query = con.prepareStatement("UPDATE users SET " + type + " = ? WHERE userID = ?");

            if (type.equals("age"))
                query.setInt(1, Integer.parseInt(newValue));
            else
                query.setString(1, newValue);
            query.setString(2, userID);
            query.executeUpdate();

        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public ArrayList<User> getAll() {

        ArrayList<User> users = new ArrayList<>();

        try(final Connection con = connection()) {
            final PreparedStatement query = con.prepareStatement("SELECT * FROM users;");
            final ResultSet set = query.executeQuery();

            while(set.next()) {
                users.add(createUser(set));
            }
            set.close();
            query.close();
        }
        catch (final SQLException e) {
            throw new PersistenceException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    //Post New User Credentials
    @Override
    public void addUser(User newUser) {

        try (final Connection con = connection()) {

            final PreparedStatement st = con.prepareStatement("INSERT INTO users VALUES(?, ?, ?, ?, ?);");
            st.setString(1, newUser.getUserName());
            st.setString(2, newUser.getName());
            st.setString(3, newUser.getEmail());
            st.setString(4, newUser.getPhone());
            st.setInt(5, newUser.getAge());


            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Post New User Credentials
    @Override
    public void updateUser(String userID, String name, String email, int age, String phone) {
        //validating Type
        assert(email.contains("@") || age > 5 || phone.length() == 10 || name.length() < 100);

        try (final Connection c = connection()) {

            final PreparedStatement query = c.prepareStatement("UPDATE users SET name = ?, email = ?, age = ?, phone = ? WHERE userID = ?;");

            query.setString(5, userID);
            query.setString(1, name);
            query.setString(2, email);
            query.setInt(3, age);
            query.setString(4, phone);
            query.executeUpdate();

        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    private User createUser(final ResultSet rs) throws Exception {
        String userID = rs.getString("userID");
        String name = rs.getString("name");
        String email = rs.getString("email");
        int age = rs.getInt("age");
        String phone = rs.getString("phone");
        CredentialPersistence cp = Services.getCredentialPersistence();
        String password = cp.getSingleEntity(userID, "password");
        String question = cp.getSingleEntity(userID, "question");
        String answer = cp.getSingleEntity(userID, "answer");

        return new User(name, age, userID, password, phone, email, question, answer);
    }
}
