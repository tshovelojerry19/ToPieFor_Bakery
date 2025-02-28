//
//import com.topiefor.dao.IngredientDao;
//import com.topiefor.dao.impl.IngredientDaoImpl;
//import com.topiefor.models.Ingredient;
//import com.topiefor.models.Unit;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class IngredientDaoTest {
//
//    private Connection con = null;
//
//    public IngredientDaoTest() {
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
//    public void getAllIngredientTest() {
//
//        IngredientDao ingredient = IngredientDaoImpl.getInstance(con);
//        assertTrue(ingredient.getAllIngredient().size() > 0);
//    }
//
////    @Test
////    public void addIngredientTest() {
////
////        IngredientDao ingredientDao = IngredientDaoImpl.getInstance(con);
////        Ingredient ingredient = new Ingredient(0, "Sugar", 100,new Unit(1), true);
////        assertTrue(ingredientDao.addIngredient(ingredient));
////
////    }
////    @Test
////    public void editIngredientTest() {
////        IngredientDao ingredientDao = IngredientDaoImpl.getInstance(con);
////        Ingredient ingredient = new Ingredient(3, "Flour", 20, new Unit(2));
////        assertTrue(ingredientDao.editIngredient(ingredient));
////    }
//
//    @Test
//    public void deleteIngredientTest()  {   
//      IngredientDao ingredientDao = IngredientDaoImpl.getInstance(con);
//       Ingredient ingredient = new Ingredient(3, "Flour", 20, new Unit(2));
//        assertTrue(ingredientDao.deleteIngredient(ingredient));
//    }
//}
