package kookmin.software.capstone2023.timebank.application.configuration;

import kookmin.software.capstone2023.timebank.domain.model.AccountType;
import lombok.Data;

@Data
public class UserProperties {
    private long id;
    private long accountId;
    private AccountType accountType;
}
