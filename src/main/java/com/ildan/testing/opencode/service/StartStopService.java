package com.ildan.testing.opencode.service;

import com.ildan.testing.opencode.model.entity.StartStopResult;
import com.ildan.testing.opencode.repositiry.StartStopResultRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartStopService {

    private final StartStopResultRep startStopResultRep;

    public boolean getResult() {
        return startStopResultRep.findAll().get(0).getResult();
    }

    public boolean startStop(Boolean bool) {
        StartStopResult model = startStopResultRep.findAll().get(0);
        model.setResult(bool);
        startStopResultRep.save(model);

        return model.getResult();
    }
}
