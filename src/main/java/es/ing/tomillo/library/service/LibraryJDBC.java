package es.ing.tomillo.library.service;

import es.ing.tomillo.library.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Desafío avanzado (ejercicio 7) — persistencia con JDBC.
 *
 * Para usar H2 en memoria, añade esta dependencia en pom.xml:
 *
 *   <dependency>
 *       <groupId>com.h2database</groupId>
 *       <artifactId>h2</artifactId>
 *       <version>2.2.224</version>
 *   </dependency>
 *
 * URL de conexión H2 en memoria: jdbc:h2:mem:library;DB_CLOSE_DELAY=-1
 */
public class LibraryJDBC {

    private final Connection connection;

    // TODO: Ejercicio 7 - inicializar la conexión JDBC y llamar a createTable()
    public LibraryJDBC(String jdbcUrl) throws SQLException {
        this.connection = DriverManager.getConnection(jdbcUrl);
        createTable();
    }

    // TODO: Ejercicio 7 - crear la tabla BOOKS si no existe:
    //   isbn VARCHAR PRIMARY KEY, title VARCHAR, author VARCHAR, available BOOLEAN
    private void createTable() throws SQLException {
    }

    // TODO: Ejercicio 7 - insertar o actualizar un libro en la base de datos (INSERT OR REPLACE)
    public void save(Book book) throws SQLException {
    }

    // TODO: Ejercicio 7 - recuperar todos los libros de la tabla
    public List<Book> findAll() throws SQLException {
        return new ArrayList<>();
    }

    // TODO: Ejercicio 7 - buscar un libro por título (SELECT ... WHERE title = ?)
    public Book findByTitle(String title) throws SQLException {
        return null;
    }

    // TODO: Ejercicio 7 - eliminar un libro por ISBN (DELETE ... WHERE isbn = ?)
    public void delete(String isbn) throws SQLException {
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
