/**
 * class: User
 * Author: Weiyu Sun, Taran
 *
 * Remark: an User object with some personal information
 */
package comp3350.budgetingApp.objects;
import java.util.ArrayList;

public class User {
    private String name;
    private int age;
    private final String userName;
    private String password;
    private final ArrayList<Entry> myEntries;
    private String securityQuestion;
    private String securityAnswer;
    private String phone;
    private String email;


    public User(String name, int age, String userName, String password, String phone, String email, String question, String answer) {
        this.name = name;
        this.age = age;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.myEntries = new ArrayList<Entry>();
        this.securityQuestion = question;
        this.securityAnswer = answer;
    }

    // setter
    public void setPassword(String newPassword) { this.password = newPassword; }
    public void setAge(int age) {
        this.age = age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSecurityQuestion(String newQuestion) { this.securityQuestion = newQuestion; }
    public void setSecurityAnswer(String newAnswer) {this.securityAnswer = newAnswer; }

    //Getter
    public String getName() {
        return name;
    }
    public String getUserName() {
        return userName;
    }
    public String getPhone(){ return phone; }
    public String getEmail(){ return email;}
    public int getAge() { return age; }
    public String getSecurityQuestion() { return securityQuestion; }
    public String getSecurityAnswer() { return securityAnswer; }
    public String getPassword() {
        return password;
    }
    public ArrayList<Entry> allEntries() { return myEntries; }

    public String toString(){
        return "name: " + name + ", username: " + userName;
    }

}
