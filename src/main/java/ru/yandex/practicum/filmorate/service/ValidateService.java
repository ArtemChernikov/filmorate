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
            log.warn("Название фильма не заполнено: {}", film);
            throw new ValidationException("Название фильма пустое.");
        }
        if (film.getDescription().length() > MAX_DESCRIPTION_FILM_LENGTH) {
            log.warn("Максимальная длина описания 200 символов: {}", film);
            throw new ValidationException("Максимальная длина описания 200 символов");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.warn("Дата производства фильма не может быть раньше чем 28.12.1895 г.: {}", film);
            throw new ValidationException("Дата производства фильма не может быть раньше чем 28.12.1895 г.");
        }
        if (film.getDuration() < 0) {
            log.warn("Продолжительность фильма не может быть отрицательной: {}", film);
            throw new ValidationException("Продолжительность фильма не может быть отрицательной");
        }
    }

    public static void validateUser(User user) {
        String email = user.getEmail();
        String login = user.getLogin();
        String name = user.getName();
        if (email == null || email.isBlank() || !email.contains("@")) {
            log.warn("Некорректный email адрес: {}", user);
            throw new ValidationException("Некорректный email адрес.");
        }
        if (login == null || login.isBlank() || login.contains(" ")) {
            log.warn("Некорректный логин пользователя: {}", user);
            throw new ValidationException("Некорректный логин пользователя.");
        }
        if (name == null || name.isBlank()) {
            log.warn("Имя пользователя не заполнено: {}", user);
            user.setName(login);
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            log.warn("День рождения не может быть позже текущей даты: {}", user);
            throw new ValidationException("День рождения не может быть позже текущей даты.");
        }
    }
}
