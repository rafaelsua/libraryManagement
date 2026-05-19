package es.ing.tomillo.library.exception;

public class MaxBorrowedBooksException extends RuntimeException {
    public MaxBorrowedBooksException(String message) {
        super(message);
    }
}
