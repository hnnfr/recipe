package guru.springframework.recipe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Notes {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
//    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    @MapsId
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
