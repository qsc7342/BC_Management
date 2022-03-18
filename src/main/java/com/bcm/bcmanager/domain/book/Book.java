package com.bcm.bcmanager.domain.book;

import com.bcm.bcmanager.domain.menu.Menu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Book {
    @Id
    @GeneratedValue
    @Column(name="book_id")
    private Long id;

    private String name; // 예약명
    private String createDate; // 예약 생성 날짜
    private String bookDate; // 예약일

    /*
    ** 예약에 들어있는 메뉴 리스트
    ** 1:N 매핑, JoinColumn (book_id)
    */
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Menu> menus = new ArrayList<>();

    private Integer totalPrice; // 총 가격
    private String isCancel; // 취소 여부
    private String isConfirm; // 확인 여부
    private String isComplete; // 처리 여부
}
