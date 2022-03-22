package com.bcm.bcmanager.domain.book;

import com.bcm.bcmanager.domain.map.MenuBookMap;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Book {

    @Id
    @GeneratedValue
    @Column(name="book_id")
    private Long id;

    @Schema(name = "예약 명")
    private String name;

    @Schema(name = "예약 생성 일")
    private String credt;

    @Schema(name = "에약 일")
    private String bookdt; // 예약일

    @Schema(name = "장바구니")
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<MenuBookMap> cart;

    @Schema(name = "총 가격")
    private Integer totprc;

    @Schema(name = "취소 여부 (Y/N)")
    private String cancleyn = "N";

    @Schema(name = "확인 여부 (Y/N)")
    private String confirmyn = "N";

    @Schema(name = "완료 여부 (Y/N)")
    private String completeyn = "N";

}
