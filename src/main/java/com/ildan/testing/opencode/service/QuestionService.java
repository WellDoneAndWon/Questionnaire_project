package com.ildan.testing.opencode.service;

import com.ildan.testing.opencode.model.entity.Ankete;
import com.ildan.testing.opencode.model.entity.Question;
import com.ildan.testing.opencode.repositiry.AnketeRep;
import com.ildan.testing.opencode.repositiry.QuestionRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final AnketeRep anketeRep;
    private final QuestionRep questionRep;

    public Question findById(Long id) {
        return questionRep.findById(id).get();
    }

    public void delete(Long idQuestion, Long idAnkate) {
        Question question = questionRep.getById(idQuestion);
        Ankete ankete = anketeRep.getById(idAnkate);
        ankete.removeQuestionToAnkate(question);
        questionRep.delete(question);
    }

    public void updateQuestion(Long idQuestion, String newTextQuestion) {
        Question question = questionRep.getById(idQuestion);
        question.setTextQuestion(newTextQuestion);
        questionRep.save(question);
    }

    public void addQuestion(Long idAnkate, String textQuestion) {
        Question question = new Question();
        question.setTextQuestion(textQuestion);
        Ankete ankete = anketeRep.getById(idAnkate);
        ankete.addQuestion(question);
        anketeRep.save(ankete);
    }
}