package com.bcm.bcmanager.domain.country;

import com.bcm.bcmanager.util.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Country implements BaseEnumCode<String> {

    한국산 ("KR"),
    중국산 ("CN");

    @Getter
    private final String value;

}

