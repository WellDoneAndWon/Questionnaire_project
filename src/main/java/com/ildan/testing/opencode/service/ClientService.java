package com.ildan.testing.opencode.service;

import com.ildan.testing.opencode.config.SecurityUser;
import com.ildan.testing.opencode.model.entity.User;
import com.ildan.testing.opencode.repositiry.ClientRep;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService implements UserDetailsService {

    private final ClientRep clientRep;

    public User findUserByUsername(String userName) {
        return clientRep.findUserByUsername(userName);
    }

    public User findByUsername(String userName) {
        return clientRep.findByUsername(userName).orElse(null);
    }

    public void save(User user) {
        clientRep.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = clientRep.findUserByUsername(name);
        return SecurityUser.fromUserDetails(user);
    }
}