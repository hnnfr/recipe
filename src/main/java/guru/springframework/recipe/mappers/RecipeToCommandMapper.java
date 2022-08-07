package guru.springframework.recipe.mappers;

import guru.springframework.recipe.commands.CategoryCommand;
import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.commands.NotesCommand;
import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.domain.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecipeToCommandMapper {

    RecipeCommand recipeToRecipeCommand(Recipe recipe);
    Recipe recipeCommandToRecipe(RecipeCommand recipeCommand);

    @Mapping(target = "recipe.ingredients", ignore = true)
    @Mapping(target = "recipe.notes", ignore = true)
    @Mapping(target = "recipes", ignore = true)
    CategoryCommand categoryToCategoryCommand(Category category);

    @Mapping(target = "recipe.ingredients", ignore = true)
    @Mapping(target = "recipe.notes", ignore = true)
    @Mapping(target = "recipes", ignore = true)
    Category categoryCommandToCategory(CategoryCommand categoryCommand);

    @Mapping(target = "recipe.ingredients", ignore = true)
    @Mapping(target = "recipe.notes", ignore = true)
    @Mapping(target = "recipe.categories", ignore = true)
    IngredientCommand ingredientToIngredientCommand(Ingredient ingredient);

    @Mapping(target = "recipe.ingredients", ignore = true)
    @Mapping(target = "recipe.notes", ignore = true)
    @Mapping(target = "recipe.categories", ignore = true)
    Ingredient ingredientCommandToIngredient(IngredientCommand ingredientCommand);

    @Mapping(target = "recipe.ingredients", ignore = true)
    @Mapping(target = "recipe.notes", ignore = true)
    @Mapping(target = "recipe.categories", ignore = true)
    NotesCommand notesToNotesCommand(Notes notes);

    @Mapping(target = "recipe.ingredients", ignore = true)
    @Mapping(target = "recipe.notes", ignore = true)
    @Mapping(target = "recipe.categories", ignore = true)
    Notes notesCommandToNotes(NotesCommand notesCommand);
}
