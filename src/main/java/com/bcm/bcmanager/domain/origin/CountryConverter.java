package com.bcm.bcmanager.domain.origin;

import com.bcm.bcmanager.domain.country.Country;
import com.bcm.bcmanager.util.AbstractBaseEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class CountryConverter extends AbstractBaseEnumConverter<Country, String> {

    @Override
    protected Country[] getValueList() {
        return Country.values();
    }
}
