package kookmin.software.capstone2023.timebank.domain.repository.spec;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import kookmin.software.capstone2023.timebank.domain.model.Account;
import kookmin.software.capstone2023.timebank.domain.model.BankAccount;
import kookmin.software.capstone2023.timebank.domain.model.BankAccountTransaction;
import kookmin.software.capstone2023.timebank.domain.model.BankBranch;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BankAccountTransactionSpecs {

    // 거래번호로 검색
    public static Specification<BankAccountTransaction> withTransactionId(Long transactionId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), transactionId);
    }

    // 은행계좌 id 로 검색
    public static Specification<BankAccountTransaction> withBankAccountId(Long bankAccountId) {
        if (bankAccountId == null) {
            return (root, query, cb) -> null;
        }

        return (root, query, cb) -> {
            query.distinct(true);

            Predicate[] predicates = new Predicate[1];

            Join<BankAccountTransaction, BankAccount> bankAccountJoin = root.join("receiverBankAccount", JoinType.INNER);
            Join<BankAccount, Account> accountJoin = bankAccountJoin.join("account", JoinType.INNER);

            predicates[0] = cb.equal(root.get("bankAccountId"), bankAccountId);

            return cb.and(predicates);
        };
    }

    // 날짜로 검색
    public static Specification<BankAccountTransaction> withTransactionAtBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            return (root, query, cb) -> null;
        }

        return (root, query, cb) -> {
            LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
            LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
            LocalDateTime transactionAt = root.get("transactionAt");
            return cb.between(transactionAt, startDateTime, endDateTime);
        };
    }

    // 은행 지점으로 검색
    public static Specification<BankAccountTransaction> withBranchId(Long branchId) {
        if (branchId == null) {
            return (root, query, cb) -> null;
        }

        return (root, query, cb) -> {
            query.distinct(true);

            Predicate[] predicates = new Predicate[1];

            Join<BankAccountTransaction, BankAccount> bankAccountJoin = root.join("receiverBankAccount", JoinType.INNER);
            Join<BankAccount, BankBranch> branchJoin = bankAccountJoin.join("branch", JoinType.INNER);

            predicates[0] = cb.equal(branchJoin.get("id"), branchId);

            return cb.and(predicates);
        };
    }

    // 계정 소유자명으로 검색
    public static Specification<BankAccountTransaction> withAccountOwnerName(String accountOwnerName) {
        if (accountOwnerName == null) {
            return (root, query, criteriaBuilder) -> null;
        }

        return (root, query, criteriaBuilder) -> {
            query.distinct(true);

            Predicate[] predicates = new Predicate[1];

            Join<BankAccountTransaction, BankAccount> accountJoin = root.join("receiverBankAccount", JoinType.INNER);
            Join<BankAccount, Account> userJoin = accountJoin.join("account", JoinType.INNER);

            predicates[0] = criteriaBuilder.like(userJoin.get("name"), "%" + accountOwnerName + "%");

            return criteriaBuilder.and(predicates);
        };
    }
}
