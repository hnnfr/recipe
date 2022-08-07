package guru.springframework.recipe.mappers;

import guru.springframework.recipe.commands.NotesCommand;
import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.domain.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotesToCommandMapper {

    NotesCommand notesToNotesCommand(Notes notes);
    Notes notesCommandToNotes(NotesCommand notesCommand);

    @Mapping(target = "ingredients", ignore = true)
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "categories", ignore = true)
    RecipeCommand recipeToRecipeCommand(Recipe recipe);

    @Mapping(target = "ingredients", ignore = true)
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Recipe recipeCommandToRecipe(RecipeCommand recipeCommand);
}
