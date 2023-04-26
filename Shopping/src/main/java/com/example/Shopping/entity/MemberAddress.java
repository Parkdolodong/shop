package com.example.Shopping.entity;

import com.example.Shopping.dto.MemberAddressDto;
import com.example.Shopping.entity.listener.DateListener;
import com.example.Shopping.entity.listener.LibraryEntityListener;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(value = { LibraryEntityListener.class })
public class MemberAddress implements DateListener {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String address;
    private int zonecode;
    private String detailedaddress;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberAddress createMemberAddress(MemberAddressDto memberAddressDto){
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setAddress(memberAddressDto.getAddress());
        memberAddress.setDetailedaddress(memberAddressDto.getDetailedaddress());
        memberAddress.setZonecode(memberAddressDto.getZonecode());
        return memberAddress;
    }
}
