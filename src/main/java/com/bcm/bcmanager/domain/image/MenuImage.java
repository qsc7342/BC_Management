package com.bcm.bcmanager.domain.image;
import com.bcm.bcmanager.domain.menu.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuimage_id")
    private Long id;

    @Schema(name = "파일 명")
    private String fname;

    @JsonIgnore
    @OneToOne(mappedBy = "image", cascade = CascadeType.ALL)
    private Menu menu;

}
