package com.samcancode.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.samcancode.domain.Ingredient;
import com.samcancode.dto.IngredientDto;

@Component
public class IngredientToIngredientDto implements Converter<Ingredient, IngredientDto> {

    private final UnitOfMeasureToUnitOfMeasureDto uomConverter;

    public IngredientToIngredientDto(UnitOfMeasureToUnitOfMeasureDto uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientDto convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setId(ingredient.getId());
//        if (ingredient.getRecipe() != null) { //remove for mongo db
//            ingredientDto.setRecipeId(ingredient.getRecipe().getId());
//        }
        ingredientDto.setAmount(ingredient.getAmount());
        ingredientDto.setDescription(ingredient.getDescription());
        ingredientDto.setUom(uomConverter.convert(ingredient.getUom()));
        return ingredientDto;
    }
}
