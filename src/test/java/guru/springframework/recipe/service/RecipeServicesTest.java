package guru.springframework.recipe.service;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.services.RecipeServices;
import guru.springframework.recipe.services.RecipeServicesImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;

public class RecipeServicesTest {

    private RecipeServices recipeServices;

    @Mock private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeServices = new RecipeServicesImpl(recipeRepository);
    }

    @Test
    void testSave() {
        Recipe recipe = new Recipe();

        when(recipeRepository.save(recipe)).thenReturn(recipe);

        Recipe returnedRecipe = recipeServices.save(recipe);

        assertEquals(recipe, returnedRecipe);
//        verify(recipeRepository, times(1)).save(recipe);
        verify(recipeRepository, times(1)).save(any());
    }
}
