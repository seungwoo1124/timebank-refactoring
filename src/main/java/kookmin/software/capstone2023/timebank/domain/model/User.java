package kookmin.software.capstone2023.timebank.domain.model;

import jakarta.persistence.*;
import kookmin.software.capstone2023.timebank.domain.model.auth.AuthenticationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 인증 타입
     */
    @Column(nullable = false, updatable = true, length = 20)
    @Enumerated(EnumType.STRING)
    private AuthenticationType authenticationType;

    /**
     * 이름
     */
    @Column(nullable = false, updatable = true, length = 20)
    private String name;

    /**
     * 전화번호
     */
    @Column(nullable = false, updatable = true, length = 20)
    private String phoneNumber;

    /**
     * 성별
     */
    @Column(nullable = false, updatable = true, length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    /**
     * 생년월일
     */
    @Column(nullable = false, updatable = true)
    private LocalDate birthday;

    /**
     * 마지막 로그인 시간 (UTC)
     */
    @Column(nullable = true, updatable = true)
    private LocalDateTime lastLoginAt;

    @Column(nullable = true, updatable = true)
    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(
            name = "account_id",
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)
    )
    private Account account;

    public void updateUserInfo(String name, String phoneNumber, Gender gender, LocalDate birthday) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthday = birthday;
    }

    public void updateLastLoginAt(LocalDateTime loginAt) {
        this.lastLoginAt = loginAt;
    }
}
