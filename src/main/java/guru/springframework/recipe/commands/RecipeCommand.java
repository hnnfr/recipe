package guru.springframework.recipe.commands;

import guru.springframework.recipe.domain.Difficulty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;

    private Difficulty difficulty;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();
    private NotesCommand notes;
}
