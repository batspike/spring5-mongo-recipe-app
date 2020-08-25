package com.samcancode.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samcancode.domain.UnitOfMeasure;
import com.samcancode.dto.UnitOfMeasureDto;

public class UnitOfMeasureDtoToUnitOfMeasureTest {

    public static final String DESCRIPTION = "description";
    public static final String LONG_VALUE = "1";

    UnitOfMeasureDtoToUnitOfMeasure converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new UnitOfMeasureDtoToUnitOfMeasure();

    }

    @Test
    public void testNullParamter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureDto()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasureDto command = new UnitOfMeasureDto();
        command.setId(LONG_VALUE);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom = converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE, uom.getId());
        assertEquals(DESCRIPTION, uom.getDescription());

    }

}