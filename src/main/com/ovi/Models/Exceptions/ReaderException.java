package main.com.ovi.Models.Exceptions;

public class ReaderException extends Exception {
    public ReaderException(String message) {
        super(message);
    }

    public ReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
