package com.bcmng.bcmanager.domain.menu;

import com.bcmng.bcmanager.domain.book.Book;
import com.bcmng.bcmanager.domain.image.MenuImage;
import com.bcmng.bcmanager.domain.origin.Origin;
import com.bcmng.bcmanager.domain.review.Review;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    private String name; // 메뉴명
    private Integer price; // 메뉴 가격

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "menuimage_id")
    private MenuImage image; // 메뉴 이미지, 1:1 매핑

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book; // 예약
//
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Review> reviews;
    /*
    ** 원산지 리스트
    */
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Origin> origins;
}
