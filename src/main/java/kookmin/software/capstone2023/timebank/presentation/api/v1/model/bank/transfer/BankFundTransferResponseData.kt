package kookmin.software.capstone2023.timebank.presentation.api.v1.model.bank.transfer

import kookmin.software.capstone2023.timebank.domain.model.TransactionStatus
import java.math.BigDecimal
import java.time.LocalDateTime

data class BankFundTransferResponseData(
    val transactionAt: LocalDateTime,
    val amount: BigDecimal?,
    val balanceSnapshot: BigDecimal?,
    val status: TransactionStatus,
    val senderBankAccountNumber: String?,
    val receiverBankAccountNumber: String?,
)
