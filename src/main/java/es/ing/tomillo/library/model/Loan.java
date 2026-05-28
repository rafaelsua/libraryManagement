package es.ing.tomillo.library.model;

import java.time.LocalDate;

public class Loan {

    // TODO: Ejercicio 7 — Añadir los atributos:
    //   - book (Book)        el libro prestado
    //   - user (User)        el usuario que lo tiene
    //   - loanDate (LocalDate)   día en que se realizó el préstamo
    //   - dueDate (LocalDate)    fecha límite de devolución (14 días después de loanDate)
    private Book book;
    private User user;
    private LocalDate loanDate;
    private LocalDate dueDate;

    // TODO: Ejercicio 7 — Implementar el constructor Loan(Book book, User user, LocalDate loanDate)
    //   En el constructor calcula automáticamente dueDate = loanDate.plusDays(14)
    public Loan(Book book, User user, LocalDate loanDate) {
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.dueDate = loanDate.plusDays(14);
    }

    // TODO: Ejercicio 7 — Añadir getters: getBook(), getUser(), getLoanDate(), getDueDate()
    public Book getBook() {
        return this.book;
    }
    public User getUser() {
        return this.user;
    }
    public LocalDate getLoanDate() {
        return this.loanDate;
    }
    public LocalDate getDueDate() {
        return this.dueDate;
    }
    // TODO: Ejercicio 7 — isOverdue() devuelve true si hoy es posterior a dueDate
    //   Pista: usa LocalDate.now().isAfter(dueDate)
    public boolean isOverdue(){
        return  LocalDate.now().isAfter(this.dueDate);

    }
}
