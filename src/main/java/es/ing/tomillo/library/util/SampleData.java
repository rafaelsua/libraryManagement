package es.ing.tomillo.library.util;

import es.ing.tomillo.library.model.Book;
import es.ing.tomillo.library.model.User;

import java.util.Arrays;
import java.util.List;

public class SampleData {
    
    // TODO: Ejercicio 1 - descomenta todas las líneas de SAMPLE_BOOKS una vez implementes el constructor de Book
    //   y descomenta también la línea books.addAll(SampleData.SAMPLE_BOOKS) en Library.loadSampleData()
    public static final List<Book> SAMPLE_BOOKS = Arrays.asList(
        new Book("El Quijote", "Miguel de Cervantes", "978-84-376-0494-7"),
        new Book("Cien años de soledad", "Gabriel García Márquez", "978-84-376-0494-8"),
        new Book("1984", "George Orwell", "978-84-376-0494-9"),
        new Book("El señor de los anillos", "J.R.R. Tolkien", "978-84-376-0494-0"),
        new Book("Orgullo y prejuicio", "Jane Austen", "978-84-376-0494-1"),
        new Book("Crimen y castigo", "Fiódor Dostoyevski", "978-84-376-0494-2"),
        new Book("El principito", "Antoine de Saint-Exupéry", "978-84-376-0494-3"),
        new Book("Don Juan Tenorio", "José Zorrilla", "978-84-376-0494-4"),
        new Book("La sombra del viento", "Carlos Ruiz Zafón", "978-84-376-0494-5"),
        new Book("El código Da Vinci", "Dan Brown", "978-84-376-0494-6")
    );

    public static final List<User> SAMPLE_USERS = Arrays.asList(
        new User("Juan Pérez", 1),
        new User("María García", 2),
        new User("Carlos López", 3),
        new User("Ana Martínez", 4),
        new User("Pedro Sánchez", 5),
        new User("Laura Fernández", 6),
        new User("David González", 7),
        new User("Sofía Rodríguez", 8),
        new User("Javier Díaz", 9),
        new User("Elena Ruiz", 10)
    );

} 