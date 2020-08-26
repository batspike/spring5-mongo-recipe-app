package com.samcancode.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samcancode.domain.Ingredient;
import com.samcancode.domain.Recipe;
import com.samcancode.domain.UnitOfMeasure;
import com.samcancode.dto.IngredientDto;

public class IngredientToIngredientDtoTest {

    public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Cheeseburger";
    public static final String UOM_ID = "2";
    public static final String ID_VALUE = "1";


    IngredientToIngredientDto converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new IngredientToIngredientDto(new UnitOfMeasureToUnitOfMeasureDto());
    }

    @Test
    public void testNullConvert() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void testConvertNullUOM() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
//        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setUom(null);
        //when
        IngredientDto ingredientDto = converter.convert(ingredient);
        //then
        assertNull(ingredientDto.getUom());
        assertEquals(ID_VALUE, ingredientDto.getId());
        assertEquals(AMOUNT, ingredientDto.getAmount());
        assertEquals(DESCRIPTION, ingredientDto.getDescription());
    }

    @Test
    public void testConvertWithUom() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
//        ingredient.setRecipe(RECIPE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(UOM_ID);

        ingredient.setUom(uom);
        //when
        IngredientDto ingredientDto = converter.convert(ingredient);
        //then
        assertEquals(ID_VALUE, ingredientDto.getId());
        assertNotNull(ingredientDto.getUom());
        assertEquals(UOM_ID, ingredientDto.getUom().getId());
        // assertEquals(RECIPE, ingredientDto.get);
        assertEquals(AMOUNT, ingredientDto.getAmount());
        assertEquals(DESCRIPTION, ingredientDto.getDescription());
    }
}