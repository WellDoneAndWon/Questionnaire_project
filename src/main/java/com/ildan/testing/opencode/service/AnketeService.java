package com.ildan.testing.opencode.service;

import com.ildan.testing.opencode.model.entity.Ankete;
import com.ildan.testing.opencode.repositiry.AnketeRep;
import com.ildan.testing.opencode.repositiry.HistoryRep;
import com.ildan.testing.opencode.repositiry.QuestionRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnketeService {

    private final AnketeRep anketeRep;
    private final HistoryRep historyRep;
    private final QuestionRep questionRep;

    public List<Ankete> findAll() {
        return anketeRep.findAll();
    }

    public Ankete findById(long index) {
        return anketeRep.findById(index).get();
    }

    public void delete(Ankete ankete) {
        anketeRep.delete(ankete);
    }

    public void deleteById(long id) {
        anketeRep.deleteById(id);
    }

    public void deleteAll() {
        historyRep.deleteAll();
        questionRep.deleteAll();
        anketeRep.deleteAll();
    }

    public void addAnkate(String ankateName) {
        Ankete ankete = new Ankete();
        ankete.setName(ankateName);
        anketeRep.save(ankete);
    }
}