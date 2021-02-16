package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    private IngredientCommandToIngredient ingredientCommandConverter;
    private NotesCommandToNotes notesCommandConverter;
    private CategoryCommandToCategory categoryCommandConverter;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandConverter, NotesCommandToNotes notesCommandConverter, CategoryCommandToCategory categoryCommandConverter) {
        this.ingredientCommandConverter = ingredientCommandConverter;
        this.notesCommandConverter = notesCommandConverter;
        this.categoryCommandConverter = categoryCommandConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setCookTime(source.getCookTime());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());

        Notes notes = notesCommandConverter.convert(source.getNotes());
        if (notes != null) {
            recipe.setNotes(notes);
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredientCommand -> recipe.getIngredients().add(ingredientCommandConverter.convert(ingredientCommand)));
        }

        if(source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories().forEach(categoryCommand -> recipe.getCategories().add(categoryCommandConverter.convert(categoryCommand)));
        }

        return recipe;
    }
}
