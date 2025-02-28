package com.topiefor.database.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.topiefor.database.manager.DatabaseManager;

@WebListener
public class DatabaseListener implements ServletContextListener{
  @Override
  public void contextInitialized(ServletContextEvent sce){
    ServletContext sc = sce.getServletContext();
    String url = sc.getInitParameter("url");
    String username = sc.getInitParameter("username");
    String password = sc.getInitParameter("password");
    String driver = sc.getInitParameter("driver");
    String database = sc.getInitParameter("database");
    DatabaseManager dbm = new DatabaseManager(url, database, driver, username, password);
    dbm.getConnection();
    sc.setAttribute("dbman", dbm);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce){
    ServletContext sc = sce.getServletContext();
    DatabaseManager dbm = (DatabaseManager) sc.getAttribute("dbman");
    if(dbm != null){
      dbm.closeConnection();
    }
  }
}
