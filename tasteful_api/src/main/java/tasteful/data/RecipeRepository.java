package tasteful.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tasteful.models.Recipe;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
