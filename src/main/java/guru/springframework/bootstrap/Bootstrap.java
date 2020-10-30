package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.IngredientRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public Bootstrap(CategoryRepository categoryRepository, IngredientRepository ingredientRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.debug("Starting Bootstrap");
        createDataSql();
    }

    private void createDataSql(){
        //Create Guacamole Recipe
        Recipe guacamole = new Recipe();
        guacamole.setDescription("Perfect Guacamole");
        guacamole.setCookTime(10);
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setServings(4);
        guacamole.setSource("SimplyRecipes");
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.<br/>" +
                "<br/>" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)<br/>" +
                "<br/>" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.<br/>" +
                "<br/>" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.<br/>" +
                "<br/>" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.<br/>" +
                "<br/>" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.<br/>" +
                "<br/>" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Recipe savedGuacamole = recipeRepository.save(guacamole);

        Set<Category> guacamoleCategories = new HashSet<>();
        Category video = findOrCreateCategoryByDescription("video");

        guacamoleCategories.add(findOrCreateCategoryByDescription("video"));
        guacamoleCategories.add(findOrCreateCategoryByDescription("dip"));
        guacamoleCategories.add(findOrCreateCategoryByDescription("avocado"));
        guacamoleCategories.add(findOrCreateCategoryByDescription("guacamole"));
        guacamole.setCategories(guacamoleCategories);

        guacamole.addIngredient(new Ingredient("ripe avocados",2L, unitOfMeasureRepository.findByDescription("Pieces").get()));
        guacamole.addIngredient(new Ingredient("of salt, more to taste",2L, unitOfMeasureRepository.findByDescription("Teaspoon").get()));
        guacamole.addIngredient(new Ingredient("fresh lime juice or lemon juice",2L, unitOfMeasureRepository.findByDescription("Tablespoon").get()));
        guacamole.addIngredient(new Ingredient("of minced red onion or thinly sliced green onion",2L, unitOfMeasureRepository.findByDescription("Tablespoons").get()));
        recipeRepository.save(guacamole);

        Recipe guacamole2 = new Recipe();
        guacamole2.setDescription("Perfect Guacamole 2");
        guacamole2.setCookTime(15);
        guacamole2.setDifficulty(Difficulty.MODERATE);
        guacamole2.setServings(5);
        guacamole2.setSource("SimplyRecipes");
        guacamole2.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole2.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.<br/>" +
                "<br/>" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)<br/>" +
                "<br/>" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.<br/>" +
                "<br/>" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.<br/>" +
                "<br/>" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.<br/>" +
                "<br/>" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.<br/>" +
                "<br/>" +
                "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");

        Recipe savedGuacamole2 = recipeRepository.save(guacamole2);

        Set<Category> guacamoleCategories2 = new HashSet<>();
        guacamoleCategories2.add(findOrCreateCategoryByDescription("video"));
        guacamoleCategories2.add(findOrCreateCategoryByDescription("dip"));
        guacamoleCategories2.add(findOrCreateCategoryByDescription("avocado"));
        guacamoleCategories2.add(findOrCreateCategoryByDescription("guacamole"));
        guacamole2.setCategories(guacamoleCategories2);

        Set<Ingredient> guacamoleIngredients2 = new HashSet<>();

        guacamoleIngredients2.add(createIngredient("ripe avocados",2L, "Pieces", guacamole2));
        guacamoleIngredients2.add(createIngredient("salt, more to taste",1L, "Teaspoon", guacamole2));
        guacamoleIngredients2.add(createIngredient("fresh lime juice or lemon juice",1L, "Tablespoon", guacamole2));
        guacamoleIngredients2.add(createIngredient("minced red onion or thinly sliced green onion",2L, "Tablespoons", guacamole2));

        guacamole2.setIngredients(guacamoleIngredients2);
        recipeRepository.save(guacamole2);
    }

    private Ingredient createIngredient (String desc, Long amount, String unitOfMeasureDesc, Recipe recipe){
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findByDescription(unitOfMeasureDesc).get();
        Ingredient ingredient = new Ingredient();
        ingredient.setDescription(desc);
        ingredient.setAmount(amount);
        ingredient.setUom(unitOfMeasure);
        ingredient.setRecipe(recipe);
        return ingredientRepository.save(ingredient);
    }

    private Category findOrCreateCategoryByDescription(String description){
        Optional<Category> categoryOptional = categoryRepository.findByDescription(description);
        Category savedCategory = null;
        if(!categoryOptional.isPresent()){
            Category category = new Category();
            category.setDescription(description);
            savedCategory = categoryRepository.save(category);
        }else{
            savedCategory = categoryOptional.get();
        }
        return savedCategory;
    }


}
