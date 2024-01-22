package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.repository.FilmRepository;
import ru.yandex.practicum.filmorate.service.ValidateService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
@Slf4j
public class FilmController {
    private final FilmRepository filmRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film create(@Valid @RequestBody Film film) {
        log.info("Create film: {}", film);
        ValidateService.validateFilm(film);
        return filmRepository.save(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        log.info("Update film: {}", film);
        ValidateService.validateFilm(film);
        return filmRepository.update(film);
    }

    @GetMapping
    public List<Film> findAll() {
        log.info("Get all films");
        return filmRepository.findAll();
    }
}
