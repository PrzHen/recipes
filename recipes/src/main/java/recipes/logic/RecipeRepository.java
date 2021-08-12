package recipes.logic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.Recipe;

import java.util.List;

@Repository
interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findAllByCategoryIgnoreCaseOrderByLastModifiedDesc(String category);

    List<Recipe> findAllByNameContainingIgnoreCaseOrderByLastModifiedDesc(String name);

}
