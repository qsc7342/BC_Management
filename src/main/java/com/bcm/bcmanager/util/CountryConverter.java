package com.bcm.bcmanager.util;

import com.bcm.bcmanager.domain.country.Country;

import javax.persistence.AttributeConverter;

public class CountryConverter implements AttributeConverter<Country, String> {

    @Override
    public String convertToDatabaseColumn(Country attribute) {
        if (attribute == null)
            return null;
        return attribute.getValue();
    }

    @Override
    public Country convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        return Country.enumOf(dbData);
    }
}