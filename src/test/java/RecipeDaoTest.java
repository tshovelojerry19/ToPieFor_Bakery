//
//import com.topiefor.dao.RecipeDao;
//import com.topiefor.dao.impl.RecipeDaoImpl;
//import com.topiefor.models.Ingredient;
//import com.topiefor.models.Recipe;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RecipeDaoTest {
//
//    private Connection con = null;
//
//    public RecipeDaoTest() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/to_pie_for_db_test", "root", "root"
//            );
//        } catch (ClassNotFoundException | SQLException cle) {
//        }
//        if (con != null) {
//            System.out.println("Got connection");
//        }
//    }
//
//    @Test
//    public void getAllRecipesTest() {
//
//        RecipeDao recipe = RecipeDaoImpl.getInstance(con);
//        assertTrue(recipe.getAllRecipies().size() > 0);
//    }
//
////   @Test
////    public void addRecipeTest() {
////
////        List<Ingredient> listOfIngredientDaoImpls = new ArrayList<>();
////        listOfIngredientDaoImpls.add(new Ingredient(3, 10));
////        listOfIngredientDaoImpls.add(new Ingredient(4, 2));
////        Recipe recipe1 = new Recipe(0, "10 kg Flour,  ", " Cake", true, listOfIngredientDaoImpls);
////        RecipeDao recipe = RecipeDaoImpl.getInstance(con);
////
////        assertTrue(recipe.addRecipe(recipe1));
////
////    }
////    @Test
////    public void editRecipeTest() {
////
////        List<Ingredient> listOfIngredientDaoImpls = new ArrayList<>();
////
////        listOfIngredientDaoImpls.add(new Ingredient(3, 10));
////        listOfIngredientDaoImpls.add(new Ingredient(4, 2));
////        RecipeDao recipe = RecipeDaoImpl.getInstance(con);
////        Recipe r = new Recipe(5, "10 kg Flour, 2 litre milk", "tap water", listOfIngredientDaoImpls);
////        assertTrue(recipe.editRecipe(r));
////
////    }
//
//    @Test
//    public void deleteRecipeTest() {
//        RecipeDao recipe = RecipeDaoImpl.getInstance(con);
//        Recipe r = new Recipe(5);
//        assertTrue(recipe.deleteRecipe(r));
//    }
//}
