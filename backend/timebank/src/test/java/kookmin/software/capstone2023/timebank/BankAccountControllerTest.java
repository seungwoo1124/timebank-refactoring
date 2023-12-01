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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        BankAccountCreateRequestData requestData = new BankAccountCreateRequestData("1234");

        // Mock user context
        UserContext userContext = new UserContext(2L, 2L, AccountType.INDIVIDUAL);

        Optional<Account> account = accountJpaRepository.findById(2L);
        assertTrue(account.isPresent(), "해당하는 id의 account가 존재하지 않습니다.");

        System.out.println(account.get().getId());
        System.out.println(account.get().getName());

        // Perform the request
        ResultActions resultActions = mockMvc.perform(post("/api/v1/bank/account")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .header(RequestAttributes.USER_CONTEXT, objectMapper.writeValueAsString(userContext))
                        .content(objectMapper.writeValueAsString(requestData)));

        System.out.println(resultActions.andReturn().getResponse().getContentAsString());

        resultActions
                .andExpect(status().isOk()) // HTTP 상태 코드가 200인지 확인
                .andExpect(content().contentType("application/json")) // 응답 컨텐츠 유형이 "application/json"인지 확인
                .andExpect(jsonPath("$.balance").value(300.0)) // JSON 응답에서 balance 속성의 값이 기대한 값과 일치하는지 확인
                .andExpect(jsonPath("$.bankAccountId").value(2L)); // JSON 응답에서 accountNumber 속성의 값이 기대한 값과 일치하는지 확인.andExpect(jsonPath("$.bankAccountId").value()); // JSON 응답에서 bankAccountId 속성의 값이 기대한 값과 일치하는지 확인
        
        System.out.println(resultActions);
    }
}
