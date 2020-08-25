package com.samcancode.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.samcancode.converters.RecipeDtoToRecipe;
import com.samcancode.converters.RecipeToRecipeDto;
import com.samcancode.domain.Recipe;
import com.samcancode.dto.RecipeDto;
import com.samcancode.exceptions.NotFoundException;
import com.samcancode.repositories.RecipeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeDto recipeToRecipeDto;

    @Mock
    RecipeDtoToRecipe recipeDtoToRecipe;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeDtoToRecipe, recipeToRecipeDto);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById("1");

        assertNotNull(recipeReturned, "Null recipe returned");
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipeByIdTestNotFound() {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        assertThrows( NotFoundException.class, () -> recipeService.findById("1") );
    }

    @Test
    public void getRecipeDtoByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);

        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setId("1");

        when(recipeToRecipeDto.convert(any())).thenReturn(recipeDto);

        RecipeDto commandById = recipeService.findDtoById("1");

        assertNotNull(commandById, "Null recipe returned");
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, never()).findAll();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
    public void getRecipesTest() throws Exception {

        Recipe recipe = new Recipe();
		HashSet receipesData = new HashSet();
        receipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyString());
    }

    @Test
    public void testDeleteById() throws Exception {

        //given
        String idToDelete = "2";

        //when
        recipeService.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(recipeRepository, times(1)).deleteById(anyString());
    }
}