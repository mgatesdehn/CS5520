---
layout: posts
---

# A5 - ([GitHub](https://github.com/mgatesdehn/CS5520/tree/main/Assignment5))
Create notification that will go off next minute:\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment5/1.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment5/2.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment5/3.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment5/4.png" width="300"/>\
Edit notification so it goes off next minute:\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment5/5.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment5/6.png" width="300"/>\
Edit notification so that it deletes the notification:\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment5/7.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment5/8.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment5/9.png" width="300"/>\
Notification doesn't go off because we disabled it:\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment5/10.png" width="300"/>

## New Features:
##### Notifications
- Notifications have been added for the remind me date set by the user. 
- Remind me checkbox schedules a notification for the provided date/time.
-- Notification includes the title of the task and a snippet of the description.
-- Clicking the notification opens the task list.
- Unchecking the remind me checkbox and saving the task deletes the pending notification.
- Implemented using WorkManager

## Old Features:
- Retains data locally in database. Restores data when app is restarted.
- Single activity, multi-fragment design using NavController
- Recommended architecture with ViewModel and Repository

##### Task List Screen
- Add new task (green circle with '+' symbol)
- Checkbox task complete
- Display task title and due date
- Delete task (Red 'X')
- Edit task by clicking on it

##### Edit Task Screen
- Enter/Edit Task Title, Description, DueDate, Remind Me Date
- Enable Reminder Checkbox (No notifications currently)
- DueDate and Remind Me Date open time/date picker
- Cancel button to discard edits
- Save button to save edits

## Deploy to App Store (closed testing):
[Web Test Link](https://play.google.com/apps/testing/edu.neu.khoury.madsea.matthewgatesdehn)\
[App Link - https://play.google.com/store/apps/details?id=edu.neu.khoury.madsea.matthewgatesdehn](https://play.google.com/store/apps/details?id=edu.neu.khoury.madsea.matthewgatesdehn)

## What I learned:
- WorkManager for scheduling tasks
- Keeping track of the next ID in the database is weird.
