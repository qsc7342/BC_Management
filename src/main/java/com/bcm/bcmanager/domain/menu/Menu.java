package com.bcm.bcmanager.domain.menu;

import com.bcm.bcmanager.domain.category.Category;
import com.bcm.bcmanager.domain.image.MenuImage;
import com.bcm.bcmanager.domain.map.MenuBookMap;
import com.bcm.bcmanager.domain.map.MenuOriginMap;
import com.bcm.bcmanager.domain.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Schema(description = "가격")
    private Integer price;

    @Schema(description = "메뉴 설명")
    @Column(name = "description")
    private String desc;

    @OneToOne
    @JoinColumn(name = "menuimage_id")
    private MenuImage image;

    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private List<MenuBookMap> books;

    @OneToMany(mappedBy = "menu")
    private List<Review> reviews;

    @OneToMany(mappedBy = "menu")
    private List<MenuOriginMap> origins;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();
}
