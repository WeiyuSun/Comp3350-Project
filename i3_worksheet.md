What technical debt has been cleaned up
========================================

Most of this iteration has been paying off technical debt. We had a bug that broke our core functionality, and that ended up being a lot more work than anticipated. Nearly all of the commits for this iteration pertain to that.

Regarding the SOLID violation we were given, we made adjustments, but do not believe that it was a true violation in the first place.

What technical debt did you leave?
==================================

I would call this entire project reckless but unintentional debt at this point. There were communication issues throughout the group that spanned the entire term. If this were something that were going to be used long term, it would need a complete overhaul, and honestly I think it would be better to start from scratch.

Discuss a Feature or User Story that was cut/re-prioritized
============================================

We got rid of the currency conversion feature. We decided that it just wasn't that important given all the issues we were having with the core functionality of the app. This is a feature that would still be great to add in the future, but it's really necessary for release. The idea is to roll out the app to Canadians first, which makes currency conversion less important. If we were to plan a worldwide rollout, the feature would have been very high priority.

[user story](https://code.cs.umanitoba.ca/winter-2022-a01/group-13/unnamed-budgeting-app/-/issues/17)

Acceptance test/end-to-end
==========================

The flaky tests were when order mattered. This is specific to adding and removing entries. If a remove occurs before an add, then the test will fail due to no entries existing.

[commit](https://code.cs.umanitoba.ca/winter-2022-a01/group-13/unnamed-budgeting-app/-/commit/cea749ad27fefa675d2c094318580cedb63a081d)

Acceptance test, untestable
===============

Sign-up test ended up being impossible to test. As far as we can tell, Espesso wasn't able to provide the equipment to properly test changes that happened inside the database.

Velocity/teamwork
=================

Our estimates actually ended up becoming somewhat irrelevant, because lots of our work ended up being fixing the last iteration rather than implimenting new things. In that sense, I supposed you could say it got worse, because the time the early features took just kept getting longer and longer with each iteration.

EXTRA IMPORTANT INFO FOR ACCEPTANCE TESTS
=========================================
Normally I would only put this in the README, but I'm putting it here because I'm unsure if markers will even be opening the README at this point.

For running Acceptance tests:

option 1:  Run the acceptance test before running anything in the application. so, when you first download the project build and run the app. After that, don not make any changes in the app and close the app it self and then run all the acceptance test.

option2: If you have checked the app then clear all the data from the app setting (Instruction: long click on app icon -> click app info -> go to storage -> clean data). After that run the app and do not make any changes to the app. Close the app and then run all acceptance tests.
