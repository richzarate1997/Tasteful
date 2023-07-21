package tasteful.data;

import org.springframework.data.jpa.repository.JpaRepository;
import tasteful.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}

