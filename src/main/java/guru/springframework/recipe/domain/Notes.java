package guru.springframework.recipe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Notes {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Recipe recipe;

    /* Notes:
     * Using @MapsId like above in the case where the table NOTES use RECIPE_ID as its Primary Key
     * (saving one column ID, and one index that is automatically created with Primary Key or Foreign Key)
     *
     * Using @Id with @GeneratedValue and @JoinColumn as below in the case the table NOTES has its own
     * column ID as Primary Key, and also RECIPE_ID column as Foreign Key. Maybe reasonable in some case,
     * but clearly less performance than @MapsId (from the database point of view).
     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
//    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
