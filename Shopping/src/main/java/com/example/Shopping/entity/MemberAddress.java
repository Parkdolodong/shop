package com.example.Shopping.entity;

import com.example.Shopping.dto.MemberAddressDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class MemberAddress extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String address; //주소
    private int zonecode; //우편번호
    private String detailedaddress; //상세주소

    public static MemberAddress createMemberAddress(MemberAddressDto memberAddressDto){
        MemberAddress memberAddress = new MemberAddress();
        memberAddress.setAddress(memberAddressDto.getAddress());
        memberAddress.setDetailedaddress(memberAddressDto.getDetailedaddress());
        memberAddress.setZonecode(memberAddressDto.getZonecode());
        return memberAddress;
    }
}
