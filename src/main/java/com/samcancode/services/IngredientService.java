package com.samcancode.services;

import com.samcancode.dto.IngredientDto;

public interface IngredientService {
    IngredientDto findByRecipeIdAndIngredientId(String recipeId, String ingredientId);
    IngredientDto saveIngredientDto(IngredientDto command);
    void deleteById(String recipeId, String idToDelete);
}
