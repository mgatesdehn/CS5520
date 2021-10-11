---
layout: posts
---

# A4 - ([GitHub](https://github.com/mgatesdehn/CS5520/tree/main/Assignment4))
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/1.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/2.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/3.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/4.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/5.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/6.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/7.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/8.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/9.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/10.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/11.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/12.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/13.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/14.png" width="300"/>\
<img src="https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment4/15.png" width="300"/>

## Features:
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
- Using Room for managing a local database. 
- Repository for an additional layer of abstraction between the ViewModel and datasource(s).
- Repository and database belong to the app lifecycle. ViewModel belongs to the activity lifecycle.
