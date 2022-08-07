package guru.springframework.recipe.mappers;

import guru.springframework.recipe.commands.CategoryCommand;
import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryToCommandMapper {

    CategoryCommand categoryToCategoryCommand(Category category);
    Category categoryCommandToCategory(CategoryCommand categoryCommand);

    @Mapping(target = "ingredients", ignore = true)
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "categories", ignore = true)
    RecipeCommand recipeToRecipeCommand(Recipe recipe);

    @Mapping(target = "ingredients", ignore = true)
    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Recipe recipeCommandToRecipe(RecipeCommand recipeCommand);
}
