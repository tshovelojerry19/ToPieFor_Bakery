//import com.topiefor.dao.CategoryDao;
//import com.topiefor.dao.impl.CategoryDaoImpl;
//import com.topiefor.models.Category;
//import com.topiefor.service.impl.CategoryServiceImpl;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class CategoryServiceTest {
//    private Connection con = null;
//    public CategoryServiceTest() {
//       try {
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
//// 
////   @Test
////    public void getAllCategoriesNotNullTest() {
////
////        CategoryServiceImpl c = new CategoryServiceImpl(CategoryDaoImpl.getInstance(con));
////         assertNotNull(c.getAllCategories() != null);
////    }
////    
////    
////    @Test
////   public void addCategoryNotNullTest(){
////    
////      CategoryServiceImpl c = new CategoryServiceImpl(CategoryDaoImpl.getInstance(con));
////        Category ct = new Category(0, "chocolate", true);
////        assertNotNull(c.addCategory(ct));
////    }
////   @Test
////    public void editCategoryNotNullTest() {
////
////       CategoryServiceImpl c = new CategoryServiceImpl(CategoryDaoImpl.getInstance(con));
////        Category ct = new Category(4, "Sweets", true);
////        assertNotNull(c.editCategory(ct));
////
////    }
////     @Test
////    public void deleteCategoryNotNullTest() {
////
////       CategoryServiceImpl c = new CategoryServiceImpl(CategoryDaoImpl.getInstance(con));
////        Category ct = new Category(1, "Cakes");
////        assertNotNull(c.deleteCategory(ct));
////
////    }
//}