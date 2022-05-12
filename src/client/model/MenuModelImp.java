package client.model;

import client.networking.Client;
import shared.Log;
import transferobjects.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class that connects the view model and the Client side of networking.
 * In this class the information about items is collected into a MenuItem object
 * and send to the Client side of networking. The class is the subject of the
 * MenuEmplViewModel and send it Error objects.
 * @author Uafa
 */

public class MenuModelImp implements MenuModel
{
  private Client client;
  private PropertyChangeSupport support;

  /**
   * Constructor for the class. Takes a Client object as a parameter and
   * assigns it to the client private variable in the class. Adds the class as
   * to be a Listener for the Client object.
   * @param client the client that information has to be send to
   */
  public MenuModelImp(Client client)
  {
    support = new PropertyChangeSupport(this);
    this.client = client;
    client.addListener(Client.ERROR_RECEIVED, this::sendError);
    client.addListener(Client.MENU_ITEMS_RECEIVED, this::sendMenuItems);
  }

  /**
   * Fires an event with the error message to the ViewModel
   * @param event the event fired by the client
   */
  private void sendError(PropertyChangeEvent event)
  {
    support.firePropertyChange(ERROR_RECEIVED, null,event.getNewValue() );

    Log.log("MenuModelImp fires an ERROR_RECEIVED property");
  }

  /**
   * Fires an event with the list of menu items to the ViewModel
   * @param event the event fired by the client
   */
  private void sendMenuItems(PropertyChangeEvent event)
  {
    support.firePropertyChange(MENU_ITEMS_RECEIVED,null,event.getNewValue());
    Log.log("MenuModelImp fires an MENU_ITEMS_RECEIVED property");
  }

  /**
   * Takes a String name, an ArrayList of Strings representing the ingredients,
   * a double value for the price and the image path. Using those parameters
   * creates a MenuItem, which is send to the client with the addItem method
   * located in the client class.
   * @param name  the name of the item to be added
   * @param ingredients an ArrayList of all ingredients of the item
   * @param price the price of the item
   */
  @Override public void addItem(String name, ArrayList<String> ingredients,
      double price, String imgPath)
  {
    try {
      BufferedImage bufferedImage = ImageIO.read(new File(imgPath));
      String format = imgPath.substring(imgPath.lastIndexOf(".") + 1);
      SerializableImage serializableImage = new SerializableImage(bufferedImage, format);

      MenuItem item = new MenuItem(name,ingredients,price);
      item.setImage(serializableImage);
      Log.log("MenuModelImpl created a new MenuItem and set its image path");

      client.addItem(item); //added the image path here
    } catch (IOException e) {
      PropertyChangeEvent evt = new PropertyChangeEvent(this, "", null, new ErrorMessage(e.getMessage()));
      sendError(evt);
    }

  }

  /**
   * The method creates request object with correct name
   * and sends this object to the client
   */
  @Override public void requestMenuItems()
  {
    Request request = new Request(Request.MENU_ITEMS_REQUEST);
    client.sendRequest(request);

    Log.log("MenuModelImpl send a MENU_ITEMS_REQUEST object to the Client (sendRequest method)");
  }

  /**
   * The method is used to send a DailyMenuItem object to the client
   * @param date the LocalDate object that the user has provided
   * @param menuItems the list of Menu items which the user has selected
   */
  @Override public void addItemsToDailyMenu(LocalDate date,
      ArrayList<MenuItem> menuItems)
  {
    DailyMenuItemList dailyMenuItemList = new DailyMenuItemList(date,menuItems);
    client.addItemsToDailyMenu(dailyMenuItemList);

    Log.log("MenuModelImpl sends a new dailyMenuItem object to the Client (addItemsToDailyMenu method)");
  }

  /**
   * Using the PropertyChangeSubject object adds a listener for a specific event
   * @param event  event to be listened
   * @param listener listener to be added
   */
  @Override public void addListener(String event,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(event,listener);

    Log.log(listener + "has been added as a listener to " + this + " for " + event);
  }

  /**
   * Using the PropertyChangeSubject object adds a listener for all types of
   * events
   * @param listener listener to be added
   */
  @Override public void addListener(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
    Log.log(listener + "has been added as a listener to " + this);
  }
}
