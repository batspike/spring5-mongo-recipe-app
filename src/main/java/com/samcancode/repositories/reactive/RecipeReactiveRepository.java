package com.samcancode.repositories.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.samcancode.domain.Recipe;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {

}
