//import com.topiefor.dao.IngredientDao;
//import com.topiefor.dao.impl.IngredientDaoImpl;
//import com.topiefor.models.Ingredient;
//import com.topiefor.models.Unit;
//import com.topiefor.service.impl.IngredientServiceImpl;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class IngredientServiceTest {
//
//    private Connection con = null;
//
//    public IngredientServiceTest() {
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con = DriverManager.getConnection(
//                   "jdbc:mysql://localhost:3306/to_pie_for_db_test", "root", "root"
//            );
//        } catch (ClassNotFoundException | SQLException cle) {
//        }
//        if (con != null) {
//            System.out.println("Got connection");
//        }
//    }
//
////    @Test
////    public void getAllIngredientNotNullTest() {
////
////          IngredientServiceImpl ingredient = new IngredientServiceImpl(IngredientDaoImpl.getInstance(con));
////        assertTrue(ingredient.getAllIngredient() != null);
////    }
////
////    @Test
////    public void addIngredientNotNullTest() {
////
////         IngredientServiceImpl ingredient = new IngredientServiceImpl(IngredientDaoImpl.getInstance(con));
////        Ingredient i = new Ingredient(0, "hdgyk", 500, new Unit(1), true);
////        assertNotNull(ingredient.addIngredient(i));
////    }
////
////    @Test
////    public void editIngredientNotNullTest() {
////
////       IngredientServiceImpl ingredient = new IngredientServiceImpl(IngredientDaoImpl.getInstance(con));
////        Ingredient i = new Ingredient(14, "yellow", 4);
////        assertNotNull(ingredient);
////
////    }
////
////    @Test
////    public void deleteIngredientNotNullTest() {
////
////         IngredientServiceImpl ingredient = new IngredientServiceImpl(IngredientDaoImpl.getInstance(con));
////        Ingredient i = new Ingredient(15, "Suncake now", 4, new Unit(1));
////        assertNotNull(ingredient.deleteIngredient(i));
////
////    }
//}