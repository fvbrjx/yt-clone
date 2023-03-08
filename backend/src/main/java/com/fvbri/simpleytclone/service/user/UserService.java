package com.fvbri.simpleytclone.service.user;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fvbri.simpleytclone.exception.user.UserAlreadyExistException;
import com.fvbri.simpleytclone.model.user.Auth0UserInfo;
import com.fvbri.simpleytclone.model.user.User;
import com.fvbri.simpleytclone.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthAPI authAPI;
    private final UserRepository userRepository;

    private  final ObjectMapper objectMapper;

    public boolean saveUser() {
        Jwt jwt = (Jwt) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        try {
            UserInfo userInfo = authAPI.userInfo(jwt.getTokenValue()).execute();
            User user = mapUser(userInfo);
            userRepository.findByAuthSub(user.getAuthSub()).ifPresent((sub) -> {
                    throw new UserAlreadyExistException(String.format("User with sub: %s already exists", sub));
            });
            this.persistUser(user);
        } catch (Auth0Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private void persistUser(User user) {
        userRepository.save(user);
    }

    private User mapUser(UserInfo userInfo) {
        final Map<String, Object> values = userInfo.getValues();
        final Auth0UserInfo auth0UserInfo = objectMapper.convertValue(
                values,
                Auth0UserInfo.class);

        User user = User.builder()
                .firstName(auth0UserInfo.getName())
                .lastName(auth0UserInfo.getFamily_name())
                .emailAddress(auth0UserInfo.getEmail())
                .authSub(auth0UserInfo.getSub())
                .build();
        return user;
    }

}
