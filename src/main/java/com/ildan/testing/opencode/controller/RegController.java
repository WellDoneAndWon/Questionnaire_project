package com.ildan.testing.opencode.controller;

import com.ildan.testing.opencode.model.entity.User;
import com.ildan.testing.opencode.model.enums.Role;
import com.ildan.testing.opencode.utils.MyConstants;
import com.ildan.testing.opencode.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(MyConstants.URL_REG)
@RequiredArgsConstructor
public class RegController {

    private final ClientService clientService;

    @GetMapping(MyConstants.URL_REG_MAIN)
    public String regClientForm() {
        return MyConstants.FORM_REG_MAIN;
    }

    @PostMapping(MyConstants.URL_REG_SETTING)
    public String regClient(@RequestParam(name = "username") String name,
                            @RequestParam(name = "password") String password,
                            @RequestParam(name = "isAdmin", required = false) String isAdmin) {

        var user = clientService.findByUsername(name);

        if (user == null) {
            password  = BCrypt.hashpw(password, BCrypt.gensalt());
            clientService.save(new User(name, password,
                    isAdmin == null ? Role.USER : Role.ADMIN, true));
            return MyConstants.URL_REDIRECT_LOGIN_FORM; //login
        }
        return MyConstants.URL_REG_REDIRECT_MAIN;
    }

    @GetMapping(MyConstants.URL_REG_CANCEL)
    public String cancel(){
        return MyConstants.URL_REDIRECT_LOGIN_FORM ;
    }
}