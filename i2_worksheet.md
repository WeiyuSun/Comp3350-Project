# Paying Off Technical Debt

The first piece was the implimentation of the fake database. Our fake database was not implimented in a way that reflected how the actual database would work. This resulted in a resulted in us needing to restructure how our logic layer was interacting with the database. This debt was unintentional and reckless. We classify it as such because while we had the intention of simulating a the usage of a real database, we did not take adequate steps to ensure that our implimentation would reflect that. We didn't really end up paying this debt off, but rather we unintentionally pushed it back to the next iteration. We made a class that would work as an adapter when we should have spent the time refactoring the code.

[Fixed here](https://code.cs.umanitoba.ca/winter-2022-a01/group-13/unnamed-budgeting-app/-/commit/d8f576be97fee716d6ba0583b26f8090df0db6c6#6be79a963e4a6f6102a4b352453be1c46b289714_0_1)

The second piece of debt paid in this iteration was the entire income/expense module. This was supposed to be implimented and included in the previous iteration, but a bug prevented it from working. Due to this, we had to spend extra time fixing that module in this iteration before we could focus on what we were planning on doing. This debt was somewhat intentional, but still reckless. The recklessness is due to the amount of time that was left to merge this code in. We're still considering it deliberate because regardless of how much time we had left, we still decided it was better to leave the bug for later instead of scrambling last minute to fix it.

[Fixed here](https://code.cs.umanitoba.ca/winter-2022-a01/group-13/unnamed-budgeting-app/-/commit/701f7d56971127d05c5bb36c5700dda97bb309c8)

# SOLID

I think the group we're looking at is doing a great job, because I wasn't able to find any SOLID violations.

# Retrospective

The retrospective did not have a huge impact on us. For the most part, those who worked on things early still did that, and those who started later did that as well. The main part of the retrospective that has improved is class attendance. We've also seen a much higher amount of communication within the group. We do not have logs for class attendance, and all our chats are on Discord, which does not provide a tool to download logs.

# Design Patterns

Our Services.java file is currently working as an adapter. This is to make our existing code work with a real database.

[Services.java](https://code.cs.umanitoba.ca/winter-2022-a01/group-13/unnamed-budgeting-app/-/blob/ab_branch/app/src/main/java/comp3350/budgetingApp/application/Services.java)

# Iteration 1 Feedback Fixes

Unfortunately the required fixing requires a very large refactoring of code, including things implimented for iteration 2 that were already completed at the time that the feedback was received. We plan on addressing these issues in the next itteration.

# Extra Info

We've included the code for the income/expense logic with this one, but the functionality had to be commented out. We ran into a bug and have spent hours attempting to fix it. At the deadline, we decided that our app had enough functionality to justify this. The UI has been implimented, so we're considering it successful in the sense that it's a non-functional prototype feature in this iteration. Our group had the unfortunate circumstance of multiple members getting sick, so this was a bit of a setback for us.

To sign in without creating a user, use the following credentials:
Username: "w"
Password: "s"
