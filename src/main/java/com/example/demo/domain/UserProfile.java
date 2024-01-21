package com.example.demo.domain;

public class UserProfile {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String username;
    private String phoneNumber;

    public UserProfile(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.phoneNumber = user.getPhoneNumber();
    }
    public UserProfile(){

    }

}
