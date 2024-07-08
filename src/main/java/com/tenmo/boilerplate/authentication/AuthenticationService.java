package com.tenmo.boilerplate.authentication;

import com.tenmo.boilerplate.shared.configs.JwtService;
import com.tenmo.boilerplate.shared.exceptions.BusinessException;
import com.tenmo.boilerplate.user.UserDAO;
import com.tenmo.boilerplate.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDAO userDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String authenticate(CredentialsDTO credentialsDTO) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    credentialsDTO.getUsername(),
                    credentialsDTO.getPassword()
            );
            authenticationManager.authenticate(token);

            return jwtService.generateToken(credentialsDTO.getUsername());
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    public void signup(SignUpDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        user.setRole(User.Role.USER);
        userDAO.save(user);
    }
}
