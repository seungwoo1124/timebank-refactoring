package kookmin.software.capstone2023.timebank.application.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application.authentication.access-token")
// yml 파일 읽기
data class AccessTokenProperties(
    val secretKey: String,
)
