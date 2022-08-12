package guru.springframework.recipe.controllers;

import guru.springframework.recipe.services.IngredientService;
import guru.springframework.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable Long recipeId, Model model) {
        log.info("Get list of ingredients for recipe #{}", recipeId);

        model.addAttribute("recipe", recipeService.getById(recipeId));

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String getIngredientByRecipeIdAndId(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
        log.info("get ingredient with recipeId = {}, ingredientId = {}", recipeId, id);

        model.addAttribute("ingredient", ingredientService.getByRecipeIdAndIngredientId(recipeId, id));

        return "recipe/ingredient/show";
    }
}
