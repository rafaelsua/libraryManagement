package es.ing.tomillo.library.model;

import es.ing.tomillo.library.exception.BookNotAvailableException;
import es.ing.tomillo.library.exception.MaxBorrowedBooksException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private String name;
    private int id;
    private final List<Book> borrowedBooks;
    private final List<Book> reservedBooks;        // ← añadida para las reservas
    private static final int MAX_BORROWED_BOOKS = 5;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();    // ← inicializada
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public List<Book> getReservedBooks() {
        return reservedBooks;
    }

    public int getBookCount() {
        return borrowedBooks.size();
    }

    public void borrowBook(Book book) {
        if (!book.isDisponible()) {                          // ← isDisponible(), como en Book.java
            throw new BookNotAvailableException(
                    "El libro '" + book.getTitulo() + "' no está disponible."); // ← getTitulo()
        }
        if (borrowedBooks.size() >= MAX_BORROWED_BOOKS) {
            throw new MaxBorrowedBooksException(
                    "No se pueden prestar más de " + MAX_BORROWED_BOOKS + " libros.");
        }
        borrowedBooks.add(book);
        book.setDisponible(false);  // ← el libro ya no está disponible para otros
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setDisponible(true);  // ← setDisponible(), como en Book.java
        } else {
            System.out.println("Este libro no estaba prestado a este usuario.");
        }
    }

    public void reserveBook(Book book) {
        if (!book.isDisponible()) {
            reservedBooks.add(book);  // ← guarda la reserva en la lista
        }
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', id=" + id +
                ", borrowedBooks=" + borrowedBooks.size() + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;  // dos usuarios son iguales si tienen el mismo id
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
