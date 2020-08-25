package com.samcancode.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.samcancode.dto.IngredientDto;
import com.samcancode.dto.RecipeDto;
import com.samcancode.dto.UnitOfMeasureDto;
import com.samcancode.services.IngredientService;
import com.samcancode.services.RecipeService;
import com.samcancode.services.UnitOfMeasureService;

@Slf4j
@Controller
public class IngredientController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findDtoById(recipeId));

        return "recipe/ingredient/list";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showRecipeIngredient(@PathVariable String recipeId,
                                       @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));
        return "recipe/ingredient/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable String recipeId, Model model){

        //make sure we have a good id value
		@SuppressWarnings("unused")
		RecipeDto recipeDto = recipeService.findDtoById(recipeId);
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setRecipeId(recipeId);
        model.addAttribute("ingredient", ingredientDto);

        //init uom
        ingredientDto.setUom(new UnitOfMeasureDto());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));

        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientDto command){
        IngredientDto savedDto = ingredientService.saveIngredientDto(command);

        log.debug("saved receipe id:" + savedDto.getRecipeId());
        log.debug("saved ingredient id:" + savedDto.getId());

        return "redirect:/recipe/" + savedDto.getRecipeId() + "/ingredient/" + savedDto.getId() + "/show";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id){

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(recipeId, id);

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
