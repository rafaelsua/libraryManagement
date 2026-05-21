# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Context

This is a **teaching project** — a Java/Maven exercise where students implement a library management system. The codebase intentionally ships with empty methods marked `// TODO:` and stub returns. **Do not "fix" TODOs unsolicited**; they are the exercises. When asked to help, prefer guiding completion of the specific TODO the user mentions over completing the whole project.

The README (`README.md`) lists 8 exercises: Book (1), User borrow/return/reserve (2), Library CRUD (3), own tests (4), search by title/author (5), reservations (6), Loan with dates (7), Streams search (8). There is no JDBC exercise.

## Commands

```bash
mvn compile                                         # compile only
mvn test                                            # run all JUnit 5 tests
mvn test -Dtest=LibraryTest                         # run one test class
mvn test -Dtest=LibraryTest#testBorrowBook          # run one test method
mvn clean install                                   # full build
mvn exec:java -Dexec.mainClass=es.ing.tomillo.library.service.Library  # run CLI (no exec plugin configured; use IDE or java -cp)
```

The `LibraryCLI` class owns the interactive `Scanner`-based menu — run it from the IDE or via `mvn exec:java -Dexec.mainClass=es.ing.tomillo.library.service.LibraryCLI`.

## Architecture

Java 17 + JUnit Jupiter 5.9.1. The only runtime dependency is the JDK; JUnit is test-scope. No Spring, no persistence, no JDBC.

Package layout (package `es.ing.tomillo.library`, Maven `groupId` is `es.ing`):

- `model/Book` — stub; students implement fields, constructor, getters/setters, `toString`, `equals`
- `model/User` — entity with `borrowedBooks` list capped at `MAX_BORROWED_BOOKS = 5`; implements `borrowBook`, `returnBook`, `equals` (by `id`)
- `model/Loan` — stub (exercise 7); students add `book`, `user`, `loanDate`, `dueDate`, constructor, getters, `isOverdue()`
- `service/Library` — aggregates `List<User>` and `List<Book>`; most methods are stubs; also has `getAvailableBooks()` and `searchAllBooksByAuthor()` stubs for exercise 8
- `service/LibraryCLI` — interactive menu, not modified by students
- `util/SampleData` — sample users and books (books commented out until Book is implemented)
- `test/.../LibraryTest` — 27 spec-first tests covering exercises 1–3, 5, 7, 8

## Key implementation contract

When implementing `Book`, the existing code in `User` and `Library` already calls these methods — they must exist with these exact signatures:

- `Book(String title, String author, String isbn)` — see `LibraryTest` and the commented `SampleData.SAMPLE_BOOKS`
- `boolean isAvailable()` / `void setAvailable(boolean)` — called by `User.borrowBook` (sets false), `User.returnBook` (sets true), `User.reserveBook`, `Library.listAvailableBooks`
- `String getTitle()` — called by `LibraryTest`
- Default `available` should be `true` (the borrow test asserts it flips to false)

The tests compile only once `Book` is implemented (exercise 1). Until then `mvn test` fails with "cannot find symbol" — this is intentional.

## Conventions

- Comments and identifiers mix Spanish and English (`prestarLibro` in TODO comments, `borrowBook` in code). New code should match the surrounding style of the class being edited.
- Recent commits show the project switched from arrays to `List<>` — keep collections, don't reintroduce arrays.
