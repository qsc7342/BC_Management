package com.bcm.bcmanager.domain.menu;

import com.bcm.bcmanager.domain.book.Book;
import com.bcm.bcmanager.domain.image.MenuImage;
import com.bcm.bcmanager.domain.menuorigin.MenuOrigin;
import com.bcm.bcmanager.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @Schema(description = "메뉴 이름")
    private String name;

    @Schema(description = "가격")
    private Integer price;

    @Schema(description = "메뉴 설명")
    @Column(name = "description")
    private String desc;

    @OneToOne
    @JoinColumn(name = "menuimage_id")
    private MenuImage image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

    @OneToMany(mappedBy = "menu")
    private List<Review> reviews;

    @OneToMany(mappedBy = "menu")
    private List<MenuOrigin> menuOrigins;

}
