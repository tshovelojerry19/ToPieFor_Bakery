package com.topiefor.processservice;

import com.topiefor.controller.ProcessRequest;
import com.topiefor.dao.AddressDao;
import com.topiefor.dao.UserDao;
import com.topiefor.dao.impl.AddressDaoImpl;
import com.topiefor.database.manager.DatabaseManager;
import com.topiefor.models.Address;
import com.topiefor.models.Role;
import com.topiefor.models.User;
import com.topiefor.service.AddressService;
import com.topiefor.service.impl.AddressServiceImpl;
import com.topiefor.service.impl.UserServiceImpl;
import com.topiefor.utils.EmailSender;
import com.topiefor.utils.PasswordEncryptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProcessTheUserRequest implements ProcessRequest {

    private String page;
    private UserServiceImpl userServiceImpl;

    public ProcessTheUserRequest(UserDao userDao) {
        userServiceImpl = new UserServiceImpl(userDao);
    }

    @Override
    public void processTheRequest(HttpServletRequest request, HttpServletResponse response) {

        List<User> allUsers = null;
        String userAction = request.getParameter("action");
        int userID = 0;
        String userIDs;
        String userName;
        String status;
        String surName;
        String title;
        String telephone;
        String email;
        String street;
        String suburb;
        String code;
        String role;
        User user = null;
        int vCode = 0;
        String codeString;
        String verificationCode;
        String password;
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
        HttpSession session = request.getSession();
        EmailSender emailSender = new EmailSender();
        boolean flagStatus = false;
        if (userAction != null) {

            switch (userAction.trim()) {
                case "add":

                    userName = request.getParameter("userName");
                    // Status = request.getParameter("Status");
                    surName = request.getParameter("surName");
                    title = request.getParameter("title");
                    telephone = request.getParameter("telephone");
                    email = request.getParameter("email");
                    street = request.getParameter("street");
                    suburb = request.getParameter("suburb");
                    code = request.getParameter("code");
                    role = request.getParameter("role");
                    password = request.getParameter("password");

                    if (userName != null && !userName.isEmpty()
                            && role != null && !role.isEmpty()
                            && surName != null && !surName.isEmpty()
                            && title != null && !title.isEmpty()
                            && telephone != null && !telephone.isEmpty()
                            && email != null && !email.isEmpty()
                            && street != null && !street.isEmpty()
                            && suburb != null && !suburb.isEmpty()
                            && code != null && !code.isEmpty()) {

                        userName = userName.trim();
                        surName = surName.trim();
                        role = role.trim();
                        title = title.trim();
                        telephone = telephone.trim();
                        email = email.trim();
                        street = street.trim();
                        suburb = suburb.trim();
                        code = code.trim();

                        if (userServiceImpl.addUser(new User(0, userName, surName, title, telephone, email, new Address(0, street, suburb, code), role.equals("PUBLIC") ? Role.PUBLIC : Role.ADMIN, true, passwordEncryptor.encryptPassword(password)))) {
                            request.setAttribute("allUsers", new ArrayList<>(userServiceImpl.getAllUsers()));
                            request.setAttribute("message", "user added!");

                        } else {
                            request.setAttribute("message", "user not Added");

                        }

                        page = "Admin/AddUserPage.jsp";

                    }
                    break;

                //---------------------------------------------------------------------------------------------------------
                case "addU":

                    userName = request.getParameter("userName");
                    telephone = request.getParameter("telephone");
                    email = request.getParameter("email");
                    street = request.getParameter("street");
                    suburb = request.getParameter("suburb");
                    code = request.getParameter("code");
                    role = request.getParameter("role");
                    password = request.getParameter("password");

                    if (userName != null && !userName.isEmpty()
                            && role != null && !role.isEmpty()
                            && telephone != null && !telephone.isEmpty()
                            && email != null && !email.isEmpty()
                            && street != null && !street.isEmpty()
                            && suburb != null && !suburb.isEmpty()
                            && code != null && !code.isEmpty()) {
                        userName = userName.trim();
                        telephone = telephone.trim();
                        email = email.trim();
                        street = street.trim();
                        suburb = suburb.trim();
                        code = code.trim();
                        user = new User(0, userName, null, telephone, email, new Address(0, street, suburb, code), Role.PUBLIC, true, passwordEncryptor.encryptPassword(password));
                        if (userServiceImpl.checkUser(email) != null) {
                            request.setAttribute("message", "user alredy Registered");
                            page = "User/LoginPage.jsp";
                            break;
                        } else {
                            session.setAttribute("user", user);
                            vCode = userServiceImpl.generateCode();
                            session.setAttribute("vCode", vCode);
                            emailSender.VerificationEmail(email, userName, vCode);
                            request.setAttribute("code", vCode);
                            page = "User/CodeVerificationPage.jsp";
                        }
                    }
                    break;
                case "verifyCode":
                    String reset = request.getParameter("reset");
                    int codes = 0;
                    codeString = request.getParameter("code");
                    vCode = (Integer) request.getSession().getAttribute("vCode");
                    if (codeString != null && !codeString.isEmpty() && vCode > 0) {
                        codeString = codeString.trim();
                        try {
                            codes = Integer.parseInt(codeString);
                        } catch (NumberFormatException ex) {
                            System.out.println("error" + ex.getMessage());
                        }
                        if (reset != null && !reset.isEmpty() && reset.equals("reset")) {
                            if (codes == vCode) {
                                request.getSession().removeAttribute("vCode");
                                request.setAttribute("message", "Password reseted");
                                page = "User/ResetPassword.jsp";
                            } else {
                                request.setAttribute("message", "Invalid Code");
                                page = "User/CodeVerificationPage.jsp";
                            }
                            break;
                        }
                        user = (User) session.getAttribute("user");
                        if (user != null && codes > 0) {
                            if (codes == vCode) {
                                if (userServiceImpl.addUser(user)) {
                                    request.setAttribute("allUsers", new ArrayList<>(userServiceImpl.getAllUsers()));
                                    request.setAttribute("message", "<p style=\"color:green\">successfully Registered!");

                                    emailSender.welcomeEmail(user.getEmail(), user.getUserName(), user.getTelephoneNumber(), user.getAddress());
                                    request.getSession().removeAttribute("user");
                                    request.getSession().removeAttribute("vCode");
                                    page = "User/LoginPage.jsp";
                                } else {
                                    request.setAttribute("message", "user not Added");
                                    page = "User/LoginPage.jsp";
                                }
                            } else {
                                request.setAttribute("message", "Invalid Code");
                                page = "User/CodeVerificationPage.jsp";
                            }
                           
                        }
                    }else{
                        page = "Admin/ErrorPage.jsp";
                    }

                    break;

                //---------------------------------------------------------------------------------------------------------
                case "get":
                    allUsers = userServiceImpl.getAllUsers();
                    if (allUsers != null && !allUsers.isEmpty()) {

                        request.setAttribute("allUsers", allUsers);
                    } else {
                        request.setAttribute("response", "no data");
                    }
                    page = "Admin/UserPage.jsp";
                    break;

                case "getActive":
                    allUsers = userServiceImpl.getAllUsers();
                    if (allUsers != null && !allUsers.isEmpty()) {

                        request.setAttribute("allUsers", allUsers);
                    } else {
                        request.setAttribute("response", "no data");
                    }
                    page = "Admin/ActiveUsersPage.jsp";
                    break;

                //------------------------------------------------------------------------------------
                case "delete":

                    status = request.getParameter("userStatus");

                    if (status != null && !status.isEmpty()) {

                        status = status.trim();
                        flagStatus = checkFlag(status, request);

                        try {
                            userID = Integer.parseInt(request.getParameter("userID").trim());
                        } catch (NumberFormatException ex) {
                            System.out.println("error " + ex.getMessage());
                        }
                        if (userID > 0) {

                            if (userServiceImpl.userAvailability(userID, flagStatus)) {
                                request.setAttribute("allUsers", new ArrayList<>(userServiceImpl.getAllUsers()));
                                request.setAttribute("message", "User Status Updated");
                            } else {
                                request.setAttribute("reponse", "User Status not Updated");
                            }
                        }
                    } else {
                        page = "Admin/ErrorPage.jsp";
                    }
                    page = "Admin/UserPage.jsp";
                    break;

                case "addForm":

                    page = "Admin/AddUserPage.jsp";
                    break;
                case "editForm":
                    user = (User) request.getSession().getAttribute("user");
                    request.setAttribute("userF", userServiceImpl.getUserByUserID(user.getUserID()));
                    page = "User/UserProfile.jsp";
                    break;
                case "editUser":
                    userName = request.getParameter("userName");
                    surName = request.getParameter("surName");
                    telephone = request.getParameter("telephone");
                    title = request.getParameter("title");
                    street = request.getParameter("street");
                    suburb = request.getParameter("suburb");
                    code = request.getParameter("code");
                    userIDs = request.getParameter("userID");
                    DatabaseManager databaseManager = null;
                    ServletContext sc = request.getServletContext();
                    if (sc != null) {
                        databaseManager = (DatabaseManager) sc.getAttribute("dbman");
                    }
                    if (databaseManager != null) {
                        AddressDao addressDao = AddressDaoImpl.getInstance(databaseManager.getConnection());
                        AddressService addressService = new AddressServiceImpl(addressDao);

                        if (userName != null && !userName.isEmpty()
                                && telephone != null && !telephone.isEmpty()
                                && userIDs != null && !userIDs.isEmpty()
                                && title != null && !title.isEmpty()
                                && surName != null && !surName.isEmpty()
                                && street != null && !street.isEmpty()
                                && suburb != null && !suburb.isEmpty()
                                && code != null && !code.isEmpty()) {
                            userName = userName.trim();
                            surName = surName.trim();
                            title = title.trim();
                            telephone = telephone.trim();
                            userIDs = userIDs.trim();
                            street = street.trim();
                            suburb = suburb.trim();
                            code = code.trim();
                            userIDs = userIDs.trim();

                            try {
                                userID = Integer.parseInt(userIDs);
                            } catch (NumberFormatException ex) {
                                System.out.println("error converting " + ex.getMessage());
                            }
                            Address addressNew = new Address(0, street, suburb, code);
                            Address address = null;
                            for (Address ad : addressService.getAllAddress()) {
                                if (addressNew.equals(ad)) {
                                    address = ad;
                                } else {
                                    address = addressNew;
                                }
                            }

                            if (userID > 0) {
                                user = new User(userID, userName, surName, title, telephone, address);
                                if (userServiceImpl.editUser(user)) {
                                    request.setAttribute("userF", userServiceImpl.getUserByUserID(user.getUserID()));
                                    request.setAttribute("message", "Profile Updated!");

                                } else {
                                    request.setAttribute("userF", userServiceImpl.getUserByUserID(user.getUserID()));
                                    request.setAttribute("message", "Profile not Updated!");
                                }
                                page = "UserController?action=editForm";
                                break;
                            }
                        }
                    } else {
                        page = "Admin/ErrorPage.jsp";
                        break;
                    }
                //get the page
                case "forgetPassEmail":
                    page = "User/ForgetPasswordEmail.jsp";
                    break;

                //get code for reseting password
                case "forgetPasswordEmail":
                    email = request.getParameter("email");

                    if (userServiceImpl.checkUser(email) == null) {
                        request.setAttribute("message", "user does not exist");
                        page = "User/ForgetPasswordEmail.jsp";
                        break;
                    } else {
                        user = userServiceImpl.checkUser(email);
                        session.setAttribute("user", user);
                        vCode = userServiceImpl.generateCode();
                        session.setAttribute("vCode", vCode);
                        emailSender.VerificationEmail(email, user.getUserName(), vCode);
                        request.setAttribute("reset", "rest");
                        page = "User/CodeVerificationPage.jsp";
                    }
                    break;
                // reset the password
                case "resetPassword":
                    password = (String) request.getParameter("password");
                    user = (User) request.getSession().getAttribute("user");
                    if (user != null && user.getUserID() > 0 && password != null && !password.isEmpty()) {
                        if (userServiceImpl.resetPassword(user.getUserID(), passwordEncryptor.encryptPassword(password))) {
                            request.setAttribute("message", "<p style=\"color:green\"> Password successfully changed!");
                            emailSender.passwordReseted(user.getUserName(), user.getEmail());
                            page = "User/LoginPage.jsp";
                        } else {
                            request.setAttribute("message", "password not reseted");
                            page = "User/LoginPage.jsp";
                        }
                    } else {
                        page = "Admin/ErrorPage.jsp";
                    }
                    break;
                case "edit":
                    page = "Admin/UserPage.jsp";
                    break;
                default:
                    page = "Admin/ErrorPage.jsp";
                    break;
            }

        }
    }

    private boolean checkFlag(String status, HttpServletRequest request) {
        boolean flagStatus = false;
        switch (status) {

            case "Activate":

                flagStatus = true;
                break;
            case "Deactivate":

                flagStatus = false;
                break;
            default:

                request.setAttribute("error", "error");
                break;
        }
        return flagStatus;
    }

    @Override
    public void processTheResponse(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        try {
            requestDispatcher.forward(request, response);

        } catch (ServletException | IOException ex) {
            System.out.println("Error Dispatching View: " + ex.getMessage());
        }
    }

}
