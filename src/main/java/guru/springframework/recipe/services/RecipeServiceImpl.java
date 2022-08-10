package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.mappers.RecipeToCommandMapper;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository repository;

    private final RecipeToCommandMapper mapper;

    public RecipeServiceImpl(RecipeRepository repository, RecipeToCommandMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RecipeCommand save(RecipeCommand recipeCommand) {
        Recipe recipe = mapper.recipeCommandToRecipe(recipeCommand);
        Recipe savedRecipe = repository.save(recipe);
        return mapper.recipeToRecipeCommand(savedRecipe);
    }

    @Override
    public List<RecipeCommand> getAll() {
        List<RecipeCommand> recipeCommands = new ArrayList<>();
        repository.findAll().forEach(recipe -> recipeCommands.add(mapper.recipeToRecipeCommand(recipe)));
        return recipeCommands;
    }

    @Override
    public RecipeCommand getById(Long id) {
        Recipe recipe = repository.findById(id).orElse(null);
        return recipe != null ? mapper.recipeToRecipeCommand(recipe) : null;
    }
}
