package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.mappers.RecipeToCommandMapper;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.services.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeServiceTest {

    private static final String NEW_DESCRIPTION = "new description";
    @Autowired
    RecipeToCommandMapper mapper;

    @Autowired
    RecipeRepository repository;

    @Autowired
    RecipeService recipeService;

    @Transactional
    @Test
    void testSave() {
        // given
        Iterable<Recipe> recipes = repository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand recipeCommand = mapper.recipeToRecipeCommand(testRecipe);

        // when
        recipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand returnedCommand = recipeService.save(recipeCommand);

        // then
        assertEquals(NEW_DESCRIPTION, returnedCommand.getDescription());
        assertEquals(recipeCommand.getId(), returnedCommand.getId());
        assertEquals(recipeCommand.getCategories().size(), returnedCommand.getCategories().size());
        assertEquals(recipeCommand.getIngredients().size(), returnedCommand.getIngredients().size());
    }
}
