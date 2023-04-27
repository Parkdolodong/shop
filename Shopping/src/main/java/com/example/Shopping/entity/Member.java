package com.example.Shopping.entity;

import com.example.Shopping.constant.Role;
import com.example.Shopping.dto.MemberDto;
import com.example.Shopping.entity.listener.DateListener;
import com.example.Shopping.entity.listener.LibraryEntityListener;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(value = { LibraryEntityListener.class })
public class Member implements DateListener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String id;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_address", referencedColumnName = "idx")
    private MemberAddress memberAddress;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
