package com.example.demo.controllers;

import com.example.demo.config.MyUserDetails;
import com.example.demo.domain.FileEntity;
import com.example.demo.domain.User;
import com.example.demo.repository.FileRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class BackendController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private FileRepository fileRepository;

    // Добавление курса пользователю
    @PutMapping("/course/add/{id}")
    public void addCourse(@PathVariable Long id) throws IOException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof MyUserDetails) {
            username = ((MyUserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user =  userRepository.findByUsername(username).orElse(null);
        FileEntity file = fileRepository.findById(String.valueOf(id)).orElse(null);
        Set<User> newSet = file.getUsersInCourse();
        newSet.add(user);
        file.setUsersInCourse(newSet);
        fileRepository.save(file);
    }
    // Обновление информации о пользователе
    @PutMapping("/update")
    public void updateUser(@RequestBody String jsonUser) throws JsonProcessingException{
        User user = jsonStringToUser(jsonUser);
        if (userRepository.findById(user.getId()).isPresent()){
            User newUser = userRepository.findById(user.getId()).get();
            newUser.setFirstName(user.getFirstName());
            newUser.setSecondName(user.getSecondName());
            newUser.setPhoneNumber(user.getPhoneNumber());
            newUser.setEmail(user.getEmail());
            newUser.setRole(user.getRole());
            userRepository.save(newUser);
        }
    }

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

    // Получение пользователя, обработка и отправка для отображения ифнормации на профиле
    @GetMapping("/getProfile")
    public String profile() throws JsonProcessingException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof MyUserDetails) {
             username = ((MyUserDetails)principal).getUsername();
        } else {
             username = principal.toString();
        }
        User user =  userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            return userProfileToJson(user);
        }
        else return "null";
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
    // Преобразование объекта User в json
    private String userProfileToJson(User user) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        user.setPassword(null);
        user.setFiles(null);
        String jsonUserProfile = objectMapper.writeValueAsString(user);
        return jsonUserProfile;
    }
}
