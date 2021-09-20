---
layout: posts
---

# A2

## Lesson 2.1 - ([GitHub](https://github.com/mgatesdehn/CS5520/tree/main/Assignment2/lesson2_1))
![SS2_1_1](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_1_1.png)\
![SS2_1_2](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_1_2.png)\
![SS2_1_3](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_1_3.png)\
![SS2_1_4](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_1_4.png)\
![SS2_1_5](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_1_5.png)\
![SS2_1_6](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_1_6.png)\
![SS2_1_7](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_1_7.png)

#### What I learned:
- Using Intent to start another activity
- Using Intent to pass data to a started activity
- Receiving data back from a started activity

## Lesson 2.2 - ([GitHub](https://github.com/mgatesdehn/CS5520/tree/main/Assignment2/lesson2_2))
![SS2_2_1](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_2_1.png)\
![SS2_2_2](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_2_2.png)\
![SS2_2_3](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_2_3.png)

#### What I learned:
- What the lifecycle of an activity is and when the callbacks are called.
- How to retain instance information when a configuration is changed and the activity destroyed.

#### Homework
![SS2_2_4](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_2_4.png)\
![SS2_2_5](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/SS2_2_5.png)

Question 1\
If you run the homework app before implementing onSaveInstanceState(), what happens if you rotate the device? Choose one:

> The EditText no longer contains the text you entered, but the counter is preserved.\
    The counter is reset to 0, and the EditText no longer contains the text you entered.\
    **The counter is reset to 0, but the contents of the EditText is preserved.**\
    The counter and the contents of the EditText are preserved.

Question 2\
What Activity lifecycle methods are called when a device-configuration change (such as rotation) occurs? Choose one:

> Android immediately shuts down your Activity by calling onStop(). Your code must restart the Activity.\
    Android shuts down your Activity by calling onPause(), onStop(), and onDestroy(). Your code must restart the Activity.\
    **Android shuts down your Activity by calling onPause(), onStop(), and onDestroy(), and then starts it over again, calling onCreate(), onStart(), and onResume().**\
    Android immediately calls onResume().

Question 3\
When in the Activity lifecycle is onSaveInstanceState() called? Choose one:

> **onSaveInstanceState() is called before the onStop() method.**\
    onSaveInstanceState() is called before the onResume() method.\
    onSaveInstanceState() is called before the onCreate() method.\
    onSaveInstanceState() is called before the onDestroy() method.

Question 4\
Which Activity lifecycle methods are best to use for saving data before the Activity is finished or destroyed? Choose one:

> **onPause() or onStop()**\
    onResume() or onCreate()\
    onDestroy()\
    onStart() or onRestart()

## Lesson 2.3 - ([GitHub](https://github.com/mgatesdehn/CS5520/tree/main/Assignment2/lesson2_3))
![SS3_2_1](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/3_2_1.png)\
![SS3_2_2](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/3_2_2.png)\
![SS3_2_3](https://raw.githubusercontent.com/mgatesdehn/CS5520/gh-pages/images/Assignment2/3_2_3.png)

#### What I learned:
- How to use implicit intents to perform external actions (open url, map location, send text).
- API level 30 has new package visibility restrictions.

