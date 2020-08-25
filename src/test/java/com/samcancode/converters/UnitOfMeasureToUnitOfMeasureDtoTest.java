package com.samcancode.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samcancode.domain.UnitOfMeasure;
import com.samcancode.dto.UnitOfMeasureDto;

public class UnitOfMeasureToUnitOfMeasureDtoTest {

    public static final String DESCRIPTION = "description";
    public static final String LONG_VALUE = "1";

    UnitOfMeasureToUnitOfMeasureDto converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureDto();
    }

    @Test
    public void testNullObjectConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObj() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(LONG_VALUE);
        uom.setDescription(DESCRIPTION);
        //when
        UnitOfMeasureDto uomc = converter.convert(uom);

        //then
        assertEquals(LONG_VALUE, uomc.getId());
        assertEquals(DESCRIPTION, uomc.getDescription());
    }

}