package guru.springframework.recipe.controllers;

import guru.springframework.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({ "", "/", "/index" })
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getAll());

        return "index";
    }
}
