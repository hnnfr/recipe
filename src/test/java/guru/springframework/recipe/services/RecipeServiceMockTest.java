package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.mappers.RecipeToCommandMapper;
import guru.springframework.recipe.mappers.RecipeToCommandMapperImpl;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.services.RecipeService;
import guru.springframework.recipe.services.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

class RecipeServiceMockTest {

    private RecipeService recipeService;

    @Mock private RecipeRepository recipeRepository;

    private RecipeToCommandMapper recipeToCommandMapper;

    @BeforeEach
    void setUp() {
        recipeToCommandMapper = new RecipeToCommandMapperImpl();
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeToCommandMapper);
    }

    @Test
    void testSave() {
        RecipeCommand recipeCommand = new RecipeCommand();
        Recipe recipe = recipeToCommandMapper.recipeCommandToRecipe(recipeCommand);
        when(recipeRepository.save(any())).thenReturn(recipe);

        // when we use this command, the test will false => because Mockito must compare 2 recipes with id = null
        // => it will not found the right recipe to mock => the method 'save' of the mock will return null
//        when(recipeRepository.save(recipe)).thenReturn(recipe);

        RecipeCommand returnedRecipe = recipeService.save(recipeCommand);

        assertEquals(recipeCommand, returnedRecipe);
//        verify(recipeRepository, times(1)).save(recipe);
        verify(recipeRepository, times(1)).save(any());
    }
}
