# Event Sourcing from Scratch

In a programming language of your choice, create a contacts list application.

## Goal

Understand the Event Sourcing pattern by implementing it from scratch.

## Feature requirements

The application should have the following features:

* A contacts list is a collection of contacts.
* A contact is a natural person or a company.
* A person has a name and can be renamed.
* A company has a name and can be renamed.
* A person can be part of a company.
* If a person is part of a company, they have a role in that company.
* Two contacts in a contacts list can have the same name.

## Technical Requirements

* Use event sourcing to record state changes.
* Provide a working, self contained code example that uses your implementation
  to build a contacts list with some contacts in it, for example as
  * A CLI script
  * A dynamic CLI application
  * A test suite
* Provide a README with instructions on how to run the example. Don't 
  assume that everybody has the programming language installed on their 
  computer or knows how to use the code example. A docker image is probably
  the safest choice.

## Suggestions

* Anticipate questions from developers that don't know the language and language
  concepts and try to avoid them
  * Prefer being explicit over being clever
  * Don't be afraid of boilerplate code
  * Try not to use "magic"
  * Use meaningful names for variables and methods
* Use as many 3rd party libraries as needed and as few as possible
* Try not to use a full stack framework like Symfony, Laravel, Django, 
  Spring Boot (see: "Don't be afraid of boilerplate code")
* "Overengineering" is relative concept - be as crude or sophisticated 
  as you want to be
* Start your implementation with In Memory Persistence, try not to get 
  diverted by abstraction layers.


## Agreements

* Present to your peers what you did, how you did it and why you did it
  the way you did it.
* Your peers will ask questions, make suggestions, discuss concepts that they
  think you might have misunderstood - they will do this in good faith, don't 
  take it personally, we are all here to learn and to teach.
* There will be no evaluation of a better or worse solution.
* There will be no judgement over the used programming language.

## Implementations

| Language | Repository |
| --- | --- |
|   |   |

Create a pull request on this repository to add your implementation


Happy coding!
