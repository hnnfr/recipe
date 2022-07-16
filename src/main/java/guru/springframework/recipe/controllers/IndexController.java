package guru.springframework.recipe.controllers;

import guru.springframework.recipe.services.RecipeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final RecipeServices recipeServices;

    public IndexController(RecipeServices recipeServices) {
        this.recipeServices = recipeServices;
    }

    @RequestMapping({ "", "/", "/index" })
    public String getIndexPage(Model model) {

        model.addAttribute("recipeList", recipeServices.getAll());

        return "index";
    }
}
