# Adding a feature 

The main feature we were able to add was [User account Management](https://code.cs.umanitoba.ca/winter-2022-a01/group-13/unnamed-budgeting-app/-/issues/9). We felt this was extremely important, since user data can change, and users need to be able to update it. For example, if someone is using a password that they know has been comprimised, they need to be able to change that quickly. We also included the password reset option in this, since it's effectively just another way of editing user data.

[Final Commit](https://code.cs.umanitoba.ca/winter-2022-a01/group-13/unnamed-budgeting-app/-/commit/744f7cfeab548c9e1b208883465c65cdf5f38b11)

# Exceptional code

[Exceptional Code](/app/src/test/java/comp3350/budgetingApp/Controller/UserTest.java)

This test needs to deal with the possibility that we get a null value from the database, since it is possible that the user does not exist. It is handled before it can throw an exception, so the user can return to the login page.

# Branching

[Strategy Description](/README.md)
![gitlab graph](/i1_graph.png)

# SOLID

I looked through group 14's personal fitness trainer app, but it looks like they did a good job of following the SOLIDprinciples. I was not able to find any issues with it. Last checked at 10AM March 4.

# Agile Planning

The biggest changes in plans were the Mortage Payment Calculator (MPC) and the Income Expense Manager (IEM).The MPC was pushed back because it and another feature were being done in ways that made conflicting assumptions aboutthe database. Since MPC is not a high priority feature, while the other was, it made sense to go with the implimentationof the other feature. MPC can easily be addapted to fit this new model, however the time constraints would not allowfor proper testing at this point, so we are pushing it to the iteration 2.

IEM was delayed due to a bug that we were not able to fix in time. The code worked locally for the group memeber who made it, but once he commited it, everything broke. That member then needed to go to work, so the rest of us tried to fix it. We ended up being unable to fix it because the code was pointing to a file that was not commited with it. As of writing this, it appears that the file in question is local to the author of the code, so we do not have access to it.
