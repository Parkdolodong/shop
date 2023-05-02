package com.example.Shopping.entity;

import com.example.Shopping.constant.Role;
import com.example.Shopping.dto.MemberDto;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String id; //아이디
    private String password; //비밀번호
    private String name; //이름
    private String email; //이메일
    private String phoneNumber; //전화번호

    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_address")
    private MemberAddress memberAddress; //상세주소

    @Enumerated(EnumType.STRING)
    private Role role; // 관리자 or 사용자

    public static Member createMember(MemberDto memberDto, MemberAddress memberAddress, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setId(memberDto.getId());
        String password = passwordEncoder.encode(memberDto.getPassword());
        member.setPassword(password);
        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());
        member.setPhoneNumber(memberDto.getPhoneNumber());
        member.setMemberAddress(memberAddress);
        member.setRole(Role.ADMIN);
        return member;
    }
}
