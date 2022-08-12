package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.mappers.IngredientToCommandMapper;
import guru.springframework.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToCommandMapper ingredientToCommandMapper;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToCommandMapper ingredientToCommandMapper) {
        this.recipeRepository = recipeRepository;
        this.ingredientToCommandMapper = ingredientToCommandMapper;
    }

    @Override
    public IngredientCommand getByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            log.error("Not found recipe with id #{}", recipeId);
            return null;
        }
        Recipe recipe = recipeOptional.get();
        Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                                                    .stream()
                                                    .filter(ing -> ing.getId().equals(ingredientId))
                                                    .findFirst();
        if (!ingredientOptional.isPresent()) {
            log.error("Not found ingredient with id #{}", ingredientId);
            return null;
        }
        return this.ingredientToCommandMapper.ingredientToIngredientCommand(ingredientOptional.get());
    }
}
