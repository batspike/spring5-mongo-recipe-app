package com.samcancode.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samcancode.domain.Ingredient;
import com.samcancode.domain.Recipe;
import com.samcancode.dto.IngredientDto;
import com.samcancode.dto.UnitOfMeasureDto;

public class IngredientDtoToIngredientTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final String ID_VALUE = "1";
    public static final String UOM_ID = "2";

    IngredientDtoToIngredient converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new IngredientDtoToIngredient(new UnitOfMeasureDtoToUnitOfMeasure());
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientDto()));
    }

    @Test
    public void convert() throws Exception {
        //given
        IngredientDto command = new IngredientDto();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureDto unitOfMeasureDto = new UnitOfMeasureDto();
        unitOfMeasureDto.setId(UOM_ID);
        command.setUom(unitOfMeasureDto);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    @SuppressWarnings("unused")
	@Test
    public void convertWithNullUOM() throws Exception {
        //given
        IngredientDto command = new IngredientDto();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureDto unitOfMeasureDto = new UnitOfMeasureDto();


        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
    }

}