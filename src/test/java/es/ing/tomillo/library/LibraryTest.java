package es.ing.tomillo.library;

import es.ing.tomillo.library.exception.BookNotAvailableException;
import es.ing.tomillo.library.exception.MaxBorrowedBooksException;
import es.ing.tomillo.library.model.Book;
import es.ing.tomillo.library.model.User;
import es.ing.tomillo.library.service.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;
    private User user;

    @BeforeEach
    void setUp() {
        library = new Library();
        book1 = new Book("Don Quijote", "Cervantes", "111-111");
        book2 = new Book("1984", "Orwell", "222-222");
        user = new User("Alice", 99);
        library.addBook(book1);
        library.addBook(book2);
        library.addUser(user);
    }

    // -------------------------------------------------------------------------
    // Ejercicio 1 — clase Book
    // -------------------------------------------------------------------------

    @Test
    void bookConstructorSetsAllFields() {
        assertEquals("Don Quijote", book1.getTitle());
        assertEquals("Cervantes", book1.getAuthor());
        assertEquals("111-111", book1.getIsbn());
    }

    @Test
    void bookIsAvailableByDefault() {
        assertTrue(book1.isAvailable(), "Un libro recién creado debe estar disponible");
    }

    @Test
    void bookSettersWork() {
        book1.setTitle("Nuevo título");
        assertEquals("Nuevo título", book1.getTitle());

        book1.setAvailable(false);
        assertFalse(book1.isAvailable());
    }

    @Test
    void bookToStringContainsTitleAndAuthor() {
        String s = book1.toString();
        assertTrue(s.contains("Don Quijote"), "toString debe contener el título");
        assertTrue(s.contains("Cervantes"), "toString debe contener el autor");
    }

    @Test
    void bookEqualsUsesIsbn() {
        Book copia = new Book("Otro título", "Otro autor", "111-111");
        assertEquals(book1, copia, "Dos libros con el mismo ISBN deben ser iguales");
    }

    @Test
    void booksWithDifferentIsbnAreNotEqual() {
        assertNotEquals(book1, book2);
    }

    // -------------------------------------------------------------------------
    // Ejercicio 2 — clase User: borrowBook / returnBook
    // -------------------------------------------------------------------------

    @Test
    void userBorrowBookAddsToListAndSetsUnavailable() {
        user.borrowBook(book1);

        assertEquals(1, user.getBorrowedBooks().size());
        assertFalse(book1.isAvailable());
    }

    @Test
    void userReturnBookRemovesFromListAndRestoresAvailability() {
        user.borrowBook(book1);
        user.returnBook(book1);

        assertTrue(user.getBorrowedBooks().isEmpty());
        assertTrue(book1.isAvailable());
    }

    @Test
    void userCannotBorrowUnavailableBook() {
        user.borrowBook(book1); // book1 pasa a no disponible

        User otherUser = new User("Bob", 100);
        assertThrows(BookNotAvailableException.class, () -> otherUser.borrowBook(book1),
                "Intentar prestar un libro no disponible debe lanzar BookNotAvailableException");
    }

    @Test
    void userCannotExceedMaxBorrowedBooks() {
        for (int i = 0; i < 5; i++) {
            user.borrowBook(new Book("Libro " + i, "Autor", "isbn-" + i));
        }

        Book extraBook = new Book("Extra", "Autor", "isbn-extra");
        assertThrows(MaxBorrowedBooksException.class, () -> user.borrowBook(extraBook),
                "Superar el límite de 5 libros debe lanzar MaxBorrowedBooksException");
    }

    // -------------------------------------------------------------------------
    // Ejercicio 3 — clase Library: addBook, borrowBook, returnBook
    // -------------------------------------------------------------------------

    @Test
    void libraryAddBookAndFindByTitle() {
        assertEquals(book1, library.searchBookByTitle("Don Quijote"));
    }

    @Test
    void libraryBorrowBookChangesState() {
        library.borrowBook(user, book1);

        assertFalse(book1.isAvailable());
        assertTrue(user.getBorrowedBooks().contains(book1));
    }

    @Test
    void libraryReturnBookRestoresState() {
        library.borrowBook(user, book1);
        library.returnBook(user, book1);

        assertTrue(book1.isAvailable());
        assertTrue(user.getBorrowedBooks().isEmpty());
    }

    // -------------------------------------------------------------------------
    // Ejercicio 5 — búsqueda por título y autor
    // -------------------------------------------------------------------------

    @Test
    void searchByTitleReturnsCorrectBook() {
        assertEquals(book2, library.searchBookByTitle("1984"));
    }

    @Test
    void searchByTitleReturnsNullWhenNotFound() {
        assertNull(library.searchBookByTitle("Libro inexistente"));
    }

    @Test
    void searchByAuthorReturnsCorrectBook() {
        assertEquals(book1, library.searchBookByAuthor("Cervantes"));
    }

    @Test
    void searchByAuthorReturnsNullWhenNotFound() {
        assertNull(library.searchBookByAuthor("Autor inexistente"));
    }
}
