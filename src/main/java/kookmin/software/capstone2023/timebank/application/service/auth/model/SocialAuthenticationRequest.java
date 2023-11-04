package kookmin.software.capstone2023.timebank.application.service.auth.model;

import lombok.Data;

@Data
public class SocialAuthenticationRequest extends AuthenticationRequest {
    private final SocialPlatformType socialPlatformType;
    private final String accessToken;

    public SocialAuthenticationRequest(AccountType accountType, SocialPlatformType socialPlatformType, String accessToken) {
        super(AuthenticationType.SOCIAL, accountType);
        this.socialPlatformType = socialPlatformType;
        this.accessToken = accessToken;
    }
}