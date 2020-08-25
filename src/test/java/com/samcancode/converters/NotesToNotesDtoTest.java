package com.samcancode.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samcancode.domain.Notes;
import com.samcancode.dto.NotesDto;

public class NotesToNotesDtoTest {

    public static final String ID_VALUE = "1";
    public static final String RECIPE_NOTES = "Notes";
    NotesToNotesDto converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new NotesToNotesDto();
    }

    @Test
    public void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesDto notesDto = converter.convert(notes);

        //then
        assertEquals(ID_VALUE, notesDto.getId());
        assertEquals(RECIPE_NOTES, notesDto.getRecipeNotes());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Notes()));
    }
}