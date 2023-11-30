package kookmin.software.capstone2023.timebank.domain.repository;

import kookmin.software.capstone2023.timebank.domain.model.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountJpaRepository extends JpaRepository<BankAccount, Long> {
    Page<BankAccount> findAll(Specification<BankAccount> spec, Pageable pageable);
    BankAccount findByAccountNumber(String accountNumber);
    List<BankAccount> findAllByAccountId(Long accountId);
}
