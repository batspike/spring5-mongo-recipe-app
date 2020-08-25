package com.samcancode.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samcancode.domain.Category;
import com.samcancode.dto.CategoryDto;

public class CategoryToCategoryDtoTest {

    public static final String ID_VALUE = "1";
    public static final String DESCRIPTION = "descript";
    CategoryToCategoryDto convter;

    @BeforeEach
    public void setUp() throws Exception {
        convter = new CategoryToCategoryDto();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(convter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(convter.convert(new Category()));
    }

    @Test
    public void convert() throws Exception {
        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);

        //when
        CategoryDto categoryDto = convter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryDto.getId());
        assertEquals(DESCRIPTION, categoryDto.getDescription());

    }

}