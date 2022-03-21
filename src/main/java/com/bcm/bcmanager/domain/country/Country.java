package com.bcm.bcmanager.domain.country;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum Country {

    KR ("국산"),
    CN ("중국산");

    @Getter
    private final String value;

    public static Country enumOf(String str) {
        return Arrays.stream(Country.values())
                .filter(t -> t.getValue().equals(str))
                .findAny().orElse(null);
    }

}

