package org.example.service;


import org.example.entities.RefeshToken;
import org.example.entities.UserInfo;
import org.example.repository.RefreshTokenRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefeshTokenService {


    // ok so this is service class responsible for creating and vatidating expire
    // and finding refesh token by  string tokenstokens



    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;



    //creating new refresh token  with the username
    public RefeshToken createRefreshToken(String username){
        // finding user details  from  db
        UserInfo user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found: " + username);
        }


        // making new refesh token out of the info pass  by above
        RefeshToken refeshToken = RefeshToken.builder()
                .userInfo(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();


        return refreshTokenRepository.save(refeshToken);




    }


    // finding token in db with the string of refresh token and returning the token with info
    public Optional<RefeshToken> findByToken (String token){
        return refreshTokenRepository.findByToken(token);
    }

    // checking the expiry of refresh token
    public RefeshToken verifyExpiration(RefeshToken token){
        //check for refesh token expiry
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");

        }

        return token;
    }




}
