package com.example.Shopping.repository;

import com.example.Shopping.entity.MemberAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAddressRepository extends JpaRepository<MemberAddress, Long> {
}
