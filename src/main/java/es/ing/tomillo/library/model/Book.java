package es.ing.tomillo.library.model;

import java.util.Objects;

public class Book {
    // TODO: Implementar los atributos según el ejercicio 1
    // - titulo (String)
    // - autor (String)
    // - isbn (String)
    // - disponible (boolean)
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;

    // TODO: Implementar constructor según el ejercicio 1
    public Book(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }
    public String getTitle() {
        return this.titulo;
    }
    public void setTitle(String titulo) {
        this.titulo = titulo;
    }
    public String getAuthor() {
        return this.autor;
    }
    public void setAuthor(String autor) {
        this.autor = autor;
    }
    public String getIsbn() {
        return this.isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public boolean isAvailable() {
        return disponible;
    }
    public void setAvailable(boolean disponible) {
        this.disponible = disponible;
    }
    // TODO: Implementar getters y setters según el ejercicio 1

    // TODO: Implementar método toString según el ejercicio 1

    // TODO: Implementar método equals para comparar libros por ISBN

}

