package com.ildan.testing.opencode.repositiry;

import com.ildan.testing.opencode.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRep extends JpaRepository<Question, Long> {
}