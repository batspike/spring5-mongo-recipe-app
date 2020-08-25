package com.samcancode.repositories;

import org.springframework.data.repository.CrudRepository;

import com.samcancode.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, String> {

    Optional<Category> findByDescription(String description);
}
