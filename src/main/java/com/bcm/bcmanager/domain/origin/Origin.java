package com.bcm.bcmanager.domain.origin;

import com.bcm.bcmanager.domain.country.Country;
import com.bcm.bcmanager.domain.menu.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "origin_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;  // 메뉴

    private String material; // 재료

    @Enumerated(EnumType.STRING)
    private Country country; // 원산지

}
