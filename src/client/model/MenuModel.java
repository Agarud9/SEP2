package client.model;

import transferobjects.MenuItem;
import transferobjects.Request;
import util.PropertyChangeSubject;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * An interface which is responsible for handling the connection between the
 * ViewModels and the Networking
 */

public interface MenuModel extends PropertyChangeSubject
{
  String ERROR_RECEIVED = "ErrorReceived";
  String MENU_ITEMS_RECEIVED = "MenuItemsReceived";

  /**
   * The method is used to send to the Client String objects, that are required
   * for adding MenuItem
   * @param name the name of menu Item
   * @param ingredients list of all MenuItem's ingredients
   * @param price the price of menu Item
   * @param imgPath the path where the image is stored
   */

  void addItem(String name, ArrayList<String> ingredients, double price,
      String imgPath);

  /**
   * The method is used to send to the Client request for list of all MenuItems
   */

  void requestMenuItems();

  /**
   * The method is used to send to the Client LocalDate and ArrayList objects, that are required
   *  for adding DailyMenu Item
   * @param date the menu's date
   * @param menuItems the list of menu's items
   */

  void addItemsToDailyMenu(LocalDate date,ArrayList<MenuItem> menuItems);
}
