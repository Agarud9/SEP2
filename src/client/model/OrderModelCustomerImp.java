package client.model;

import client.networking.Client;
import shared.Log;
import transferobjects.CartItem;
import transferobjects.OrderItem;
import transferobjects.Request;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

//TODO javadocs

public class OrderModelCustomerImp implements OrderModelCustomer
{
  private Client client;
  private PropertyChangeSupport support;

  public OrderModelCustomerImp(Client client)
  {
    this.client = client;
    support = new PropertyChangeSupport(this);

    client.addListener(Client.ORDER_RECEIVED, this::orderReceived);
  }

  private void orderReceived(PropertyChangeEvent evt)
  {
    Log.log("OrderModelCustomerImpl order received from client");
    support.firePropertyChange(ORDER_RECEIVED, null, evt.getNewValue());
  }

  @Override public void requestUncollectedOrder()
  {
    String username = UserModelImp.getUsername();
    Request request = new Request(Request.CUSTOMER_UNCOLLECTED_ORDER_REQUEST);
    request.setObject(username);

    client.sendRequest(request);
  }

  @Override public void cancelOrder()
  {
    String username = UserModelImp.getUsername();
    Request request = new Request(Request.CANCEL_ORDER);
    request.setObject(username);

    client.sendRequest(request);
  }

  @Override public void addListener(String event,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(event, listener);
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }
}
