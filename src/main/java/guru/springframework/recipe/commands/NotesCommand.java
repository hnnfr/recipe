package guru.springframework.recipe.commands;

import lombok.Data;

@Data
public class NotesCommand {
    private Long id;
    private RecipeCommand recipe;
    private String recipeNotes;
}
