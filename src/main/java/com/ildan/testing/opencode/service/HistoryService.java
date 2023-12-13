package com.ildan.testing.opencode.service;

import com.ildan.testing.opencode.model.entity.Ankete;
import com.ildan.testing.opencode.model.entity.History;
import com.ildan.testing.opencode.model.entity.User;
import com.ildan.testing.opencode.repositiry.HistoryRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRep historyRep;
    private final AnketeService anketeService;
    private final ClientService clientService;

    public List<History> getHistoriesForAnkate(Long idAnlate) {
        return historyRep
                .findAll()
                .stream()
                .filter(item -> item
                        .getAnkete()
                        .getId()
                        .equals(idAnlate))
                .collect(Collectors.toList());
    }

    public void saveAllHistory(List<String> paramsList, String userName, Long idAnkate) {
        History history = new History();
        history.setAnswer(paramsList.toString() + " = on, else false");
        history.setLocalDateTime(LocalDateTime.now());

        Ankete ankete = anketeService.findById(idAnkate);
        ankete.addHistory(history);

        User user = clientService.findByUsername(userName);
        user.addHistory(history);

        historyRep.save(history);
    }
}