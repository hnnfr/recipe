package guru.springframework.recipe.services;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.mappers.IngredientToCommandMapper;
import guru.springframework.recipe.mappers.IngredientToCommandMapperImpl;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository uomRepositoty;

    IngredientToCommandMapper ingredientToCommandMapper;

    IngredientService ingredientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientToCommandMapper = new IngredientToCommandMapperImpl();
        ingredientService = new IngredientServiceImpl(recipeRepository, uomRepositoty, ingredientToCommandMapper);
    }

    @Test
    void testGetByRecipeIdAndIngredientId() {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        // when
        IngredientCommand ingredientCommand = ingredientService.getByRecipeIdAndIngredientId(1L, 3L);

        // then
        assertEquals(3L, ingredientCommand.getId());
        assertEquals(1L, ingredientCommand.getRecipe().getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void testSaveIngredient() {
        // given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
        unitOfMeasure1.setId(1L);
        ingredient1.setUnitOfMeasure(unitOfMeasure1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);
        Optional<UnitOfMeasure> uomOptional = Optional.of(unitOfMeasure1);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(uomRepositoty.findById(anyLong())).thenReturn(uomOptional);
        when(recipeRepository.save(any())).thenReturn(recipe);

        IngredientCommand ingredientCommand = ingredientToCommandMapper.ingredientToIngredientCommand(ingredient1);

        // when
        IngredientCommand savedCommand = ingredientService.saveIngredient(ingredientCommand);

        // then
        assertEquals(ingredientCommand.getId(), savedCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(uomRepositoty, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any());
    }
}