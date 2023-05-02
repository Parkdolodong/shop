package com.example.Shopping.dto;

import com.example.Shopping.constant.BidStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDto {
    private Long idx;

    private String itemName;
    private String itemDetail;
    private LocalDateTime endDate;
    private int minBicPrice;
    private String catagory;
    private BidStatus bidStatus;
    private MemberDto memberDto;
}
