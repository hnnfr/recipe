package guru.springframework.recipe.controllers;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    private IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    void testMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getIndexPage() {
        RecipeCommand recipe = new RecipeCommand();
        List<RecipeCommand> recipes = new ArrayList<>();
        recipes.add(recipe);
        when(recipeService.getAll()).thenReturn(recipes);

        String pageName = indexController.getIndexPage(model);
        assertEquals("index", pageName);
        verify(recipeService, times(1)).getAll();
        verify(model, times(1)).addAttribute(eq("recipes"), anyList());
//        verify(model, times(1)).addAttribute("recipes", recipeService.getAll());

        // Fail of course => and I will give explanation!
//        List<Recipe> recipeList = (List<Recipe>) model.getAttribute("recipeList");
//        assertEquals(1, recipeList.size());
//        assertEquals(recipe, recipeList.get(0));
    }
}