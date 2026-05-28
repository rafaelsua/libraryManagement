package es.ing.tomillo.library.model;

import java.util.Objects;

public class Book {

    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;

    public Book(String titulo, String autor, String isbn) {
        this.titulo     = titulo;
        this.autor      = autor;
        this.isbn       = isbn;
        this.disponible = true;
    }

    // Métodos en español (usados en User.java y Library.java)
    public String getTitulo()             { return titulo; }
    public String getAutor()              { return autor; }
    public String getIsbn()               { return isbn; }
    public boolean isDisponible()         { return disponible; }
    public void setTitulo(String titulo)  { this.titulo = titulo; }
    public void setAutor(String autor)    { this.autor = autor; }
    public void setIsbn(String isbn)      { this.isbn = isbn; }
    public void setDisponible(boolean b)  { this.disponible = b; }

    // Alias en inglés (usados por los tests)
    public String getTitle()             { return titulo; }
    public String getAuthor()            { return autor; }
    public boolean isAvailable()         { return disponible; }
    public void setTitle(String titulo)  { this.titulo = titulo; }
    public void setAvailable(boolean b)  { this.disponible = b; }

    @Override
    public String toString() {
        return "Book{titulo='" + titulo + "', autor='" + autor + "', isbn='" + isbn + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}

