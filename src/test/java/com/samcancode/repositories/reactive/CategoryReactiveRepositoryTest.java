package com.samcancode.repositories.reactive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samcancode.domain.Category;

@SpringBootTest
class CategoryReactiveRepositoryTest {

	@Autowired
	CategoryReactiveRepository categoryReactiveRepo;
	
	@BeforeEach
	void setUp() throws Exception {
		categoryReactiveRepo.deleteAll().block();
	}

	@Test
	void testFindByDescription() {
		Category category = new Category();
		category.setDescription("foo");
		
		categoryReactiveRepo.save(category).block();
		
		Category foundCategory = categoryReactiveRepo.findByDescription("foo").block();
		
		assertNotNull(foundCategory.getId());
	}

	@Test
	void testSave() {
		Category category = new Category();
		category.setDescription("Foo");
		
		categoryReactiveRepo.save(category).block();
		
		Long count = categoryReactiveRepo.count().block();
		assertEquals(Long.valueOf(1L), count);
	}

}
