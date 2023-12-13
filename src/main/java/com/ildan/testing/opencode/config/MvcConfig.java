package com.ildan.testing.opencode.config;

import com.ildan.testing.opencode.model.enums.Role;
import com.ildan.testing.opencode.model.entity.StartStopResult;
import com.ildan.testing.opencode.model.entity.User;
import com.ildan.testing.opencode.repositiry.ClientRep;
import com.ildan.testing.opencode.repositiry.StartStopResultRep;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final ClientRep clientRepository;
    private final StartStopResultRep startStopResultRep;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public ApplicationRunner firstStartDbInitTestUsers() {

        PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

        return args -> {

            if (clientRepository.count() == 0) {
                User admin = new User();
                admin.setId(1L);
                admin.setUsername("admin");
                admin.setPassword(bCryptPasswordEncoder.encode("admin"));
                admin.setActive(true);
                admin.setRole(Role.ADMIN);

                User user = new User();
                user.setUsername("user");
                user.setPassword(bCryptPasswordEncoder.encode("user"));
                user.setActive(true);
                user.setRole(Role.USER);

                clientRepository.saveAll(List.of(admin, user));
            }

            if (startStopResultRep.count() == 0) {
                StartStopResult startStopResult = new StartStopResult();
                startStopResult.setResult(false);
                startStopResultRep.save(startStopResult);
            }
        };
    }
}