//import com.topiefor.dao.PaymentDao;
//import com.topiefor.dao.impl.PaymentDaoImpl;
//import com.topiefor.models.Order;
//import com.topiefor.models.Payment;
//import com.topiefor.service.PaymentService;
//import com.topiefor.service.impl.PaymentServiceImpl;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class PaymentServiceTest {
//
//    private Connection con = null;
//
//    public PaymentServiceTest() {
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
//    public void addPaymentTest() {
//
//        PaymentService payment = new PaymentServiceImpl(PaymentDaoImpl.getInstance(con));
//        assertTrue(payment.addPayment(new Payment(0, "cash", true, new Order(2)), 2, 568));
//
//    }
//
//
//@Test
//        public void makePaymentTest() {
//
//        PaymentService payment = new PaymentServiceImpl(PaymentDaoImpl.getInstance(con));
//        assertTrue(payment.makePayment("approved", 30.00));
//        
//    }
//}