package es.ing.tomillo.library.model;

import java.time.LocalDate;

public class Loan {

    // TODO: Ejercicio 7 — Añadir los atributos:
    //   - book (Book)        el libro prestado
    //   - user (User)        el usuario que lo tiene
    //   - loanDate (LocalDate)   día en que se realizó el préstamo
    //   - dueDate (LocalDate)    fecha límite de devolución (14 días después de loanDate)

    // TODO: Ejercicio 7 — Implementar el constructor Loan(Book book, User user, LocalDate loanDate)
    //   En el constructor calcula automáticamente dueDate = loanDate.plusDays(14)
    public Loan(Book book, User user, LocalDate loanDate) {
    }

    // TODO: Ejercicio 7 — Añadir getters: getBook(), getUser(), getLoanDate(), getDueDate()

    // TODO: Ejercicio 7 — isOverdue() devuelve true si hoy es posterior a dueDate
    //   Pista: usa LocalDate.now().isAfter(dueDate)
    public boolean isOverdue() {
        return false;
    }
}
