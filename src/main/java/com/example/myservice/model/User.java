package com.example.myservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    private boolean admin;
}
