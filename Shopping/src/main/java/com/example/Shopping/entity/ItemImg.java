package com.example.Shopping.entity;

import com.example.Shopping.dto.ItemImgDto;
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
public class ItemImg extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String imgName; //이미지파일명
    private String oriImgName; //원본 이미지 파일명
    private String imgUrl; //이미지 조회 경로
    private String repimgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public void uqdateItemImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }

    public static ItemImg createItemImg(ItemImgDto itemImgDto, Item item) {
        ItemImg itemImg = new ItemImg();
        itemImg.setIdx(itemImgDto.getIdx());
        itemImg.setImgName(itemImgDto.getImgName());
        itemImg.setOriImgName(itemImgDto.getOriImgName());
        itemImg.setImgUrl(itemImgDto.getImgUrl());
        itemImg.setRepimgYn(itemImgDto.getRepimgYn());
        itemImg.setItem(item);
        return itemImg;
    }
}
