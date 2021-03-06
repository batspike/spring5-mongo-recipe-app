package com.samcancode.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.samcancode.domain.Ingredient;
import com.samcancode.domain.Recipe;
import com.samcancode.dto.IngredientDto;

@Component
public class IngredientDtoToIngredient implements Converter<IngredientDto, Ingredient> {

    private final UnitOfMeasureDtoToUnitOfMeasure uomConverter;

    public IngredientDtoToIngredient(UnitOfMeasureDtoToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientDto source) {
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());

        if(source.getRecipeId() != null){
            Recipe recipe = new Recipe();
            recipe.setId(source.getRecipeId());
//            ingredient.setRecipe(recipe); // remove for mongodb
            recipe.addIngredient(ingredient);
        }

        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUom()));
        return ingredient;
    }
}
