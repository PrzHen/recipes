package recipes.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipes.Recipe;

import java.util.List;
import java.util.Map;

@RestController
class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/recipe")
    public Map<String, Long> postRecipe(@RequestBody Recipe recipe) {
        return recipeService.postRecipe(recipe);
    }

    @PutMapping("/recipe/{id}")
    public HttpStatus updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(id, recipe);
    }

    @GetMapping("/recipe")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable long id) {
        return recipeService.getRecipeById(id);
    }

    @DeleteMapping("/recipe/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
    }

    @RequestMapping("/recipe/byCategory/{category}")
    public List<Recipe> getByCategory(@PathVariable String category) {
        return recipeService.getByCategory(category);
    }

    @RequestMapping("/recipe/byName/{name}")
    public List<Recipe> getByName(@PathVariable String name) {
        return recipeService.getByName(name);
    }

}
