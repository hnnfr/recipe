package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RecipeServices {

    private final RecipeRepository repository;

    public RecipeServices(RecipeRepository repository) {
        this.repository = repository;
    }

    public Recipe save(Recipe recipe) {
        return repository.save(recipe);
    }

    public List<Recipe> getAll() {
        List<Recipe> recipes = StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return recipes;
    }
}
