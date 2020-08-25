package com.samcancode.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.samcancode.domain.Notes;
import com.samcancode.dto.NotesDto;

public class NotesDtoToNotesTest {

    public static final String ID_VALUE = "1";
    public static final String RECIPE_NOTES = "Notes";
    NotesDtoToNotes converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new NotesDtoToNotes();

    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new NotesDto()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesDto notesDto = new NotesDto();
        notesDto.setId(ID_VALUE);
        notesDto.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = converter.convert(notesDto);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }

}