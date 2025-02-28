//
//import com.topiefor.dao.OrderDao;
//import com.topiefor.dao.impl.OrderDaoImpl;
//import com.topiefor.models.Address;
//import com.topiefor.models.Order;
//import com.topiefor.models.Payment;
//import com.topiefor.models.Product;
//import com.topiefor.models.User;
//import com.topiefor.service.OrderService;
//import com.topiefor.service.impl.OrderServiceImpl;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class OrderServiceTest {
//
//    private Connection con = null;
//
//    public OrderServiceTest() {
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
//    @Test
//    public void addOrderNotNullTest() {
//
//        OrderService orderService = new OrderServiceImpl(OrderDaoImpl.getInstance(con));
//        List<Product> lp = new ArrayList();
//        lp.add(new Product(11, 1));
//        assertNotNull(orderService.addOrder(new Order(new Address(30), new User(11), 40.00, lp, new Payment(0, "Approved", true))));
//
//    }
//
//    @Test
//    public void cancelOrderNotNullTest() {
//
//        OrderService orderService = new OrderServiceImpl(OrderDaoImpl.getInstance(con));
//        assertNotNull(orderService.cancelOrder(new Order(2, "Cancelled")));
//
//    }
//
//    @Test
//    public void getOrderDetailsByOrderIDNotNullTest() {
//        OrderService orderService = new OrderServiceImpl(OrderDaoImpl.getInstance(con));
//        assertNotNull(orderService.getOrderDetailsByOrderID(5));
//    }
//
//    @Test
//    public void changeOrderStatusNotNullTest() {
//
//        OrderService orderService = new OrderServiceImpl(OrderDaoImpl.getInstance(con));
//        assertNotNull(orderService.cancelOrder(new Order(2, "baked")));
//
//    }
//
//    @Test
//    public void getAllOrdersNotNullTest() {
//
//        OrderService orderService = new OrderServiceImpl(OrderDaoImpl.getInstance(con));
//        assertNotNull(orderService.getAllOrders().size() > 0);
//
//    }
//
//}