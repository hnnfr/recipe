package guru.springframework.recipe.mappers;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Recipe;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IngredientToCommandMapper {

    IngredientCommand ingredientToIngredientCommand(Ingredient entity);
    Ingredient ingredientCommandToIngredient(IngredientCommand command);

    @Mapping(target = "ingredients", ignore = true)
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "categories", ignore = true)
    RecipeCommand recipeToRecipeCommand(Recipe recipe);

    @Mapping(target = "ingredients", ignore = true)
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Recipe recipeCommandToRecipe(RecipeCommand recipeCommand);
}
