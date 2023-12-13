package com.ildan.testing.opencode.repositiry;

import com.ildan.testing.opencode.model.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRep  extends JpaRepository<History, Long> {
}