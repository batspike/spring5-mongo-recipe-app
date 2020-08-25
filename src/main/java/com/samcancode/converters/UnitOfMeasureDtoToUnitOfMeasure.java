package com.samcancode.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.samcancode.domain.UnitOfMeasure;
import com.samcancode.dto.UnitOfMeasureDto;

@Component
public class UnitOfMeasureDtoToUnitOfMeasure implements Converter<UnitOfMeasureDto, UnitOfMeasure>{

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureDto source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setDescription(source.getDescription());
        return uom;
    }
}
