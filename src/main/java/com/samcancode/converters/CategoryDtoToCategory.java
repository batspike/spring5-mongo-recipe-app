package com.samcancode.converters;


import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.samcancode.domain.Category;
import com.samcancode.dto.CategoryDto;

@Component
public class CategoryDtoToCategory implements Converter<CategoryDto, Category>{

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryDto source) {
        if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}
