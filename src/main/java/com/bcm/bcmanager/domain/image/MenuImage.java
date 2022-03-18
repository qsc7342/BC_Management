package com.bcm.bcmanager.domain.image;
import com.bcm.bcmanager.domain.menu.Menu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class MenuImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuimage_id")
    private Long id;

    private String fname;
}
