package com.samcancode.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.samcancode.domain.Category;
import com.samcancode.domain.Recipe;
import com.samcancode.dto.RecipeDto;

@Component
public class RecipeToRecipeDto implements Converter<Recipe, RecipeDto>{

    private final CategoryToCategoryDto categoryConveter;
    private final IngredientToIngredientDto ingredientConverter;
    private final NotesToNotesDto notesConverter;

    public RecipeToRecipeDto(CategoryToCategoryDto categoryConveter, IngredientToIngredientDto ingredientConverter,
                                 NotesToNotesDto notesConverter) {
        this.categoryConveter = categoryConveter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeDto convert(Recipe source) {
        if (source == null) {
            return null;
        }

        final RecipeDto command = new RecipeDto();
        command.setId(source.getId());
        command.setCookTime(source.getCookTime());
        command.setPrepTime(source.getPrepTime());
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setDirections(source.getDirections());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setUrl(source.getUrl());
        command.setImage(source.getImage());
        command.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach((Category category) -> command.getCategories().add(categoryConveter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }
}
