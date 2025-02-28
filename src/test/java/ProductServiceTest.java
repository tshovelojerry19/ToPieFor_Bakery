//import com.topiefor.dao.ProductDao;
//import com.topiefor.dao.impl.ProductDaoImpl;
//import com.topiefor.models.Category;
//import com.topiefor.models.Product;
//import com.topiefor.models.Recipe;
//import com.topiefor.service.ProductService;
//import com.topiefor.service.impl.ProductServiceImpl;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ProductServiceTest {
//     private Connection con = null;
//    public ProductServiceTest() {
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
//
//    
//      @Test
//    public void getAllProductNotNullTest() {
//
//       ProductService product = new ProductServiceImpl(ProductDaoImpl.getInstance(con));
//        assertTrue(product.getAllProducts() != null);
//    }
//
//    @Test
//    public void addProductNotNullTest() {
//
//         ProductService product = new ProductServiceImpl(ProductDaoImpl.getInstance(con));
//        Product p = new Product(0, "bunny", 339.00, " peanuts", new Recipe(2), new Category(1), true, "image");
//         assertNotNull(product.addProduct(p));
//
//    }
//
//    @Test
//    public void editProductNotNullTest() {
//
//        ProductService product = new ProductServiceImpl(ProductDaoImpl.getInstance(con));
//        Product p = new Product(11, "brownies", 20.00, "chocolate", new Recipe(1), new Category(1), true, "yhhe");
//       assertNotNull(product.editProduct(p));
//
//    }
//
//    @Test
//    public void productAvailabilityNotNullTest() {
//        ProductService product = new ProductServiceImpl(ProductDaoImpl.getInstance(con));
//        Product p = new Product(12);
//         assertNotNull(product.productAvailability(p));
//      
//    }
//    
//    @Test 
//    public void getProductsByCategoryNotNullTest(){
//        ProductService product = new ProductServiceImpl(ProductDaoImpl.getInstance(con));
//        assertNotNull(product.getProductsByCategory(11));
//    }
//  }
