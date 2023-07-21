package tasteful.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    @Column(nullable = false)
    @NotBlank(message = "Recipe name cannot be blank.")
    @Size(max = 50, message = "Recipe name must be less than 50 characters.")
    private String title;

    @Column
    @NotBlank(message = "Recipe instructions cannot be blank.")
    @Size(max = 1000, message = "Recipe instructions must be 1000 characters or less.")
    private String instructions;

    @Min(value = 1, message = "Recipe servings cannot be less than 1.")
    @Max(value = 50, message = "Recipe servings cannot be greater than 50.")
    private int servings;

    @Column
    @Min(value = 1, message = "Recipe cook time cannot be less than 1 minute.")
    @Max(value = 25200, message = "Recipe cook time cannot be greater than 1 week.")
    private Integer timeRequired;

    @Column(precision = 5, scale = 2)
    private BigDecimal calories;

    @Column(precision = 5, scale = 2)
    private BigDecimal carbs;

    @Column(precision = 5, scale = 2)
    private BigDecimal protein;

    @Column(precision = 5, scale = 2)
    private BigDecimal fat;

    @NotNull
    @Size(max = 255, message = "Recipe source url too long.")
    private String sourceUrl;
    @Lob
    @Column
    private byte [] image;
    @Column
    private boolean vegetarian;
    @Column
    private boolean vegan;
    @Column
    private boolean glutenFree;
    @Column
    private boolean dairyFree;


    @OneToMany // Or another appropriate relationship annotation
    @NotNull(message = "Ingredients list cannot be null.")
    @Size(min = 1, max=50, message = "Recipe ingredient count must be between 1 and 50 ingredients.")
    @Column
    private List<RecipeIngredient> ingredients = new ArrayList<>();


    @OneToMany // Or another appropriate relationship annotation
    @NotNull(message = "Cuisine list cannot be null.")
    @Size(max = 4, message = "Whoa, calm down! Fusion Confusion! Keep it under 5 cuisines.")
    @Column
    private List<CuisineType> cuisines = new ArrayList<>();


    public Recipe() {

    }

    // getters and setters ...


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public Integer getTimeRequired() {
        return timeRequired;
    }

    public void setTimeRequired(Integer timeRequired) {
        this.timeRequired = timeRequired;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }

    public BigDecimal getCarbs() {
        return carbs;
    }

    public void setCarbs(BigDecimal carbs) {
        this.carbs = carbs;
    }

    public BigDecimal getProtein() {
        return protein;
    }

    public void setProtein(BigDecimal protein) {
        this.protein = protein;
    }

    public BigDecimal getFat() {
        return fat;
    }

    public void setFat(BigDecimal fat) {
        this.fat = fat;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<CuisineType> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<CuisineType> cuisines) {
        this.cuisines = cuisines;
    }
}

