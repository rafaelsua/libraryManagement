package es.ing.tomillo.library.service;

import es.ing.tomillo.library.model.Book;
import es.ing.tomillo.library.model.User;
import es.ing.tomillo.library.util.SampleData;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private final List<User> users;
    private final List<Book> books;

    public Library() {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
    }

    private void loadSampleData() {
        users.addAll(SampleData.SAMPLE_USERS);
        books.addAll(SampleData.SAMPLE_BOOKS);
        System.out.println("Datos de ejemplo cargados: " + users.size() + " usuarios, " + books.size() + " libros.");
    }
    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    public void listUsers() {
        for (User user : users) {
            System.out.println("ID: " + user.getId());
            System.out.println("Nombre: " + user.getName());
            System.out.println("Número de libros prestados: " + user.getBookCount());
        }
    }
    public void addBook(Book book) {
        books.add(book); // añade el libro a la lista
    }

    public List<Book> getBooks() {
        return books;
    }
    public void borrowBook(User user, Book book) {
        user.borrowBook(book);
    }

    public void returnBook(User user, Book book) {
        user.returnBook(book);
    }
    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitulo().toLowerCase().contains(title.toLowerCase())) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByAuthor(String author) {
        for (Book book : books) {
            if (book.getAutor().toLowerCase().contains(author.toLowerCase())) {
                return book;
            }
        }
        return null;
    }
    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(book -> book.isDisponible())
                .toList();
    }

    public List<Book> searchAllBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAutor().toLowerCase().contains(author.toLowerCase()))
                .toList();
    }
    public void listAvailableBooks() {
        System.out.println("Libros disponibles:");
        for (Book book : books) {
            if (book.isDisponible()) { // ← isDisponible(), no isAvailable()
                System.out.println(book);
            }
        }
    }
}