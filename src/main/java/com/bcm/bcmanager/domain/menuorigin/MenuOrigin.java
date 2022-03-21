package com.bcm.bcmanager.domain.menuorigin;

import com.bcm.bcmanager.domain.menu.Menu;
import com.bcm.bcmanager.domain.origin.Origin;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class MenuOrigin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

}
