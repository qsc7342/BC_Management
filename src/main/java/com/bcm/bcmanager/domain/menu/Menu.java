package com.bcm.bcmanager.domain.menu;

import com.bcm.bcmanager.domain.book.Book;
import com.bcm.bcmanager.domain.image.MenuImage;
import com.bcm.bcmanager.domain.origin.Origin;
import com.bcm.bcmanager.domain.review.Review;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
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

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Review> reviews;
    /*
    ** 원산지 리스트
    */
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<Origin> origins;

//    public static Menu createMenu(String name, Integer price) {
//        Menu menu = new Menu();
//        MenuImage menuImage = new MenuImage();
//        menu.setName(name);
//        menu.setPrice(price);
//
//        menuImage.setFileName("테스트 이미지");
//        menuImage.setFilePath("https://cdn.shopify.com/s/files/1/0251/8420/6927/products/d721ac9b7f8a29acc5d0f20553c331d1_200x200.png?v=1573827808");
//        menu.setImage(menuImage);
//        menuImage.setMenu(menu);
//        return menu;
//    }
}
