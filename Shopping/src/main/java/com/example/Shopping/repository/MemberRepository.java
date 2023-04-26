package com.example.Shopping.repository;

import com.example.Shopping.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByPhoneNumber(String phoneNumber);
}
