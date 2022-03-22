package com.bcm.bcmanager.domain.origin;

import com.bcm.bcmanager.domain.country.Country;
import com.bcm.bcmanager.domain.map.MenuOriginMap;
import com.bcm.bcmanager.util.CountryConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Origin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column(name = "origin_id")
    private Long id;

    @OneToMany(mappedBy = "origin")
    @JsonIgnore
    private List<MenuOriginMap> map;

    private String material;

    @Convert(converter = CountryConverter.class)
    private Country country;

}
