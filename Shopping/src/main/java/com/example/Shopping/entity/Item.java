package com.example.Shopping.entity;

import com.example.Shopping.constant.BidStatus;
import com.example.Shopping.dto.ItemDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Item extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String itemName; //상품이름
    private String itemDetail; //상품설명
    private LocalDateTime endDate; //입찰 종료 시간
    private int minBicPrice; //최소 입찰가
    private String catagory; //카테고리

    @Enumerated(EnumType.STRING)
    private BidStatus bidStatus; //경매 상태

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //상품 판매자

    public static Item createItem(ItemDto itemDto, Member member) {
        Item item = new Item();
        item.setIdx(itemDto.getIdx());
        item.setItemName(itemDto.getItemName());
        item.setItemDetail(itemDto.getItemDetail());
        item.setEndDate(itemDto.getEndDate());
        item.setMinBicPrice(itemDto.getMinBicPrice());
        item.setCatagory(itemDto.getCatagory());
        item.setBidStatus(BidStatus.SALE);
        item.setMember(member);
        return item;
    }

    public void endBid() {
        this.bidStatus = BidStatus.END;
    }
}
