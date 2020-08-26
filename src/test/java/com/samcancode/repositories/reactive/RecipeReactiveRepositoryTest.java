package com.samcancode.repositories.reactive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samcancode.domain.Recipe;

@SpringBootTest
class RecipeReactiveRepositoryTest {

	@Autowired
	RecipeReactiveRepository recipeReactiveRepo;
	
	@BeforeEach
	void setUp() throws Exception {
		recipeReactiveRepo.deleteAll().block();
	}

	@Test
	void testSave() {
		Recipe recipe = new Recipe();
		recipe.setDescription("Yummy");
		
		recipeReactiveRepo.save(recipe).block();
		
		Long count = recipeReactiveRepo.count().block();
		assertEquals(Long.valueOf(1L), count);
	}

}
