package com.bcm.bcmanager.domain.map;

import com.bcm.bcmanager.domain.menu.Menu;
import com.bcm.bcmanager.domain.origin.Origin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MenuOriginMap {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_id")
    @JsonUnwrapped
    private Origin origin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();
}
