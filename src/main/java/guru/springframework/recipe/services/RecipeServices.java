package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;

import java.util.List;

public interface RecipeServices {
    Recipe save(Recipe recipe);

    List<Recipe> getAll();
}
