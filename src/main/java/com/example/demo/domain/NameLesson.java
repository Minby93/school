package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NameLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;


    public String getString() {
        return name;
    }

    public void setString(String name) {
        this.name = name;
    }

    public NameLesson(String name) {
        this.name = name;
    }

    public NameLesson() {
    }

    @Override
    public String toString() {
        return "NameLesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
