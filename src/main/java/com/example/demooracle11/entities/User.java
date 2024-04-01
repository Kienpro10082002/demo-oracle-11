package com.example.demooracle11.entities;

import lombok.*;

import javax.persistence.*;

@Entity ()
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "dbuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
