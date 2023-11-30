package kookmin.software.capstone2023.timebank;

import com.fasterxml.jackson.databind.ObjectMapper;
import kookmin.software.capstone2023.timebank.application.service.bank.account.BankAccountCreateService;
import kookmin.software.capstone2023.timebank.domain.model.Account;
import kookmin.software.capstone2023.timebank.domain.model.AccountType;
import kookmin.software.capstone2023.timebank.domain.repository.AccountJpaRepository;
import kookmin.software.capstone2023.timebank.presentation.api.RequestAttributes;
import kookmin.software.capstone2023.timebank.presentation.api.v1.model.bank.account.BankAccountCreateRequestData;
import kookmin.software.capstone2023.timebank.presentation.api.auth.model.UserContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BankAccountCreateService bankAccountCreateService;

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Test
    public void testFindAccountById() throws Exception {
        Optional<Account> account = accountJpaRepository.findById(1L);

        System.out.println(account.get().getId());
    }

    @Test
    public void testCreateBankAccountService() throws Exception {
        BankAccountCreateRequestData requestData = new BankAccountCreateRequestData("test1");
        UserContext userContext = new UserContext(1L, 1L, AccountType.INDIVIDUAL);

        BankAccountCreateService.CreatedBankAccount createdBankAccount = bankAccountCreateService.createBankAccount(
                userContext.getAccountId(),
                requestData.getPassword(),
                1L
        );
    }

    @Test
    public void testCreateBankAccount() throws Exception {
        // Mock request data
        BankAccountCreateRequestData requestData = new BankAccountCreateRequestData("test1");

        // Mock user context
        UserContext userContext = new UserContext(1L, 1L, AccountType.INDIVIDUAL);

        // Perform the request
        ResultActions resultActions = mockMvc.perform(post("/api/v1/bank/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .requestAttr(RequestAttributes.USER_CONTEXT, userContext)
                        .content(objectMapper.writeValueAsString(requestData)));
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(100.0))
                .andExpect(jsonPath("$.bankAccountNumber").value("1234567890"))
                .andExpect(jsonPath("$.bankAccountId").value(1L));

        System.out.println(resultActions);
    }
}
