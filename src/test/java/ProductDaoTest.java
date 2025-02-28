//
//import com.topiefor.dao.ProductDao;
//import com.topiefor.dao.impl.ProductDaoImpl;
//import com.topiefor.models.Category;
//import com.topiefor.models.Product;
//import com.topiefor.models.Recipe;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ProductDaoTest {
//
//    private Connection con = null;
//
//    public ProductDaoTest() {
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
//    public void getAllProductTest() {
//        ProductDao product = ProductDaoImpl.getInstance(con);
//        assertTrue(product.getAllProducts().size() > 0);
//        assertTrue(product.getAllProducts().size() > 0);
//    }
//
////    @Test
////    public void addProductTest() {
////
////        ProductDao product = ProductDaoImpl.getInstance(con);
////        Product p = new Product(0, "chocolate", 339.00, " peanuts", new Recipe(2), new Category(1), true, "image");
////        assertTrue(product.addProduct(p));
////
////    }
//
//    @Test
//    public void editProductTest() {
//
//        ProductDao product = ProductDaoImpl.getInstance(con);
//        Product p = new Product(11, "sponge cake", 20.00, "chocolate", new Recipe(1), new Category(1), true, "yhhe");
//        assertTrue(product.editProduct(p));
//
//    }
//
//    @Test
//    public void productAvailabilityTest() {
//        ProductDao product = ProductDaoImpl.getInstance(con);
//        Product p = new Product(12);
//        assertTrue(product.productAvailability(p));
//    }
//}
