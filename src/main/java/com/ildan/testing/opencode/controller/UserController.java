package com.ildan.testing.opencode.controller;

import com.ildan.testing.opencode.service.AnketeService;
import com.ildan.testing.opencode.utils.MyConstants;
import com.ildan.testing.opencode.service.HistoryService;
import com.ildan.testing.opencode.service.StartStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AnketeService anketeService;
    private final HistoryService historyService;
    private final StartStopService startStopService;
    public static final String FORM_USER_MAIN = "main-client-form";
    public static final String FORM_USER_ANKATE = "ankate-client-form";
    public static final String START_STOP_FORM = "start-stop-form";

    @GetMapping("/main")
    public String mainForm(Model model) {
        model.addAttribute("ankats", anketeService.findAll());
        return FORM_USER_MAIN;
    }

    @GetMapping("/ankate/{id}")
    public String getAnkateForId(@PathVariable(name = "id") Long id, Model model) {

        if (startStopService.getResult()) {
            model.addAttribute("ankate", anketeService.findById(id));
            return FORM_USER_ANKATE;
        } else {
            model.addAttribute("msg", "Администратор еще не начал сеанс!");
            return START_STOP_FORM;
        }
    }

    @GetMapping("/processing-form")
    public String processing(HttpServletRequest request,
                             @RequestParam(name = "id_ankate") Long id, Model model) {

        List<String> paramsList = new ArrayList<String>() {{
            addAll(request.getParameterMap().keySet());
        }};
        paramsList.remove(0);
        historyService.saveAllHistory(paramsList, getCurrentUsername(), id);
        model.addAttribute("ankats", anketeService.findAll());
        return FORM_USER_MAIN;
    }

    @GetMapping("/cancel")
    public String cancel() {
        return MyConstants.FORM_USER_MAIN_REDIRECT;
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}