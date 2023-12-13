package com.ildan.testing.opencode.controller;

import com.ildan.testing.opencode.model.enums.Role;
import com.ildan.testing.opencode.utils.MyConstants;
import com.ildan.testing.opencode.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RedirectionController {

    private final ClientService clientService;

    @GetMapping(MyConstants.URL_REDIRECT_MAIN)
    public String getUserOrAdminForm(){
        var user =  clientService.findUserByUsername(getCurrentUsername());
        return user.getRole().equals(Role.ADMIN) ?
                MyConstants.FORM_ADMIN_MAIN_REDIRECT : MyConstants.FORM_USER_MAIN_REDIRECT;
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}