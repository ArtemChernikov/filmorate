package ru.yandex.practicum.filmorate.service;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ValidateServiceTest {
    @Test
    void whenEmailIsNotValid() {
        User user = new User();
        user.setEmail(" ");
        assertThrows(ValidationException.class, () -> ValidateService.validateUser(user));
    }

    @Test
    void whenLoginIsNotValid() {
        User user = new User();
        user.setEmail("email@list.ru");
        user.setLogin(" ");
        assertThrows(ValidationException.class, () -> ValidateService.validateUser(user));
    }

    @Test
    void whenUserNameIsEmpty() {
        User user = new User();
        user.setEmail("email@list.ru");
        user.setLogin("login");
        user.setName(" ");
        user.setBirthday(LocalDate.now().minusWeeks(2));
        ValidateService.validateUser(user);
        assertThat(user.getName()).isEqualTo(user.getLogin());
    }

    @Test
    void whenBirthdayInFuture() {
        User user = new User();
        user.setEmail("email@list.ru");
        user.setLogin("login");
        user.setName("");
        user.setBirthday(LocalDate.of(2045, 5, 5));
        assertThrows(ValidationException.class, () -> ValidateService.validateUser(user));
    }

    @Test
    void whenFilmNameIsNotValid() {
        Film film = new Film();
        film.setName(" ");
        assertThrows(ValidationException.class, () -> ValidateService.validateFilm(film));
    }

    @Test
    void whenFDescriptionIsNotValid() {
        Film film = new Film();
        film.setName("name film");
        film.setDescription("lkishjfgdkajsdhvashdkvbaslkhvbvdxfgbxcvbxvbxcvbxcvb lkasdhjbvlkalsblsllhsjbksdhvknbsvnb" +
                "ksbv dlkasdhjbvlkalsblsllhsjbksdhvknbsvnbksbvlkasdhjbvlkalsblsllhsjbksdhvknbsvnbksbvlkasdhjbvlkalsbl" +
                "sllhsjbksdhvknbsvnbksbvlkasdhjbvlkalsblsllhsjbksdhvknbsvnbksbvlkasdhjbvlkalsblsllhsjbksdhvknbsvnbks" +
                "bvvbfdgefbgsdfbsdfbsdfbsdgfbsbfdbgdsfbvgf nknxzbv kdfsvzcdldkbjsdkjbgpksdgfpbjsodpkgbjnvkv");
        assertThrows(ValidationException.class, () -> ValidateService.validateFilm(film));
    }

    @Test
    void whenReleaseDateIsNotValid() {
        Film film = new Film();
        film.setName("name film");
        film.setDescription("vgf nknxzbv kdfsvzcdldkbjsdkjbgpksdgfpbjsodpkgbjnvkv");
        film.setReleaseDate(LocalDate.of(1845, 5, 5));
        assertThrows(ValidationException.class, () -> ValidateService.validateFilm(film));
    }

    @Test
    void whenDurationIsNegative() {
        Film film = new Film();
        film.setName("name film");
        film.setDescription("vgf nknxzbv kdfsvzcdldkbjsdkjbgpksdgfpbjsodpkgbjnvkv");
        film.setReleaseDate(LocalDate.of(1945, 5, 5));
        film.setDuration(-2L);
        assertThrows(ValidationException.class, () -> ValidateService.validateFilm(film));
    }

}