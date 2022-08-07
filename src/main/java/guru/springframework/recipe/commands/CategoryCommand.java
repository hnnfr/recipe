package guru.springframework.recipe.commands;

import lombok.Data;

import java.util.Set;

@Data
public class CategoryCommand {
    private Long id;
    private String description;
    private Set<RecipeCommand> recipes;
}
