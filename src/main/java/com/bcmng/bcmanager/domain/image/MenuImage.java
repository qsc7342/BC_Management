package com.bcmng.bcmanager.domain.image;
import com.bcmng.bcmanager.domain.menu.Menu;
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
//
    @OneToOne(mappedBy = "image", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Menu menu;

    private String filePath;
    private String fileName;
}
