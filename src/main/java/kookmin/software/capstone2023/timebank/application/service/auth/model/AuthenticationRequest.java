package kookmin.software.capstone2023.timebank.application.service.auth.model;

import jakarta.annotation.Resource;
import kookmin.software.capstone2023.timebank.domain.model.AccountType;
import kookmin.software.capstone2023.timebank.domain.model.auth.AuthenticationType;
import kookmin.software.capstone2023.timebank.domain.model.auth.SocialPlatformType;
import lombok.Data;

@Data
public abstract class AuthenticationRequest {
    private final AuthenticationType type;
    private final AccountType accountType;

    public AuthenticationRequest(AuthenticationType type, AccountType accountType) {
        this.type = type;
        this.accountType = accountType;
    }
}




