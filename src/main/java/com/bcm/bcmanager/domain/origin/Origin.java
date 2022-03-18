package com.bcm.bcmanager.domain.origin;

import com.bcm.bcmanager.domain.country.Country;
import com.bcm.bcmanager.domain.menu.Menu;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Origin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "origin_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;  // 메뉴

    private String material; // 재료

    @Enumerated(EnumType.STRING)
    private Country country; // 원산지
}
