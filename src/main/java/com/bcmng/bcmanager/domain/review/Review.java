package com.bcmng.bcmanager.domain.review;

import com.bcmng.bcmanager.domain.menu.Menu;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu; // 리뷰 메뉴 (N:1 매핑)

    private String content; // 리뷰 내용
    private String createDate; // 리뷰 생성 날짜

}
