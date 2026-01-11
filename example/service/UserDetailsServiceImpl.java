package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entities.UserInfo;
import org.example.model.UserInfoDto;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.UUID;

@Component
@AllArgsConstructor
@Data
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private final UserRepository userRepository;


    @Autowired
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not found user !! " + username);
        }

        return new CoustomUserDetails(user);


    }

    public UserInfo checkIfUserAlredyExsist(UserInfoDto userInfoDto) {

        return userRepository.findByUsername(userInfoDto.getUsername());

    }

    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }


    public boolean signupUser(UserInfoDto userInfoDto) {
        if (userExists(userInfoDto.getUsername())) {
            throw new RuntimeException("Username already exists: " + userInfoDto.getUsername());
        }


        String encodedPassword = passwordEncoder.encode(userInfoDto.getPassword());


        UserInfo newUser = new UserInfo(
                UUID.randomUUID().toString(),
                userInfoDto.getUsername(),
                encodedPassword,
                new HashSet<>()
        );

        UserInfo savedUser = userRepository.save(newUser);

        return true;


    }
}
