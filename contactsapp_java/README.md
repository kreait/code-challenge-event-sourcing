# What this project is about
This project is a response to the [Event Sourcing from Scratch](https://github.com/kreait/code-challenge-event-sourcing) challenge. 
It implements the Contacts List use case.

# How to run it
1. Go to the console and enter `java -version`. It should show that Java is installed and a version >= 8. You can get Java [here](https://jdk.java.net/12/).
2. Download the zip file contained in the `binaries` folder of this project, and unzip it to your hard drive.
3. Using the console, go to the unzipped folder on your hard drive. Enter the `start_scripts`folder. 
5. Run the `contactsapp_java` script (Unix) or the `contactsapp_java.bat` script (Windows).

# The output of the script
The script performs the major steps of the Contacts List use case. The output is:

```Adding person: John Q. Public
Adding person: John Q. Public
Adding company: FooBar Inc.
Renaming person: John Q. Public to: John Q. Private
Replaying events...

The contacts are:
Person [getName()=John Q. Private]
Company [getName()=FooBar Inc.]
```

# How it works internally
1. The main class sends commands and queries (as objects) to the contact list boundary. 
The contact list boundary encapsulates the contact list. It contains models that configure which command/query/event is handled by which handler.
2. The boundary sends a command to its command handler.
3. The command handler performs validation and any business logic to determine the state change. It returns the state change as event.
4. The boundary forwards the event to an event publisher (known via dependency injection). The event publisher in the example is the event store.
5. The event store stores the event in memory. The store then sends the events to subscribers, including the boundary.
6. The boundary handles the event, by performing the state change on the contact list. The contact list is a regular domain class.

Queries are handled differently. Instead of returning events, the query handler returns the result of the query.

# Limitations
For the sake of simplicity, all communication happens synchronous. This will be changed when further use cases are implemented.

