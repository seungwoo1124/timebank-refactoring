package kookmin.software.capstone2023.timebank.application.service.auth;

import kookmin.software.capstone2023.timebank.application.exception.UnauthorizedException;
import kookmin.software.capstone2023.timebank.application.service.auth.token.AccessTokenService;
import kookmin.software.capstone2023.timebank.domain.repository.AccountJpaRepository;
import kookmin.software.capstone2023.timebank.domain.repository.UserJpaRepository;

public class DefaultUserAuthenticator implements UserAuthenticator {
    private final AccessTokenService accessTokenService;
    private final UserJpaRepository userJpaRepository;
    private final AccountJpaRepository accountJpaRepository;

    public DefaultUserAuthenticator(
            AccessTokenService accessTokenService,
            UserJpaRepository userJpaRepository,
            AccountJpaRepository accountJpaRepository
    ) {
        this.accessTokenService = accessTokenService;
        this.userJpaRepository = userJpaRepository;
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public AuthenticationData authenticate(String accessToken) {
        AccessTokenService.AccessTokenClaims claims = accessTokenService.verify(accessToken);

        User user = userJpaRepository.findById(claims.getUserId()).value;
        if (user == null) {
            throw new UnauthorizedException(null);
        }

        Account account = accountJpaRepository.findById(claims.getAccountId()).value;
        if (account == null) {
            throw new UnauthorizedException(null);
        }

        if (user.getAccount().getId() != account.getId()) {
            throw new UnauthorizedException(null);
        }

        return new AuthenticationData(user.getId(), account.getId(), account.getType());
    }
}
