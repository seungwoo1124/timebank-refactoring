package kookmin.software.capstone2023.timebank.application.service.account

import kookmin.software.capstone2023.timebank.domain.model.Account
import kookmin.software.capstone2023.timebank.domain.repository.AccountJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
// JPA 사용
// 레포지토리 개별 분리 필요
class AccountFinder(
    private val accountJpaRepository: AccountJpaRepository,
) {
    fun findById(accountId: Long): Account? {
        return accountJpaRepository.findByIdOrNull(accountId)
    }
}
