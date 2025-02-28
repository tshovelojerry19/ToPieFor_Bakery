//import com.topiefor.dao.IngredientReportDao;
//import com.topiefor.dao.impl.IngredientReportDaoImpl;
//import com.topiefor.service.impl.IngredientReportServiceImpl;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class IngredientReportServiceTest {
//    private Connection con = null;
//    
//    public IngredientReportServiceTest() {
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
////  @Test
////    public void ingredientInStockNotNullTest() {
////
////        
////         IngredientReportServiceImpl ingredientReport = new IngredientReportServiceImpl(IngredientReportDaoImpl.getInstance(con));
////        assertTrue(ingredientReport.ingredientInStock()!= null);
////    }
////
////    @Test
////    public void ingredientsRequiredToBe0rderedNotNullTest() {
////
////      IngredientReportServiceImpl ingredientReport = new IngredientReportServiceImpl(IngredientReportDaoImpl.getInstance(con));
////         assertTrue(ingredientReport.ingredientsRequiredToBe0rdered()!= null);
////
////    }
////
////    @Test
////    public void ingredientInStockWriterNotNullTest() {
////
////          IngredientReportServiceImpl ingredientReport = new IngredientReportServiceImpl(IngredientReportDaoImpl.getInstance(con));
////        assertNotNull(ingredientReport.ingredientInStockWriter("C:\\Users\\Train\\Desktop\\ingredientInStock.txt"));
////
////    }
////
////    @Test
////    public void ingredientsRequiredToBe0rderedWriterNotNullTest()  {
////
////       IngredientReportServiceImpl ingredientReport = new IngredientReportServiceImpl(IngredientReportDaoImpl.getInstance(con));
////        assertNotNull(ingredientReport.ingredientInStockWriter("C:\\Users\\Train\\Desktop\\ingredientsRequiredToBe0rdered.txt"));
////
////
////    }
//}