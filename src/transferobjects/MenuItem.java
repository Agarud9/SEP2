package transferobjects;

import shared.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class representing a MenuItem object with name, list of all ingredients and price.
 * @author Agata
 * @version 1
 */

public class MenuItem implements Serializable
{
  private String name;
  private ArrayList<String> ingredients;
  private double price;
  private String imgPath;

  /**
   * Construct the MenuItem object
   * needed to transfer menu item
   * @param name item's name
   * @param ingredients list of item's ingredients
   * @param price item's price
   */

  public MenuItem(String name, ArrayList<String> ingredients, double price, String imgPath)
  {
    this.name = name;
    this.ingredients = ingredients;
    this.price = price;
    this.imgPath = imgPath;

    Log.log("MenuItem transferobject created");
  }

  /**
   * gets the name
   * @return item's name
   */

  public String getName()
  {
    return name;
  }

  /**
   * gets the ingredients
   * @return list of item's ingredients
   */

  public ArrayList<String> getIngredients()
  {
    return ingredients;
  }

  /**
   * gets the price
   * @return item's price
   */

  public double getPrice()
  {
    return price;
  }

  /**
   * Getting the image path
   * @return the image path
   */
  public String getImgPath(){
    return imgPath;
  }

  /**
   * A string representation with a name
   * @return the name
   */
  public String toString()
  {
    return name;
  }

}
