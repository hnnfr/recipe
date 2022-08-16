package guru.springframework.recipe.controllers;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.services.IngredientService;
import guru.springframework.recipe.services.RecipeService;
import guru.springframework.recipe.services.UnitOfMeasureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    IngredientController ingredientController;

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService uomService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredientController = new IngredientController(recipeService, ingredientService, uomService);
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void testListIngredients() throws Exception {
        // given
        RecipeCommand recipeCommand = new RecipeCommand();
        when(recipeService.getById(anyLong())).thenReturn(recipeCommand);
//        when(recipeService.getById(anyLong())).thenReturn(null);  // in this case, the attribute will not be added into the model

        // when
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        // then
        verify(recipeService, times(1)).getById(anyLong());
    }

    @Test
    void testGetIngredientByRecipeIdAndId() throws Exception {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        when(ingredientService.getByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        // when
        mockMvc.perform(get("/recipe/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));

        // then
        verify(ingredientService, times(1)).getByRecipeIdAndIngredientId(anyLong(), anyLong());
    }

    @Test
    void testUpdateIngredient() throws Exception {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        when(ingredientService.getByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

        Set<UnitOfMeasureCommand> uoms = new HashSet<>();
        UnitOfMeasureCommand unitOfMeasureCommand1 = new UnitOfMeasureCommand();
        unitOfMeasureCommand1.setId(1L);
        uoms.add(unitOfMeasureCommand1);
        UnitOfMeasureCommand unitOfMeasureCommand2 = new UnitOfMeasureCommand();
        unitOfMeasureCommand2.setId(2L);
        uoms.add(unitOfMeasureCommand2);
        when(uomService.getAllUnitOfMeasures()).thenReturn(uoms);

        // when
        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient", "unitOfMeasureList"));

        // then
        verify(ingredientService, times(1)).getByRecipeIdAndIngredientId(anyLong(), anyLong());
        verify(uomService, times(1)).getAllUnitOfMeasures();
    }

    @Test
    void testSaveOrUpdate() throws Exception {
        // given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(1L);
        ingredientCommand.setId(2L);
        when(ingredientService.saveIngredient(any())).thenReturn(ingredientCommand);

        // when
        mockMvc.perform(post("/recipe/1/ingredient"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredient/2/show"));

        // then
        verify(ingredientService, times(1)).saveIngredient(any());
    }
}