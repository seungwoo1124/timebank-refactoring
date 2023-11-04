package kookmin.software.capstone2023.timebank.application.service.auth.model;

import lombok.Data;

@Data
public class PasswordAuthenticationRequest extends AuthenticationRequest {
    private final String username;
    private final String password;

    public PasswordAuthenticationRequest(AccountType accountType, String username, String password) {
        super(AuthenticationType.PASSWORD, accountType);
        this.username = username;
        this.password = password;
    }
}