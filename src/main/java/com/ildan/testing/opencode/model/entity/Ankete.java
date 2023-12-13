package com.ildan.testing.opencode.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ankete {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "ankete", fetch = FetchType.LAZY)
    private List<Question> questions;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "ankete", fetch = FetchType.EAGER)
    private List<History> histories;

    public void addQuestion(Question question) {
        if (questions == null) questions = new ArrayList<>();
        questions.add(question);
        question.setAnkete(this);
    }

    public void removeQuestionToAnkate(Question question) {
        if (questions == null) return;

        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getId().equals(question.getId())) {
                questions.remove(i);
            }
        }
    }

    public void addHistory(History history) {
        if (histories == null) histories = new ArrayList<>();
        histories.add(history);
        history.setAnkete(this);
    }
}