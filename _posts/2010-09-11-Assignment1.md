---
layout: posts
---

# Hello Mad

## Lesson 1.1 - ([GitHub](https://github.com/mgatesdehn/CS5520/tree/main/Assignment1/Lesson1_1))
![SS1_1](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/1_1.png)\

#### What I learned:
- Android uses XML files for defining most of its features.
- Gradle is used for building projects.
- Log statements can be added that can help in debugging.

#### Homework:
Question 1\
What is the name of the layout file for the main activity?

> MainActivity.java\
    AndroidManifest.xml\
    **activity_main.xml**\
    build.gradle\

Question 2\
What is the name of the string resource that specifies the application's name?

> **app_name**\
    xmlns:app\
    android:name\
    applicationId\

Question 3\
Which tool do you use to create a new emulator?

>Android Device Monitor\
    **AVD Manager** \
    SDK Manager\
    Theme Editor\

Question 4\
Assume that your app includes this logging statement:
`Log.i("MainActivity", "MainActivity layout is complete");`
You see the statement "MainActivity layout is complete" in the Logcat pane if the Log level menu is set to which of the following? (Hint: multiple answers are OK.)

> **Verbose**\
    Debug\
    **Info**\
    Warn\
    Error\
    Assert\

## Lesson 1.2A - ([GitHub](https://github.com/mgatesdehn/CS5520/tree/main/Assignment1/Lesson1_2))
![SS1_2A](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/1_2A.png)\
#### What I learned:
- Android Studio has a convenient visual layout manager.
- It's best practice to define strings in the resources folder.
- Button Handlers

## Lesson 1.2B - ([GitHub](https://github.com/mgatesdehn/CS5520/tree/main/Assignment1/Lesson1_2))
![linear](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/linear.png)\
![relative](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/relative.png)\
#### What I learned:
- Creating layout variants for horizontal layouts, tablets etc.
- Align baselines to align views that have text.
- LinearLayout and RelativeLayout ViewGroups.

#### Homework:
![1](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/1.png)\
![2](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/2.png)\
![3](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/3.png)\
![4](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/4.png)\

Question 1\
Which two layout constraint attributes on the Zero Button position it vertically equal distance between the other two Button elements? (Pick 2 answers.)

> **app:layout_constraintBottom_toTopOf="@+id/button_count"**\
    android:layout_marginBottom="8dp"\
    android:layout_marginStart="16dp"\
    **app:layout_constraintTop_toBottomOf="@+id/button_toast"**\
    android:layout_marginTop="8dp"\

Question 2\
Which layout constraint attribute on the Zero Button positions it horizontally in alignment with the other two Button elements?

> **app:layout_constraintLeft_toLeftOf="parent"**\
    app:layout_constraintBottom_toTopOf="@+id/button_count"\
    android:layout_marginBottom="8dp"\
    app:layout_constraintTop_toBottomOf="@+id/button_toast"\

Question 3\
What is the correct signature for a method used with the android:onClick XML attribute?

> public void callMethod()\
    **public void callMethod(View view)**\
    private void callMethod(View view)\
    public boolean callMethod(View view)\

Question 4\
The click handler for the Count Button starts with the following method signature:

    public void countUp(View view)

Which of the following techniques is more efficient to use within this handler to change the Button element's background color? Choose one:

> Use findViewById to find the Count Button. Assign the result to a View variable, and then use setBackgroundColor().
    **Use the view parameter that is passed to the click handler with setBackgroundColor(): view.setBackgroundColor()**

## Lesson 1.3 - ([GitHub](https://github.com/mgatesdehn/CS5520/tree/main/Assignment1/Lesson1_3))
![1_3](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/1_3.png)\
![1_3_2](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/1_3_2.png)\
#### What I learned:
- How to use ScrollViews
- Nesting ViewGroups

#### Homework
![1_3_hw](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment1/1_3_hw.png)\

Question 1\
How many views can you use within a ScrollView? Choose one:

> One view only\
    **One view or one view group**\
    As many as you need\

Question 2\
Which XML attribute do you use in a LinearLayout to show views side by side? Choose one:

> **android:orientation="horizontal"**\
    android:orientation="vertical"\
    android:layout_width="wrap_content"\

Question 3\
Which XML attribute do you use to define the width of the LinearLayout inside the scrolling view? Choose one:

> android:layout_width="wrap_content"\
    **android:layout_width="match_parent"**\
    android:layout_width="200dp"\



