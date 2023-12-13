package com.ildan.testing.opencode.repositiry;

import com.ildan.testing.opencode.model.entity.StartStopResult;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StartStopResultRep extends JpaRepository<StartStopResult, Long> {
}