package comp3350.budgetingApp.controller;

import org.junit.Test;
import static org.junit.Assert.*;

import comp3350.budgetingApp.objects.User;

public class UserTest {
    @Test
    public void testNewUser() {
        String name = "user1";
        int age = 10;
        String userName = "uxer1";
        String password = "password";
        String phone = "2222222222";
        String email = "user@catLover.ca";
        String q1, a1;
        q1 = "Q1";
        a1 = "A1";

        User user = new User(name, age, userName,password,phone,email,q1,a1);

        assertNotNull(user);
        System.out.println("\n Testing User Class");

        //getters
        assertEquals(name, user.getName());
        assertEquals(age, user.getAge());
        assertEquals(email, user.getEmail());
        assertEquals(phone, user.getPhone());
        assertEquals(password, user.getPassword());
        assertEquals(userName, user.getUserName());
        assertNotNull(user.allEntries());
        assertEquals(0, user.allEntries().size());
        assertNotNull(user.getSecurityQuestion());

        //setters
        user.setAge(20);
        user.setEmail("het@notcool.com");
        user.setName("user2");
        user.setPhone("1111111111");
        user.setPassword("password1");
        assertEquals(20, user.getAge());
        assertEquals("het@notcool.com", user.getEmail());
        assertEquals("user2", user.getName());
        assertEquals("1111111111", user.getPhone());
        assertEquals("password1", user.getPassword());

        System.out.println("\n Finished UserTest!");
    }
}