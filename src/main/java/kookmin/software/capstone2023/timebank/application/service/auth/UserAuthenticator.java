package kookmin.software.capstone2023.timebank.application.service.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

public interface UserAuthenticator {
    @Data
    @AllArgsConstructor
    class AuthenticationData {
        private final long userId;
        private final long accountId;
        private final AccountType accountType;
    }

    AuthenticationData authenticate(String accessToken);
}
