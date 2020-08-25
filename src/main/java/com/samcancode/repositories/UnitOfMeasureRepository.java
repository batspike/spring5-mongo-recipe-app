package com.samcancode.repositories;

import org.springframework.data.repository.CrudRepository;

import com.samcancode.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, String> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
