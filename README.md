# Biblioteca Java: Primeras Aventuras en la Programación

## ¿Qué vas a construir?

Una aplicación en Java para gestionar una biblioteca: libros, usuarios, préstamos y devoluciones. Al terminar, tendrás un programa funcional que arranca con un menú interactivo y una suite de tests que valida automáticamente tu código.

---

## Antes de empezar

Necesitas tener instalado:
- **Java 17** o superior
- **Maven 3.x**
- Un IDE (IntelliJ IDEA, Eclipse o VS Code)

Haz fork del repositorio y clónalo:

```bash
git clone https://github.com/<tu-usuario>/libraryManagement.git
cd libraryManagement
```

---

## Cómo usar los tests para guiarte

Este proyecto sigue un enfoque **spec-first**: los tests ya están escritos y describen exactamente qué debe hacer tu código. Tu objetivo es conseguir que todos pasen en verde.

```bash
mvn test          # ejecuta todos los tests y genera el informe de cobertura
```

Al principio verás errores de compilación. Eso es normal: los tests no pueden compilar hasta que implementes la clase `Book`. Ese es precisamente el primer ejercicio.

Cada vez que completes un ejercicio, más tests pasarán a verde. El informe de cobertura se genera automáticamente en:

```
target/site/jacoco/index.html
```

Ábrelo en el navegador para ver qué porcentaje de tu código está cubierto por tests.

---

## Ejercicios

### Ejercicio 1 — Clase `Book`

**Fichero:** `src/main/java/es/ing/tomillo/library/model/Book.java`

Implementa los atributos, el constructor y los métodos de la clase `Book`:

- **Atributos:** `titulo` (String), `autor` (String), `isbn` (String), `disponible` (boolean)
- **Constructor:** `Book(String titulo, String autor, String isbn)` — el libro debe crearse disponible por defecto
- **Getters y setters** para todos los atributos
- **`toString()`** que muestre título, autor e ISBN
- **`equals()`** que compare libros por ISBN (dos libros son el mismo si tienen el mismo ISBN)

> **Pista:** cuando termines este ejercicio, el proyecto compilará. Descomenta también las líneas indicadas en `SampleData.java` y en `Library.loadSampleData()` para cargar los libros de ejemplo.

Tests que deben pasar: `bookConstructorSetsAllFields`, `bookIsAvailableByDefault`, `bookSettersWork`, `bookToStringContainsTitleAndAuthor`, `bookEqualsUsesIsbn`, `booksWithDifferentIsbnAreNotEqual`

---

### Ejercicio 2 — Clase `User`: préstamos y devoluciones

**Fichero:** `src/main/java/es/ing/tomillo/library/model/User.java`

Los métodos `borrowBook` y `returnBook` ya están implementados. Analízalos y entiende cómo funcionan. Fíjate en:

- El límite máximo de libros prestados (`MAX_BORROWED_BOOKS = 5`)
- Las excepciones `BookNotAvailableException` y `MaxBorrowedBooksException` que se lanzan en lugar de imprimir mensajes
- Qué hace `returnBook` cuando el libro no estaba prestado al usuario

Luego implementa el método `reserveBook`:

```java
// Debe permitir al usuario reservar un libro que no está disponible (ya está prestado).
// Añádelo a una lista de reservas del usuario.
public void reserveBook(Book book) { ... }
```

Tests que deben pasar: `userBorrowBookAddsToListAndSetsUnavailable`, `userReturnBookRemovesFromListAndRestoresAvailability`, `userCannotBorrowUnavailableBook`, `userCannotExceedMaxBorrowedBooks`

---

### Ejercicio 3 — Clase `Library`: gestión central

**Fichero:** `src/main/java/es/ing/tomillo/library/service/Library.java`

Implementa el método que falta:

```java
public void addBook(Book book) {
    // TODO: añadir el libro a la lista 'books'
}
```

Los métodos `borrowBook` y `returnBook` ya delegan en `User` — revisa que los entiendes.

Tests que deben pasar: `libraryAddBookAndFindByTitle`, `libraryBorrowBookChangesState`, `libraryReturnBookRestoresState`

---

### Ejercicio 4 — Tus propios tests

**Fichero:** `src/test/java/es/ing/tomillo/library/LibraryTest.java`

Añade al menos **3 tests propios** que cubran casos que consideres importantes y que no estén ya cubiertos. Algunos ejemplos:

- ¿Qué pasa si se intenta devolver un libro que el usuario no tiene prestado?
- ¿Qué ocurre con `listAvailableBooks()` cuando no hay libros disponibles?
- ¿Puede un mismo usuario pedir prestado el mismo libro dos veces?

---

### Ejercicio 5 — Búsqueda de libros

**Fichero:** `src/main/java/es/ing/tomillo/library/service/Library.java`

Implementa los métodos de búsqueda:

```java
public Book searchBookByTitle(String title) { ... }
public Book searchBookByAuthor(String author) { ... }
```

La búsqueda debe ser **insensible a mayúsculas** (`"don quijote"` debe encontrar `"Don Quijote"`).

Tests que deben pasar: `searchByTitleReturnsCorrectBook`, `searchByTitleReturnsNullWhenNotFound`, `searchByAuthorReturnsCorrectBook`, `searchByAuthorReturnsNullWhenNotFound`

---

### Ejercicio 6 — Reserva de libros (ampliación)

Actualmente `User.reserveBook` está pendiente de implementar. Diseña e implementa un sistema de reservas:

- Un usuario puede reservar un libro que esté prestado
- Cuando el libro se devuelve, el sistema debe notificar (o marcar) que hay una reserva pendiente
- Escribe los tests correspondientes

---

### Ejercicio 7 — Desafío avanzado: persistencia con JDBC

**Fichero:** `src/main/java/es/ing/tomillo/library/service/LibraryJDBC.java`

Implementa la persistencia de libros usando JDBC con una base de datos H2 en memoria. El esqueleto ya está creado con los TODOs que guían la implementación.

Añade la dependencia H2 en `pom.xml` (instrucciones dentro del fichero `LibraryJDBC.java`) y completa los métodos:

- `createTable()` — crea la tabla `BOOKS`
- `save(Book book)` — inserta o actualiza un libro
- `findAll()` — recupera todos los libros
- `findByTitle(String title)` — busca por título
- `delete(String isbn)` — elimina un libro por ISBN

---

## Ejecutar la aplicación

Una vez completados los ejercicios, puedes arrancar el menú interactivo:

```bash
mvn compile
mvn exec:java -Dexec.mainClass=es.ing.tomillo.library.service.LibraryCLI
```

O directamente desde tu IDE ejecutando la clase `LibraryCLI`.

---

## Estructura del proyecto

```
src/
├── main/java/es/ing/tomillo/library/
│   ├── model/
│   │   ├── Book.java          ← ejercicio 1
│   │   └── User.java          ← ejercicio 2
│   ├── service/
│   │   ├── Library.java       ← ejercicios 3 y 5
│   │   ├── LibraryCLI.java    ← menú interactivo (no modificar)
│   │   └── LibraryJDBC.java   ← ejercicio 7 (avanzado)
│   ├── exception/
│   │   ├── BookNotAvailableException.java
│   │   └── MaxBorrowedBooksException.java
│   └── util/
│       └── SampleData.java    ← datos de ejemplo
└── test/java/es/ing/tomillo/library/
    └── LibraryTest.java       ← ejercicios 4 y validación del resto
```

---

## Dudas

Contacta con el profesor en rafaelsua@gmail.com
