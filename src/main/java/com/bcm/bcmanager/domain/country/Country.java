package com.bcm.bcmanager.domain.country;

import lombok.Getter;

public enum Country {
    KR ("한국산"),
    CN ("중국산");

    @Getter
    private final String value;

    Country(String value) {
        this.value = value;
    }
}

