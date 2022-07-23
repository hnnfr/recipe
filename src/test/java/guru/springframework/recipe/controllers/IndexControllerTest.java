package guru.springframework.recipe.controllers;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.services.RecipeServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    private IndexController indexController;

    @Mock RecipeServices recipeServices;

    @Mock Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(recipeServices);
    }

    @Test
    void getIndexPage() {
        Recipe recipe = new Recipe();
        List<Recipe> recipes = new ArrayList<>();
        when(recipeServices.getAll()).thenReturn(recipes);

        String pageName = indexController.getIndexPage(model);
        assertEquals("index", pageName);
        verify(recipeServices, times(1)).getAll();
        verify(model, times(1)).addAttribute(eq("recipeList"), anyList());
//        verify(model, times(1)).addAttribute("recipeList", recipeServices.getAll());

        // Fail of course => and I will give explanation!
//        List<Recipe> recipeList = (List<Recipe>) model.getAttribute("recipeList");
//        assertEquals(1, recipeList.size());
//        assertEquals(recipe, recipeList.get(0));
    }
}