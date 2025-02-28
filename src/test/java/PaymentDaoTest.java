//import com.topiefor.dao.PaymentDao;
//import com.topiefor.dao.impl.PaymentDaoImpl;
//import com.topiefor.models.Order;
//import com.topiefor.models.Payment;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//
//public class PaymentDaoTest {
//     private Connection con = null;
//    public PaymentDaoTest() {
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
//
//    @Test
//    public void addPaymentTest() {
//
//        PaymentDao payment = PaymentDaoImpl.getInstance(con);
//        assertTrue(payment.addPayment(new Payment(0, "cancel", true, new Order(2)), 2, 568));
//        
//    }
//    
//    @Test
//    public void getAllPaymentTest() {
//
//        PaymentDao payment = PaymentDaoImpl.getInstance(con);
//        assertTrue(payment.getAllPayments().size()>0);
//        
//    }
//}