//import com.topiefor.dao.AddressDao;
//import com.topiefor.dao.impl.AddressDaoImpl;
//import com.topiefor.models.Address;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class AddressDaoTest {
//private Connection con = null;
//    public AddressDaoTest() {
//         try {
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
////    @Test
////    public void addAddressTest() {
////
////        AddressDao address = AddressDaoImpl.getInstance(con);
////        Address a = new Address(0, "093 tembisa", "South ", "1666");
////        assertTrue(address.addAddress(a));
////    }
//
//    @Test
//    public void editAddressTest() {
//
//       
//       AddressDao address = AddressDaoImpl.getInstance(con);
//        Address a = new Address(5, "Elson 123 road", "Kaalfontein", "0293");
//        assertTrue(address.editAddress(a));
//
//    }
//    @Test
//    public void getAllAddressesByUserID(){
//        AddressDao address = AddressDaoImpl.getInstance(con);
//        assertTrue( !address.getAllAddressesByUserID(2).isEmpty());
//        
//       
//    }
//    
//    @Test
//    public void getAllAddress(){
//        AddressDao address = AddressDaoImpl.getInstance(con); 
//        assertTrue(address.getAllAddress().size() > 0);
//    }
//}
