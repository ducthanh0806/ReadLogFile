package com.example.ReadLogFile.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Setter
@Getter
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLES")
    private String roles;

    @Column(name = "EMAIL")
    private String email;
}
