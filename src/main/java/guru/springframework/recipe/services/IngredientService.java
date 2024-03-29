package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.IngredientCommand;

public interface IngredientService {
    IngredientCommand getByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredient(IngredientCommand ingredientCommand);
    void deleteByRecipeIdAndId(Long recipeId, Long id);
}
