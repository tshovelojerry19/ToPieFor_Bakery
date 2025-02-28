//
//import com.topiefor.dao.CategoryDao;
//import com.topiefor.dao.impl.CategoryDaoImpl;
//import com.topiefor.models.Category;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import org.junit.jupiter.api.Test;
//
//
//public class CategoryDaoTest {
//      private Connection con = null;
//    public CategoryDaoTest() {
//      try {
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
//     
//   @Test
//    public void getAllCategoryNotNullTest() {
//
//        CategoryDao category = CategoryDaoImpl.getInstance(con);
//        assertTrue(category.getAllCategories() != null);
//    }
//
//    @Test
//    public void getAllCategoryTest() {
//
//        CategoryDao category = CategoryDaoImpl.getInstance(con);
//        assertTrue(category.getAllCategories().size() > 0);
//    }
//
////    @Test
////    public void addCategoryTest() {
////
////        CategoryDao c = CategoryDaoImpl.getInstance(con);
////        Category ct = new Category(0, "Testing", true);
////        assertTrue(c.addCategory(ct));
////
////    }
//
//    @Test
//    public void editCategoryTest() {
//
//        CategoryDao c = CategoryDaoImpl.getInstance(con);
//        Category ct = new Category(4, "Sweets", true);
//        assertTrue(c.editCatgory(ct));
//
//    }
//
//    @Test
//    public void deleteCategoryTest() {
//
//        CategoryDao c = CategoryDaoImpl.getInstance(con);
//        Category ct = new Category(4, "Sweets");
//        assertTrue(c.deleteCategory(ct));
//
//    }
//}
