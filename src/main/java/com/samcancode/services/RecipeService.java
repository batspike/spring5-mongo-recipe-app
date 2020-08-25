package com.samcancode.services;


import java.util.Set;

import com.samcancode.domain.Recipe;
import com.samcancode.dto.RecipeDto;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(String id);
    RecipeDto findDtoById(String id);
    RecipeDto saveRecipeDto(RecipeDto command);
    void deleteById(String idToDelete);
}
