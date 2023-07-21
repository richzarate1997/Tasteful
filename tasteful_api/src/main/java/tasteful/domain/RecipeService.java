package tasteful.domain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tasteful.data.DataException;
import tasteful.data.RecipeIngredientRepository;
import tasteful.data.RecipeRepository;
import tasteful.models.Recipe;
import tasteful.models.RecipeIngredient;

import javax.validation.Validator;
import java.time.DateTimeException;
import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final Validator validator;

    public RecipeService(RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository, Validator validator) {
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.validator = validator;
    }

    @Transactional
    public Result<Recipe> save(Recipe recipe) throws DataException {
        Result<Recipe> result = validate(recipe);

        if (!result.isSuccess()) {
            return result;
        }

        if (recipe.getId() > 0) {
            result.addMessage("Cannot create existing recipe.");
            return result;
        }

        recipe = recipeRepository.save(recipe);
        result.setPayload(recipe);
        return result;
    }
    @Transactional
    public Recipe update(Recipe recipe) {
        // Add any business logic here, e.g. validation
        return recipeRepository.save(recipe);
    }

//    @Transactional
//    public Result<Recipe> deleteById(int recipeId) {
//        Result<Recipe> result = new Result<>();
//        if (!recipeRepository.delete(recipeId)) {
//            result.addMessage(String.format("Recipe %s does not exist.", recipeId), ResultType.NOT_FOUND);
//        }
//        return result;
//    }

    public Recipe findById(Integer id) {
        // You could handle the case where no Recipe is found here,
        // or let it propagate as an exception to be handled elsewhere
        return recipeRepository.findById(id).orElseThrow(() -> new DateTimeException("Recipe not found"));
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    private Result<Recipe> validate(Recipe recipe) {
        Result<Recipe> result = new Result<>();

        if (recipe == null) {
            result.addMessage("Recipe cannot be null.");
            return result;
        }

        for (var violation: validator.validate(recipe)) {
            result.addMessage(violation.getMessage());
        }

        return result;
    }

    private Result<Recipe> validate(RecipeIngredient recipeIngredient, Result<Recipe> result) {

        if (recipeIngredient == null) {
            result.addMessage("Cannot add a null ingredient to recipe.", ResultType.INVALID);
        }

        for (var violation : validator.validate(recipeIngredient)) {
            result.addMessage(violation.getMessage());
        }

        return result;
    }
}


