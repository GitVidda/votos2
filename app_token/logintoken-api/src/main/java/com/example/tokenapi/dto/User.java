package com.example.tokenapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String user;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
    @Builder.Default
    private ZonedDateTime registrationDate = ZonedDateTime.now();
}
