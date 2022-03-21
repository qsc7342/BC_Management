package com.bcm.bcmanager.domain.review;

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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Long id;

    private String pwd;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "작성 일")
    private String credt;

    @Schema(description = "수정 일")
    private String upddt;

}
