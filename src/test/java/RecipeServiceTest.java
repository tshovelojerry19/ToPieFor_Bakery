//
//import com.topiefor.dao.RecipeDao;
//import com.topiefor.dao.impl.RecipeDaoImpl;
//import com.topiefor.models.Ingredient;
//import com.topiefor.models.Recipe;
//import com.topiefor.service.RecipeService;
//import com.topiefor.service.impl.RecipeServiceImpl;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RecipeServiceTest {
//     private Connection con = null;
//    public RecipeServiceTest() {
//         try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection(
//                  "jdbc:mysql://localhost:3306/to_pie_for_db_test", "root", "root"
//            );
//        } catch (ClassNotFoundException | SQLException cle) {
//        }
//        if (con != null) {
//            System.out.println("Got connection");
//        }  
//    }
////@Test
////    public void getAllUserNotNullTest() {
////
////        RecipeService recipe = new RecipeServiceImpl(RecipeDaoImpl.getInstance(con));
////        assertTrue(recipe.getAllRecipies() != null);
////    }
////
////    @Test
////    public void addRecipeNotNullTest() {
////
////  List<Ingredient> listOfIngredientDaoImpls = new ArrayList<>();
////        listOfIngredientDaoImpls.add(new Ingredient(10, 10));
////        listOfIngredientDaoImpls.add(new Ingredient(8, 2));
////        listOfIngredientDaoImpls.add(new Ingredient(3, 6));
////        Recipe r = new Recipe(0, "dfvh", " erghregg", true, listOfIngredientDaoImpls);
////        RecipeService recipe = new RecipeServiceImpl(RecipeDaoImpl.getInstance(con));
////       
////           assertNotNull(recipe.addRecipe(r));
////
////    }
////
////    @Test
////    public void editUserNotNullTest() {
////
////       
////       List<Ingredient> listOfIngredientDaoImpls = new ArrayList<>();
////        listOfIngredientDaoImpls.add(new Ingredient(12, 10));
////        listOfIngredientDaoImpls.add(new Ingredient(14, 2));
////        listOfIngredientDaoImpls.add(new Ingredient(15, 6));
////        RecipeService recipe = new RecipeServiceImpl(RecipeDaoImpl.getInstance(con));
////        Recipe r = new Recipe(4, "erf3h4g", "rfgjh", listOfIngredientDaoImpls);
////         assertNotNull(recipe.editRecipe(r));
////
////    }
////
////    @Test
////    public void deleteRecipeNotNullTest() {
////        
////        
////        RecipeService recipe = new RecipeServiceImpl(RecipeDaoImpl.getInstance(con));
////        Recipe r = new Recipe(5);
////         assertNotNull(recipe.deleteRecipe(r));
////    }
//  
//}