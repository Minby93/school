package com.example.demo.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="usr") // Использую аннотацию, чтобы записать таблицу с название usr

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String username; // логин
    private String phoneNumber;
    private String password;
    private String role;
    @ManyToMany
    @JoinTable(
            name = "user_courses",
            inverseJoinColumns = @JoinColumn(name = "file_id"),
            joinColumns = @JoinColumn(name = "user_id")
    )
    private Set<FileEntity> files = new HashSet<>();

    public User(Long id, String firstName, String secondName, String email, String username, String phoneNumber, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public User(User user) {
        this.id = user.id;
        this.firstName = user.firstName;
        this.secondName = user.secondName;
        this.email = user.email;
        this.username = user.username;
        this.phoneNumber = user.phoneNumber;
        this.password = user.password;
        this.role = user.role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public Set<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(Set<FileEntity> files) {
        this.files = files;
    }
}
