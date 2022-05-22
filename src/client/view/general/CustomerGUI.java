package client.view.general;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.scene.control.TabPane;
import shared.Log;

//TODO javadocs

/**
 * CustomerGUI is part of the strategy pattern, specifically this is the strategy when the user
 * is a customer
 * @author Greg
 * @version 1
 */
public class CustomerGUI extends UserStrategy
{
  private final static String BASE_PATH = "src/client/view/customer/";
  /**
   * All the tabs to load when the logged in user is a customer
   */
  private final static String[] tabs = {
      "displayMenu/DisplayMenuView.fxml", "Today's Menu",
      "displayWeeklyMenu/WeeklyMenuView.fxml", "Weekly Menu",
          "shoppingCart/ShoppingCartView.fxml", "Shopping Cart",
      "myOrder/MyOrderView.fxml" , "My Order"

  };

  public CustomerGUI(TabPane tabPane, ViewHandler viewHandler, ViewModelFactory viewModelFactory) {
    super(tabPane, viewHandler, viewModelFactory);
  }

  @Override public void loadTabs() {
    Log.log("CustomerGUI customer tabs are loading");
    loadTabs(BASE_PATH, tabs);
  }
}
