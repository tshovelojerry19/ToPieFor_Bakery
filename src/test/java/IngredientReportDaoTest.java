//import com.topiefor.dao.IngredientReportDao;
//import com.topiefor.dao.impl.IngredientReportDaoImpl;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class IngredientReportDaoTest {
//     private Connection con = null;
//    
//    public IngredientReportDaoTest() {
//        try {
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
//// @Test
////    public void ingredientInStockTest() {
////
////        IngredientReportDao ingredientReport = IngredientReportDaoImpl.getInstance(con);
////        assertTrue(ingredientReport.ingredientInStock().size() > 0);
////    }
////
////    @Test
////    public void ingredientsRequiredToBe0rderedTest() {
////
////       IngredientReportDao ingredientReport = IngredientReportDaoImpl.getInstance(con);
////        assertTrue(ingredientReport.ingredientsRequiredToBe0rdered().size() > 0);
////
////    }
////
////    @Test
////    public void ingredientInStockWriterTest() {
////
////       IngredientReportDao ingredientReport = IngredientReportDaoImpl.getInstance(con);
////        assertTrue(ingredientReport.ingredientInStockWriter("C:\\Users\\Train\\Desktop\\ingredientInStock.txt"));
////
////    }
////
////    @Test
////    public void ingredientsRequiredToBe0rderedWriterTest()  {
////
////     IngredientReportDao ingredientReport = IngredientReportDaoImpl.getInstance(con);
////        assertTrue(ingredientReport.ingredientInStockWriter("C:\\Users\\Train\\Desktop\\ingredientsRequiredToBe0rdered.txt"));
////
////
////    }
//   
//}