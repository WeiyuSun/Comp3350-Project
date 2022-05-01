/**
 * class UIChecker
 * Author: Weiyu Sun
 *
 * a tool to check if user input is valid
 */
package comp3350.budgetingApp.UI;

import android.content.Context;
import android.widget.Toast;

import comp3350.budgetingApp.controller.AccessUsers;

public class UIChecker {

    /**
     * check if input name valid
     *
     * 1. less then 80 characters
     * 2. only letters and ' ' allowed
     * 3. Cannot start or end with ' '
     * 4. cannot be empty
     *
     * @param name
     * @param context
     * @return
     */
    public static boolean isNameValid(String name, Context context) {
        if (name == null || name.length() == 0) {
            Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (name.length() > 80) {
            Toast.makeText(context, "Invalid Name. Please enter less than 80 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!name.matches("^[ A-Za-z]*$")) {
            Toast.makeText(context, "Invalid Name. Only letters and ' ' are allowed", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (name.charAt(0) == ' ' || name.charAt(name.length() - 1) == ' ') {
            Toast.makeText(context, "Invalid Name. Cannot start or end with a space", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * check if user name valid
     *
     * 1 less then 20 characters
     * 2 Only letters, numbers and '_' are allowed
     * 3 the username is unique
     * 4 cannot be empty
     *
     * @param userName
     * @param context
     * @return
     */
    public static boolean isUserNameValid(String userName, Context context) {
        if (userName == null || userName.length() == 0) {
            Toast.makeText(context, "User name cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        int n = userName.length();
        if (n > 20) {
            Toast.makeText(context, "Invalid User Name. Please enter less than 20 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!userName.matches("^[a-zA-Z0-9_]*$")) {
            Toast.makeText(context, "Invalid User Name. Only letters, numbers and '_' are allowed", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(new AccessUsers().getUser(userName) != null){
            Toast.makeText(context, "This user name already exists", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * 1 cannot be empty
     * 2 cannot longer than 100 characters
     * 3 follow the email format, ex: xxxx@xxx.xxx
     * @param email
     * @param context
     * @return
     */
    public static boolean isEmailValid(String email, Context context) {
        if (email == null || email.length() == 0) {
            Toast.makeText(context, "Email cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (email.length() > 100) {
            Toast.makeText(context, "Email cannot longer than 100 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        String emailReg = "^[0-9A-Za-z]+([-_+.][0-9A-Za-z]+)?[@][a-zA-Z0-9]+[.][a-zA-Z]{2,4}([.][a-zA-Z]{2,4})?$";

        if (!email.matches(emailReg)) {
            Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * check if phone number valid
     *
     * 1 cannot be emoty
     * 2 length = 10
     * 3 just contain numbers
     * @param phone
     * @param context
     * @return
     */
    public static boolean isPhoneValid(String phone, Context context) {
        if (phone == null || phone.length() == 0) {
            Toast.makeText(context, "Phone number cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (phone.length() != 10) {
            Toast.makeText(context, "Please input valid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!phone.matches("^[0-9]*$")) {
            Toast.makeText(context, "Please input valid phone number", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * check if age valid
     *
     * 1 cannot be empty
     * 2 a number from 5 to 120
     * @param age
     * @param context
     * @return
     */
    public static boolean isAgeValid(String age, Context context) {
        if (age == null || age.length() == 0) {
            Toast.makeText(context, "Age cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        int ageInt = Integer.parseInt(age);

        if (ageInt < 5 || ageInt > 120) {
            Toast.makeText(context, "Invalid age, Available age: 5~120", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * check if pass word valid
     *
     * 1 cannot be empty
     * 2. password is longer than 8 characters
     * 3. password is less than 20 characters
     * 4 contain at least two kinds of uppercase letter, lowercase letter, number and special symbol
     * @param password
     * @param context
     * @return
     */
    public static boolean isPasswordValid(String password, Context context) {
        if (password == null || password.length() == 0) {
            Toast.makeText(context, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 8) {
            Toast.makeText(context, "Please make sure your password is longer than 8 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() > 20) {
            Toast.makeText(context, "Please make sure your password is less than 20 characters", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (!password.matches("^(?![A-Z]+$)(?![a-z]+$)(?!\\d+$)(?![\\W_]+$)\\S{6,16}$")) {
            Toast.makeText(context, "Your password must contain at least two kinds of uppercase letter, lowercase letter, number and special symbol", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * check if security question valid
     *
     * 1 cannot be empty
     * 2 can only contain letters, numbers and space
     * 3 less then 400 characters
     *
     * @param msg
     * @param context
     * @return
     */
    public static boolean isSecurityQuestionValid(String msg, Context context) {
        if (msg == null || msg.length() == 0) {
            Toast.makeText(context, "Security question cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!msg.matches("^[ a-zA-Z0-9]*$")) {
            Toast.makeText(context, "Security question can contain letters, numbers and space", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (msg.length() > 400) {
            Toast.makeText(context, "Please make sure your security question less then 400 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * check if security answer valid
     *
     * 1. cannot be empty
     * 2. can only contain letters, numbers and space
     * 3. less then 100 characters
     *
     * @param msg
     * @param context
     * @return
     */
    public static boolean isSecurityAnswerValid(String msg, Context context) {
        if (msg == null || msg.length() == 0) {
            Toast.makeText(context, "Security answer cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!msg.matches("^[ a-zA-Z0-9]*$")) {
            Toast.makeText(context, "Security answer can contain letters, numbers and space", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (msg.length() > 100) {
            Toast.makeText(context, "Please make sure your security answer less then 100 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public static boolean isNum(String string){
        int flag=0;
        if(string.charAt(0)=='0'&&string.charAt(1)!='.')
            return false;
        if(string.charAt(0)=='.')
            return false;
        for(int i=0;i<string.length();i++)
        {
            if((string.charAt(i)<'0'||string.charAt(i)>'9')&&string.charAt(i)!='.')
                return false;
            else if(string.charAt(i)=='.')
            {
                flag++;
                if(flag>1)
                    return false;
            }
        }
        return true;
    }
}