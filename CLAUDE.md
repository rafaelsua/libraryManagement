# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Context

This is a **teaching project** — a Java/Maven exercise where students implement a library management system. The codebase intentionally ships with empty methods marked `// TODO:` and stub returns. **Do not "fix" TODOs unsolicited**; they are the exercises. When asked to help, prefer guiding completion of the specific TODO the user mentions over completing the whole project.

The README (`README.md`) lists the seven exercises (Book attributes/constructor, User borrow/return/reserve, Library CRUD/search, JUnit tests, search by title/author, reservations, optional JDBC challenge).

## Commands

```bash
mvn compile                                         # compile only
mvn test                                            # run all JUnit 5 tests
mvn test -Dtest=LibraryTest                         # run one test class
mvn test -Dtest=LibraryTest#testBorrowBook          # run one test method
mvn clean install                                   # full build
mvn exec:java -Dexec.mainClass=es.ing.tomillo.library.service.Library  # run CLI (no exec plugin configured; use IDE or java -cp)
```

The `Library` class has a `main()` with an interactive `Scanner`-based menu — run it directly from your IDE.

## Architecture

Java 17 + JUnit Jupiter 5.9.1. The only runtime dependency is the JDK; JUnit is test-scope. There is **no Spring, no persistence layer, no JDBC** despite the README's optional challenge — `LibraryJDBC` does not exist in the codebase.

Package layout (note the package is `es.ing.tomillo.library`, while the Maven `groupId` is `es.ing`):

- `model/Book` — entity (currently a stub; students implement fields, constructor, getters/setters, `toString`, `equals`)
- `model/User` — entity with `borrowedBooks` list capped at `MAX_BORROWED_BOOKS = 5`; already implements `borrowBook`, `returnBook`, `reserveBook`, `equals` (by `id`)
- `service/Library` — aggregates `List<User>` and `List<Book>`, loads `SampleData` in constructor, exposes add/borrow/return/search methods (most are stubs), and owns `main()`
- `util/SampleData` — static lists of 10 sample users (active) and 10 sample books (commented out because `Book` has no constructor yet)
- `test/.../LibraryTest` — three JUnit tests that **define the expected `Book` API**: constructor `Book(String title, String author, String isbn)`, `getTitle()`, `isAvailable()`, default `available = true`

## Key implementation contract

When implementing `Book`, the existing code in `User` and `Library` already calls these methods — they must exist with these exact signatures:

- `Book(String title, String author, String isbn)` — see `LibraryTest` and the commented `SampleData.SAMPLE_BOOKS`
- `boolean isAvailable()` / `void setAvailable(boolean)` — called by `User.borrowBook` (sets false), `User.returnBook` (sets true), `User.reserveBook`, `Library.listAvailableBooks`
- `String getTitle()` — called by `LibraryTest`
- Default `available` should be `true` (the borrow test asserts it flips to false)

`LibraryTest.testReturnBook` asserts `assertNull(user.getBorrowedBooks().get(0))` after a return, which would throw `IndexOutOfBoundsException` against the current `User.returnBook` (which removes from the list). That test is inconsistent with the implementation — flag it to the user rather than silently changing `User` to match.

## Conventions

- Comments and identifiers mix Spanish and English (`prestarLibro` in TODO comments, `borrowBook` in code). New code should match the surrounding style of the class being edited.
- Recent commits show the project switched from arrays to `List<>` — keep collections, don't reintroduce arrays.
