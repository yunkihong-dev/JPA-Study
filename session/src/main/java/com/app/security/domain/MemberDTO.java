package com.app.security.domain;

import com.app.security.constant.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Data
@NoArgsConstructor
public class MemberDTO {
    private String memberName;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate memberBirth;
    private String memberPhoneNumber;
    private String memberId;
    private String memberPassword;
    private String memberEmail;
    private Role memberRole;
}
