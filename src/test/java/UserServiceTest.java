//
//import com.topiefor.dao.UserDao;
//import com.topiefor.dao.impl.UserDaoImpl;
//import com.topiefor.models.Address;
//import com.topiefor.models.Role;
//import com.topiefor.models.User;
//import com.topiefor.service.UserService;
//import com.topiefor.service.impl.UserServiceImpl;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UserServiceTest {
//        private Connection con = null;
//    public UserServiceTest() {
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
//     @Test
//    public void getAllUserNotNullTest() {
// UserService user = new UserServiceImpl(UserDaoImpl.getInstance(con));
//        assertTrue(user.getAllUsers() != null);
//        
//    }
//
//    @Test
//    public void addUserNotNullTest() {
//
//         UserService user = new UserServiceImpl(UserDaoImpl.getInstance(con));
//        User u = new User(0, "Nyiko", "Herbert", "Mrs", "7777777777", "girl@gmail", new Address(0, "22 Tembisa Road", "Ka-Mhinga", "1111"), Role.PUBLIC, true, "fumani");
//        assertNotNull(user.addUser(u));
//
//    }
//
//    @Test
//    public void editUserNotNullTest() {
//
//       
//       UserService user = new UserServiceImpl(UserDaoImpl.getInstance(con));
//        User u = new User(1, "fumiPrecios", "Ngove", "Mrs", "0847562999", "ytfh@gmail", new Address(7, "92 venda hayani", "Mvenda", "4867"), Role.PUBLIC, true, "fumani");
//        assertNotNull(user.editUser(u));
//
//    }
//
//}