package com.samcancode.domain;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notes {
	@Id
    private String id;
//    private Recipe recipe; // remove for mongodb
    private String recipeNotes;
}
