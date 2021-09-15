package main.com.ovi.Models.Exceptions;

public class WriterException extends Exception {
    public WriterException(String message) {
        super(message);
    }

    public WriterException(String message, Throwable cause) {
        super(message, cause);
    }
}
