package recipes.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.Recipe;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Map<String, Long> postRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return Collections.singletonMap("id", recipe.getId());
    }

    public HttpStatus updateRecipe(Long id, Recipe newRecipe) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        recipe.setName(newRecipe.getName());
        recipe.setCategory(newRecipe.getCategory());
        recipe.setDescription(newRecipe.getDescription());
        recipe.setIngredients(newRecipe.getIngredients());
        recipe.setDirections(newRecipe.getDirections());

        recipeRepository.save(recipe);

        return HttpStatus.NO_CONTENT;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public ResponseEntity<Recipe> getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.ok().body(recipe);
    }

    public List<Recipe> getByCategory(String category) {
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByLastModifiedDesc(category);
    }

    public List<Recipe> getByName(String name) {
        return recipeRepository.findAllByNameContainingIgnoreCaseOrderByLastModifiedDesc(name);
    }

    public void deleteRecipe(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        recipeRepository.delete(recipe);
    }
}
