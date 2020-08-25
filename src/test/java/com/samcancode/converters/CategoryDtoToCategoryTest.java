package com.samcancode.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samcancode.domain.Category;
import com.samcancode.dto.CategoryDto;

public class CategoryDtoToCategoryTest {

    public static final String ID_VALUE = "1";
    public static final String DESCRIPTION = "description";
    CategoryDtoToCategory conveter;

    @BeforeEach
    public void setUp() throws Exception {
        conveter = new CategoryDtoToCategory();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new CategoryDto()));
    }

    @Test
    public void convert() throws Exception {
        //given
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(ID_VALUE);
        categoryDto.setDescription(DESCRIPTION);

        //when
        Category category = conveter.convert(categoryDto);

        //then
        assertEquals(ID_VALUE, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }

}