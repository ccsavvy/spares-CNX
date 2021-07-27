# spares-CNX
SparesCNX ~ Cat Knowledge Base

# Basic task
The app allows interested users to learn new facts about cats. Each fact on a screen looks like a picture with a
cat, under which there is text information, as well as the date when the fact was added. All facts are placed in a
vertical scrollable feed.
Initially, the user is shown a blank screen with an invitation to learn a new fact. When you click on the button in
the action bar, one random fact is loaded, as well as a random picture for this fact.
A random image is taken from the service https://cataas.com
A random fact is taken from https://catfact.ninja. I decided not to use https://alexwohlbruck.github.io/cat-facts/ as it is not working anymore.
The next time you click, a new fact and image are loaded and added to the feed.

# Mandatory requirements
- The code is written only in Java. You can't use Kotlin/Scala.
- Implemented MVP architecture.
- Use of Dependency Injection
- Use of RxJava

# Extra requirements:
- The app is designed in Material Design.
- Unit tests.

# Additional task 1
Implement the offline mode of the app. All uploaded facts are saved to the database (preferably using Room)
and displayed from the database when there is no internet connection.

# Additional task 2
Implement the ability to delete a fact from the screen and from the database. You can do this through a long
press on the corresponding fact.

## Final output (Screenshots)

1.
  ![alt text](https://github.com/ccsavvy/spares-CNX/blob/main/Screenshot_1627403292.png?raw=true)

2.
  ![alt text](https://github.com/ccsavvy/spares-CNX/blob/main/Screenshot_1627403300.png?raw=true)
  
3.
  ![alt text](https://github.com/ccsavvy/spares-CNX/blob/main/Screenshot_1627403320.png?raw=true)
  
4.
  ![alt text](https://github.com/ccsavvy/spares-CNX/blob/main/Screenshot_1627403322.png?raw=true)
  
5.
  ![alt text](https://github.com/ccsavvy/spares-CNX/blob/main/Screenshot_1627403333.png?raw=true)
  
