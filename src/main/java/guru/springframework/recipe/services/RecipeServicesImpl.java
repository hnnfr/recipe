package guru.springframework.recipe.services;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServicesImpl implements RecipeServices {

    private final RecipeRepository repository;

    public RecipeServicesImpl(RecipeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Recipe save(Recipe recipe) {
        return repository.save(recipe);
    }

    @Override
    public List<Recipe> getAll() {
//        List<Recipe> recipes = StreamSupport
//                .stream(repository.findAll().spliterator(), false)
//                .collect(Collectors.toList());
        List<Recipe> recipes = new ArrayList<>();
        repository.findAll().forEach(recipes::add);
        return recipes;
    }
}
