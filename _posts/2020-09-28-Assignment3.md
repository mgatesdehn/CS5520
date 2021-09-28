
---
layout: posts
---

# A3 - ([GitHub](https://github.com/mgatesdehn/CS5520/tree/main/Assignment3))
![SS1](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment3/1.png)\
![SS2](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment3/2.png)\
![SS3](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment3/3.png)\
![SS4](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment3/4.png)

## Features:
##### Task List Screen
- Add new task (green circle with '+' symbol)
- Checkbox task complete
- Display task title and due date
- Delete task (Red 'X')
- Edit task by clicking on it

##### Edit Task Screen
- Enter/Edit Task Title, Description, DueDate, Remind Me Date
- Enable Reminder Checkbox (No notifications currently)
- Cancel button to discard edits
- Save button to save edits

## Deploy to App Store (closed testing):
- Currently 'in review' (Released Monday)

## What I learned:
- Use of a singleton to store data
- The way I reused a layout for tasks was difficult.
--- I had to assign ids programatically when I created new views for new tasks
--- Accessing the parent view to determine which view was clicked on by the user (for editing existing tasks) was clunky.
- Android retains my singleton variable even when the orientation is changed.
