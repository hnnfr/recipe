package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.mappers.IngredientToCommandMapper;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository uomRepositoty;
    private final IngredientToCommandMapper ingredientToCommandMapper;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepositoty, IngredientToCommandMapper ingredientToCommandMapper) {
        this.recipeRepository = recipeRepository;
        this.uomRepositoty = uomRepositoty;
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

    @Override
    public IngredientCommand saveIngredient(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if (!recipeOptional.isPresent()) {
            log.error("Recipe not found for Id {}", ingredientCommand.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients()
                                                        .stream()
                                                        .filter(ing -> ing.getId().equals(ingredientCommand.getId()))
                                                        .findFirst();

            // Notes: ! or no ! => the test unit with mock can detect this
            // A good point to show if in a training session
            if (ingredientOptional.isPresent()) {
                Ingredient ingredient = ingredientOptional.get();
                ingredient.setDescription(ingredientCommand.getDescription());
                ingredient.setAmount(ingredientCommand.getAmount());
                ingredient.setUnitOfMeasure(uomRepositoty.findById(ingredientCommand.getUnitOfMeasure().getId())
                                                .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
            } else {
                recipe.addIngredient(ingredientToCommandMapper.ingredientCommandToIngredient(ingredientCommand));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            return ingredientToCommandMapper.ingredientToIngredientCommand(
                    savedRecipe.getIngredients()
                            .stream()
                            .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                            .findFirst()
                            .orElse(null));
        }
    }
}
