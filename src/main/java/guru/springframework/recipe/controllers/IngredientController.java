package guru.springframework.recipe.controllers;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.services.IngredientService;
import guru.springframework.recipe.services.RecipeService;
import guru.springframework.recipe.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
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

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateIngredient(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
        log.info("update ingredient {} of recipe {}", id, recipeId);

        model.addAttribute("ingredient", ingredientService.getByRecipeIdAndIngredientId(recipeId, id));
        model.addAttribute("unitOfMeasureList", uomService.getAllUnitOfMeasures());

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
        IngredientCommand savedCommand = ingredientService.saveIngredient(ingredientCommand);

        log.info("Saved ingredient {} of recipe {}", savedCommand.getId(), savedCommand.getRecipeId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
        log.info("Delete ingredient {}", id);

        ingredientService.deleteByRecipeIdAndId(recipeId, id);

        return "redirect:/recipe/" + recipeId + "/ingredients";

        /* We can do as following:
         * Request the recipe by recipeId, put it in the model, and then render the view directly
         * (list of ingredients for the recipe in this case).
         * Doing that, Spring will not pass again by DispatcherSevlet, then select Controller's method
         * by given URL. But the method is doing 2 things as a time (deleting ingredient and rendering
         * view on recipe's ingredients) => it will be harder for testing and maintaining code.
         * (Think about the case where view to be rendered is in another controller)
         * Better use redirect here!
         */
//        model.addAttribute("recipe", recipeService.getById(recipeId));
//        return "recipe/ingredient/list";

    }
}
