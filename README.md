# Event Sourcing from Scratch

Getting a better understanding of the Event Sourcing mechanics 
by implementing them from scratch.

[![Discord](https://img.shields.io/discord/523866370778333184.svg?color=7289da&logo=discord)](https://discord.gg/TU8qucU)

---

* [The Challenge](#the-challenge)
* [Possible use cases](#possible-use-cases)
  * [Club membership management](#club-membership-management)
  * [Course Management at a college](#course-management-at-a-college)
  * [Business Contacts List](#contacts-list)
  * [_Share your use case_](#share-your-use-case)
* [Suggestions](#suggestions)
* [Agreements](#agreements)
* [Implementations](#implementations)

---

## The challenge

* In a programming language of your choice, implement one of the following
  use cases (or your own)
* Implement and use event sourcing to record state changes.
* Provide a working, self-contained code example that uses your implementation
  to build a contacts list with some contacts in it, for example as
  * A CLI script
  * A dynamic CLI application
  * A test suite
* Provide a README with instructions on how to run the example. Don't 
  assume that everybody has the programming language installed on their 
  Computer or knows how to use the code example. A docker image is probably
  the safest choice.

## Possible use cases

Please note that these use cases and the proposed features are meant only to
give you an idea on what _could_ be required - they are not required in the
scope of this challenge. Remember: this is for you, not for anybody else!

### Club membership management

Imagine that you want to manage a club (or a company) and want to track changes
that happen with an within the club, for example:

* A club can be founded, and there can be more than one club.
* A person can apply for membership in a club.
* A person can be approved or denied a membership.
* A membership can be terminated, by the club or by the member.
* There can be different kind of memberships (e.g., free, paid, sponsored)
  and each kind of membership has different perks.

### Course management at a college

_This use case has been suggested by [Kasey Speakman](https://dev.to/kspeakman)
on [dev.to](https://dev.to/jeromegamez/code-challenge-event-sourcing-1e7d)_.

Your college wants to manage its students and courses and asks you to create an
application that handles, for example, the following features:

* The college provides courses.
* A course is held in the scope of a semester.
* A course has dates in a week on which it is held.
* The same course can be held in multiple semesters.
* A student can attend and withdraw from a course.
* A student receives a grade after completing a course.
* A student receives a grade after completing a semester.
  * Course grades are factored into the final grade, but also the number 
    of courses that students have attended or withdrawn from.

### Contacts List

Imagine you want to manage a business contacts list and want to track relevant
changes of a person or company, for example:

* A contacts list is a collection of contacts.
* A contact is either a natural person or a company.
* A person has a name and can be renamed.
* A company has a name and can be renamed.
* A person can enter and quite a company.
* If a person is part of a company, they have a role in that company.

### Share your use case

Do you have an idea for a use case? Then join us on 
[Discord](https://discord.gg/TU8qucU) or create a new issue in the repo's
issue tracker and let's talk!

## Suggestions

* Anticipate questions from developers that don't know the language and language
  concepts and try to avoid them
  * Prefer being explicit over being clever.
  * Don't be afraid of boilerplate code.
  * Try not to use "magic".
  * Use meaningful names for variables and methods.
* Use as many 3rd party libraries as needed and as few as possible.
* Try not to use a full-stack framework like Symfony, Laravel, Django, 
  Spring Boot (see: "Don't be afraid of boilerplate code").
* Be as simple or sophisticated in your implementation as you want to 
  be - the goal is for you to get accustomed to the mechanics of 
  Event Sourcing, not to create the next LinkedIn :).
* Start your implementation with In-memory-persistence, try not to get 
  diverted by abstraction layers.
* If you have questions about the challenge, are stuck or want to hang
  around with fellow developers, we'd be happy to have you on the 
  [kreait Discord](https://discord.gg/TU8qucU)!

## Agreements

_(These agreements are mostly meant for the developers at kreait internally,
but feel free to adapt them)_

* Present to your peers what you did, how you did it and why you did it
  the way you did it.
* Your peers will ask questions, make suggestions, discuss concepts that they
  think you might have misunderstood - they will do this in good faith, don't 
  take it personally, we are all here to learn and to teach.
* There will be no evaluation of a better or worse solution.
* There will be no judgment over the used programming language.

## Implementations

| Language | Use case | Repository |
| --- | --- | --- |
|   |   |   |

Create a pull request on this repository to add your implementation.

Happy coding!
