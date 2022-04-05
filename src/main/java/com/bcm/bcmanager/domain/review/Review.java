package com.bcm.bcmanager.domain.review;

import com.bcm.bcmanager.domain.menu.Menu;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @JsonBackReference
    private Menu menu;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "작성 일")
    private String credt;

    @Schema(description = "수정 일")
    private String upddt;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();
}
