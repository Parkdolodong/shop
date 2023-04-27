package com.example.Shopping.service;

import com.example.Shopping.dto.MemberAddressDto;
import com.example.Shopping.dto.MemberDto;
import com.example.Shopping.entity.Member;
import com.example.Shopping.entity.MemberAddress;
import com.example.Shopping.repository.MemberAddressRepository;
import com.example.Shopping.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final MemberAddressRepository memberAddressRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     */
    public Member saveMember(MemberDto memberDto){
        validateDuplicateMember(memberDto);
        MemberAddressDto memberAddressDto = memberDto.getMemberAddressDto();
        MemberAddress memberAddress = MemberAddress.createMemberAddress(memberAddressDto);
        memberAddress = memberAddressRepository.save(memberAddress);
        Member member = Member.createMember(memberDto, memberAddress, passwordEncoder);
        member = memberRepository.save(member);
        return member;
    }

    private void validateDuplicateMember(MemberDto memberDto) throws DuplicateKeyException {
        Optional<Member> findMemberId = memberRepository.findById(memberDto.getId());
        Optional<Member> findMemberEmail = memberRepository.findByEmail(memberDto.getEmail());
        Optional<Member> findMemberPhoneNumber = memberRepository.findByPhoneNumber(memberDto.getPhoneNumber());
        if(findMemberId.isPresent()) {
            throw new DuplicateKeyException("아이디가 이미 존재합니다.");
        }
        if(findMemberEmail.isPresent()) {
            throw new DuplicateKeyException("이메일이 이미 존재합니다.");
        }
        if(findMemberPhoneNumber.isPresent()) {
            throw new DuplicateKeyException("이미 사용 중인 전화번호입니다");
        }
    }

    /**
     * 로그인 함수
     */
//    public boolean isPasswordMatch(String id, String password) {
//        Optional<Member> memberOpt = memberRepository.findById(id);
//        log.info("password: {}", passwordEncoder.matches(password, memberOpt.get().getPassword()));
//        return memberOpt.isPresent() && passwordEncoder.matches(password, memberOpt.get().getPassword());
//    }
//    public boolean login(String id, String password) {
//        Optional<Member> member = memberRepository.findById(id);
//        if (member.isPresent() && passwordEncoder.matches(password, member.get().getPassword())) {
//            // 로그인 성공
//            return true;
//        } else {
//            // 로그인 실패
//            return false;
//        }
//    }

    /**
     * 중복되는 아이디 찾는 함수
     */
    public boolean isIdExists(String userId) {
        Optional<Member> member = memberRepository.findById(userId);
        return member.isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findById(id);
        if(!member.isPresent()) {
            throw new UsernameNotFoundException(id);
        }

        return User.builder()
                .username(member.get().getId())
                .password(member.get().getPassword())
                .roles(member.get().getRole().toString())
                .build();
    }
}
