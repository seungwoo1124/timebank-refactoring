package kookmin.software.capstone2023.timebank.application.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

@Configuration
// 클래스에 어노테이션 지정시, 지정한 클래스 안에 메소드들을 bean 등록 가능
class PasswordEncoderConfiguration {

    @Bean
    // 비밀번호 암호화
    fun passwordEncoder(): PasswordEncoder {
        return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8()
    }
}
