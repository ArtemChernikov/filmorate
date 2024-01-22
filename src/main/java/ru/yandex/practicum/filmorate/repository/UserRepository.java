package ru.yandex.practicum.filmorate.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

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
public class UserRepository {
    private long ids;
    private final Map<Long, User> users = new HashMap<>();

    public long generateId() {
        return ++ids;
    }

    public User save(User user) {
        long id = generateId();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    public User update(User user) {
        Long id = user.getId();
        if (users.containsKey(id)) {
            users.put(id, user);
            log.info("Update user: {}", user);
        } else {
            throw new IllegalArgumentException("User not exist");
        }
        return user;
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
