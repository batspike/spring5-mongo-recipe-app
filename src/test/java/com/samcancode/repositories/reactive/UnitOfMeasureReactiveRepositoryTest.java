package com.samcancode.repositories.reactive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.samcancode.domain.UnitOfMeasure;

@SpringBootTest
class UnitOfMeasureReactiveRepositoryTest {

	@Autowired
	UnitOfMeasureReactiveRepository uomReactiveRepo;
	
	@BeforeEach
	void setUp() throws Exception {
		uomReactiveRepo.deleteAll().block();
	}

	@Test
	void testSave() {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription("Each");
		
		uomReactiveRepo.save(uom).block();
		
		Long count = uomReactiveRepo.count().block();
		assertEquals(Long.valueOf(1L), count);
	}

	@Test
	void testFindByDescription() {
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setDescription("Each");
		
		uomReactiveRepo.save(uom).block();
		
		UnitOfMeasure foundUom = uomReactiveRepo.findByDescription("Each").block();
		assertEquals("Each", foundUom.getDescription());
	}

}
