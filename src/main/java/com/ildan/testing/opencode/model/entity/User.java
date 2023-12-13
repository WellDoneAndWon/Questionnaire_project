package com.ildan.testing.opencode.model.entity;

import com.ildan.testing.opencode.model.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Column(name = "enabled")
    private boolean isActive;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ankete", fetch = FetchType.EAGER)
    private List<History> histories;

    public User(String username, String password, Role role, boolean isActive) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }

    public void addHistory(History history) {
        if (histories == null) histories = new ArrayList<>();
        histories.add(history);
        history.setUser(this);
    }
}