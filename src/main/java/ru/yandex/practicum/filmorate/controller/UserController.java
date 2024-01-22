package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.UserRepository;
import ru.yandex.practicum.filmorate.service.ValidateService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody User user) {
        log.info("Create user: {}", user);
        ValidateService.validateUser(user);
        return userRepository.save(user);
    }

    @PutMapping
    public User update(@Valid @RequestBody User user) {
        log.info("Update user: {}", user);
        ValidateService.validateUser(user);
        return userRepository.update(user);
    }

    @GetMapping
    public List<User> findAll() {
        log.info("Get all users");
        return userRepository.findAll();
    }
}
