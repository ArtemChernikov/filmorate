package ru.yandex.practicum.filmorate.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.01.2024
 */
@Component
@Slf4j
public class FilmRepository {
    private long ids;
    private final Map<Long, Film> films = new HashMap<>();

    public long generateId() {
        return ++ids;
    }

    public Film save(Film film) {
        long id = generateId();
        film.setId(id);
        films.put(id, film);
        return film;
    }

    public Film update(Film film) {
        Long id = film.getId();
        if (films.containsKey(id)) {
            films.put(id, film);
            log.info("Update film: {}", film);
        } else {
            throw new IllegalArgumentException("Film not exist");
        }
        return film;
    }

    public List<Film> findAll() {
        return new ArrayList<>(films.values());
    }
}
