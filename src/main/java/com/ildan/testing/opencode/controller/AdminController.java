package com.ildan.testing.opencode.controller;

import com.ildan.testing.opencode.service.AnketeService;
import com.ildan.testing.opencode.utils.MyConstants;
import com.ildan.testing.opencode.service.HistoryService;
import com.ildan.testing.opencode.service.QuestionService;
import com.ildan.testing.opencode.service.StartStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AnketeService anketeService;
    private final HistoryService historyService;
    private final QuestionService questionService;
    private final StartStopService startStopService;
    public static final String FORM_ADMIN_MAIN = "main-admin-form";
    public static final String FORM_ADMIN_UPDATE_QUESTION = "update-question-form";
    public static final String FORM_ADMIN_ADD_QUESTION = "add-question-form";
    public static final String FORM_ADMIN_ADD_ANKATE = "add-ankate-form";
    public static final String FORM_ADMIN_HISTORY = "ankate-history-form";

    @GetMapping("/main")
    public String mainForm(Model model) {
        model.addAttribute("ankats", anketeService.findAll());
        model.addAttribute("startStop", startStopService.getResult() ? "Выполнение включено" : "Выполнение отключено");
        return FORM_ADMIN_MAIN;
    }

    @GetMapping("/delete/{id_ankate}/{id_question}")
    public String deleteQuestion(@PathVariable(value = "id_ankate") Long idAnkate,
                                 @PathVariable(value = "id_question") Long idQuestion) {
        questionService.delete(idQuestion, idAnkate);
        return MyConstants.FORM_ADMIN_MAIN_REDIRECT;
    }

    @GetMapping("/update/{id_question}")
    public String updateQuestion(@PathVariable(value = "id_question") Long idQuestion, Model model) {
        model.addAttribute("question", questionService.findById(idQuestion));
        return FORM_ADMIN_UPDATE_QUESTION;
    }

    @PostMapping("/update")
    public String updateQuestion(@RequestParam(name = "id_question") Long idQuestion,
                                 @RequestParam(name = "question") String newTextQuestion) {
        questionService.updateQuestion(idQuestion, newTextQuestion);
        return MyConstants.FORM_ADMIN_MAIN_REDIRECT;
    }

    @GetMapping("/add-question/{id_ankate}")
    public String addQuestion(@PathVariable(value = "id_ankate") Long idAnkate, Model model) {
        model.addAttribute("id_ankate", idAnkate);
        return FORM_ADMIN_ADD_QUESTION;
    }

    @PostMapping("/add-question")
    public String addQuestion(@RequestParam(name = "id") Long id,
                              @RequestParam(name = "question") String string) {
        questionService.addQuestion(id, string);
        return MyConstants.FORM_ADMIN_MAIN_REDIRECT;
    }

    @GetMapping("/add-ankate")
    public String addAnkate() {
        return FORM_ADMIN_ADD_ANKATE;
    }

    @PostMapping("/add-ankate")
    public String addAnkete(@RequestParam(name = "anketeName") String ankateName) {
        anketeService.addAnkate(ankateName);
        return MyConstants.FORM_ADMIN_MAIN_REDIRECT;
    }

    @GetMapping("/ankate-history/{id}")
    public String history(@PathVariable Long id, Model model) {
        model.addAttribute("histories", historyService.getHistoriesForAnkate(id));
        return FORM_ADMIN_HISTORY;
    }

    @GetMapping("/cancel")
    public String cancel() {
        return MyConstants.FORM_ADMIN_MAIN_REDIRECT;
    }

    @GetMapping("/delete-ankate/{id}")
    public String deleteAnkate(@PathVariable Long id) {
        anketeService.deleteById(id);
        return MyConstants.FORM_ADMIN_MAIN_REDIRECT;
    }

    @GetMapping("/delete-all")
    public String deleteAll() {
        anketeService.deleteAll();
        return MyConstants.FORM_ADMIN_MAIN_REDIRECT;
    }

    @PostMapping("/start-stop")
    public String executeQuestions(@RequestParam Boolean result) {
        startStopService.startStop(result);
        return MyConstants.FORM_ADMIN_MAIN_REDIRECT;
    }
}