package com.ildan.testing.opencode.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "question")
    private String textQuestion;

    private boolean answer;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ankete_id")
    private Ankete ankete;

    public Question() {
        answer = false;
    }
}
