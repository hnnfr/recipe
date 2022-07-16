package guru.springframework.recipe.bootstrap;

import guru.springframework.recipe.domain.Difficulty;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.CategoryRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository uomRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        Recipe recipeGuacamole = new Recipe();
        recipeGuacamole.setDescription("How to Make the Best Guacamole");
        recipeGuacamole.setPrepTime(10);
        recipeGuacamole.setPrepTime(10);
        recipeGuacamole.setServings(4);
        recipeGuacamole.setSource("simplyrecipes.com");
        recipeGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipeGuacamole.setDifficulty(Difficulty.EASY);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription("Avocat");
        ingredient1.setAmount(BigDecimal.valueOf(2));
        ingredient1.setUnitOfMeasure(uomRepository.findByDescription("Ripe").get());
        ingredient1.setRecipe(recipeGuacamole);

        recipeGuacamole.getIngredients().add(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setDescription("kosher salt");
        ingredient2.setAmount(BigDecimal.valueOf(0.25));
        ingredient2.setUnitOfMeasure(uomRepository.findByDescription("Teaspoon").get());
        ingredient2.setRecipe(recipeGuacamole);

        recipeGuacamole.getIngredients().add(ingredient2);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setDescription("fresh lime or lemon juice");
        ingredient3.setAmount(BigDecimal.valueOf(1));
        ingredient3.setUnitOfMeasure(uomRepository.findByDescription("Tablespoon").get());
        ingredient3.setRecipe(recipeGuacamole);

        recipeGuacamole.getIngredients().add(ingredient3);

        Ingredient ingredient4 = new Ingredient();
        ingredient4.setDescription("minced red onion or thinly sliced green onion");
        ingredient4.setAmount(BigDecimal.valueOf(3));
        ingredient4.setUnitOfMeasure(uomRepository.findByDescription("Tablespoon").get());
        ingredient4.setRecipe(recipeGuacamole);

        recipeGuacamole.getIngredients().add(ingredient4);

        Ingredient ingredient5 = new Ingredient();
        ingredient5.setDescription("chilis, stems and seeds removed, minced");
        ingredient5.setAmount(BigDecimal.valueOf(2));
        ingredient5.setUnitOfMeasure(uomRepository.findByDescription("Serrano").get());
        ingredient5.setRecipe(recipeGuacamole);

        recipeGuacamole.getIngredients().add(ingredient5);

        // Step-by-step: Add Note
        Notes notes = new Notes();
        notes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");
        notes.setRecipe(recipeGuacamole);

        recipeGuacamole.setNotes(notes);

        // Step-by-step: Add Category
        recipeGuacamole.getCategories().add(categoryRepository.findByDescription("Mexican").get());

        recipeRepository.save(recipeGuacamole);
    }
}
