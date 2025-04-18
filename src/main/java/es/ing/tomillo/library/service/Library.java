package es.ing.tomillo.library.service;

import es.ing.tomillo.library.model.Book;
import es.ing.tomillo.library.model.User;
import es.ing.tomillo.library.util.SampleData;

import java.util.Scanner;

public class Library {
    // TODO: Implementar los atributos según el ejercicio 3
    // - libros (Array de Libro)
    // - usuarios (Array de Usuario)
    // - contadorLibros (int)
    // - contadorUsuarios (int)
    private final Book[] books;
    private final User[] users;
    private int bookCount;
    private int userCount;

    // TODO: Implementar constructor según el ejercicio 3
    public Library() {
        this.books = new Book[100]; // Maximum 100 books
        this.users = new User[50]; // Maximum 50 users
        this.bookCount = 0;
        this.userCount = 0;
        
        // Cargar datos de ejemplo
        loadSampleData();
    }

    private void loadSampleData() {
        // Cargar libros de ejemplo
        for (Book book : SampleData.SAMPLE_BOOKS) {
            if (bookCount < books.length) {
                books[bookCount] = book;
                bookCount++;
            }
        }

        // Cargar usuarios de ejemplo
        for (User user : SampleData.SAMPLE_USERS) {
            if (userCount < users.length) {
                users[userCount] = user;
                userCount++;
            }
        }

        System.out.println("Datos de ejemplo cargados:");
        System.out.println("- " + bookCount + " libros");
        System.out.println("- " + userCount + " usuarios");
    }

    // TODO: Implementar método añadirLibro según el ejercicio 3
    public void addBook(Book book) {
        if (bookCount < books.length) {
            books[bookCount] = book;
            bookCount++;
        } else {
            System.out.println("ERROR: No se pueden añadir más libros. Límite alcanzado.");
        }
    }

    // TODO: Implementar método añadirUsuario según el ejercicio 3
    public void addUser(User user) {
        if (userCount < users.length) {
            users[userCount] = user;
            userCount++;
        } else {
            System.out.println("ERROR: No se pueden añadir más usuarios. Límite alcanzado.");
        }
    }

    // TODO: Implementar método prestarLibro según el ejercicio 3
    public void borrowBook(User user, Book book) {
        user.borrowBook(book);
    }

    // TODO: Implementar método devolverLibro según el ejercicio 3
    public void returnBook(User user, Book book) {
        user.returnBook(book);
    }

    // TODO: Implementar método buscarLibroPorTitulo según el ejercicio 4
    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book != null && book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // TODO: Implementar método buscarLibroPorAutor según el ejercicio 4
    public Book searchBookByAuthor(String author) {
        for (Book book : books) {
            if (book != null && book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    // TODO: Implementar método listarLibrosDisponibles según el ejercicio 5
    // Debe mostrar por pantalla todos los libros que están disponibles (isAvailable = true)
    public void listAvailableBooks() {
        // TODO: Implementar la lógica para listar los libros disponibles
    }

    // TODO: Implementar método listarUsuarios según el ejercicio 5
    // Debe mostrar por pantalla todos los usuarios registrados en la biblioteca
    public void listUsers() {
        // TODO: Implementar la lógica para listar los usuarios
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String title;
        String isbn;
        Book book = null;
        User user = null;
        int id=0;

        while (!exit) {
            System.out.println("Menu Options:");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Search Book by Author");
            System.out.println("7. List Available Books");
            System.out.println("8. List Users");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    isbn = scanner.nextLine();
                    book = new Book(title, author, isbn);
                    library.addBook(book);
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    id = scanner.nextInt();
                    user = new User(name, id);
                    library.addUser(user);
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    user = library.users[id];
                    book = library.searchBookByTitle(title);
                    if (user != null && book != null) {
                        library.borrowBook(user, book);
                    } else {
                        System.out.println("User or book not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter user ID: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    user = library.users[id];
                    book = library.searchBookByTitle(title);
                    if (user != null && book != null) {
                        library.returnBook(user, book);
                    } else {
                        System.out.println("User or book not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter book title: ");
                    title = scanner.nextLine();
                    book = library.searchBookByTitle(title);
                    if (book != null) {
                        System.out.println(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter book author: ");
                    author = scanner.nextLine();
                    book = library.searchBookByTitle(author);
                    if (book != null) {
                        System.out.println(book);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 7:
                    library.listAvailableBooks();
                    break;
                case 8:
                    library.listUsers();
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}

