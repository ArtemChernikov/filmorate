package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.01.2024
 */
@Slf4j
public class ValidateService {
    public static final Integer MAX_DESCRIPTION_FILM_LENGTH = 200;

    private ValidateService() {
    }

    public static void validateFilm(Film film) {
        String name = film.getName();
        if (name == null || name.isBlank()) {
            log.warn("Film name is empty: {}", film);
            throw new ValidationException("Film name is empty or null.");
        }
        if (film.getDescription().length() > MAX_DESCRIPTION_FILM_LENGTH) {
            log.warn("Film description max length is 200: {}", film);
            throw new ValidationException("Film description max length must be 200");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.warn("Film release date is before 28.12.1895: {}", film);
            throw new ValidationException("Film release date is before 28.12.1895");
        }
        if (film.getDuration() < 0) {
            log.warn("Film duration is negative: {}", film);
            throw new ValidationException("Film duration is negative");
        }
    }

    public static void validateUser(User user) {
        String email = user.getEmail();
        String login = user.getLogin();
        String name = user.getName();
        if (email == null || email.isBlank() || !email.contains("@")) {
            log.warn("Not correct user's email: {}", user);
            throw new ValidationException("Not correct user's email.");
        }
        if (login == null || login.isBlank() || login.contains(" ")) {
            log.warn("Not correct user's login: {}", user);
            throw new ValidationException("Not correct user's login.");
        }
        if (name == null || name.isBlank()) {
            log.warn("User's name is empty: {}", user);
            user.setName(login);
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            log.warn("Birthday is after: {}", user);
            throw new ValidationException("Birthday is after.");
        }
    }
}
