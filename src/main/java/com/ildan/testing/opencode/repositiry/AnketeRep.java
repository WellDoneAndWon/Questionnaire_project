package com.ildan.testing.opencode.repositiry;

import com.ildan.testing.opencode.model.entity.Ankete;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AnketeRep extends JpaRepository<Ankete, Long> {
}