package com.example.demo.controllers;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BackendController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


  // Обработка POST запроса и сохранение объекта User в БД
    @PostMapping("/add")
    public void processFormData(@RequestBody String jsonUser) throws JsonProcessingException {
        User user = jsonStringToUser(jsonUser);  // Получаю объект User из json
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user);
        userRepository.save(user);
    }
    // Отправка списка объектов User на фронт в json формате
    @GetMapping("/listOfUsers")
    public String listOfUsers() throws JsonProcessingException {
        List<User> users = userRepository.findAll();
        return usersToJson(users);  // Возвращаю список объектов User в виде json
    }
    // Преобразование json в объект User
    private User jsonStringToUser(String jsonUser) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(jsonUser, User.class);
        return user;
    }
    // Преобразование списка объектов User в json
    private String usersToJson(List<User> users) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUsers = objectMapper.writeValueAsString(users);
        return jsonUsers;
    }
}
