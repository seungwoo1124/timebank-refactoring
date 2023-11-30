package kookmin.software.capstone2023.timebank.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class AccountProfile(
    /**
     * 닉네임
     */
    @Column(nullable = true, updatable = true, length = 20)
    var nickname: String,

    /**
     * 프로필 이미지 URL
     */
    @Column(nullable = true, updatable = true, length = 100)
    var imageUrl: String,
)
