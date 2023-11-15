package kookmin.software.capstone2023.timebank.domain.model.auth

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import kookmin.software.capstone2023.timebank.domain.model.BaseTimeEntity

@Entity
@Table(name = "authentication_password")
class PasswordAuthentication(
    @Id
    @Column(nullable = false, updatable = false)
    val userId: Long,

    @Column(nullable = false, updatable = true)
    val username: String,

    @Column(nullable = false, updatable = true)
    var password: String,
) : BaseTimeEntity() {
    fun updatePassword(password: String) {
        this.password = password
    }
}
