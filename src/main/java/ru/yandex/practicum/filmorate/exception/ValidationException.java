package ru.yandex.practicum.filmorate.exception;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.01.2024
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
