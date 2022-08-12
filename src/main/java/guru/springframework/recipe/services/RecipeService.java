package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.RecipeCommand;

import java.util.List;

public interface RecipeService {
    RecipeCommand save(RecipeCommand recipe);

    List<RecipeCommand> getAll();

    RecipeCommand getById(Long id);

    void deleteById(Long id);
}
