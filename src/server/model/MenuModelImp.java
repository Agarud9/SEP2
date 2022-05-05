package server.model;

import server.databaseConn.DatabaseConn;
import transferobjects.MenuItem;

/**
 * Class responsible for connecting the networking part of the Server with Database connection and sending Menu Items into the Database.
 * @author Simon
 * @version 1
 */
public class MenuModelImp implements MenuModel
{

  private DatabaseConn databaseConn;

  /**
   * Constructor which is assigning database connection
   * @param databaseConn is an instance of the database connection which is connection the server with the database
   */
  public MenuModelImp(DatabaseConn databaseConn){
    this.databaseConn = databaseConn;
  }

  /**
   * Passes the menu item onto the Database connection.
   * @param menuItem which is passed onto the class Databaseconn
   */
  @Override public void addItem(MenuItem menuItem)
  {
    //databaseConn.addItem(menuItem);
  }
}
