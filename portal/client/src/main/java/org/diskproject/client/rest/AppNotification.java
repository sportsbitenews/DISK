package org.diskproject.client.rest;

import org.diskproject.client.application.ApplicationView;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.vaadin.polymer.Polymer;
import com.vaadin.polymer.elemental.Function;
import com.vaadin.polymer.paper.PaperToastElement;

public class AppNotification {
  
  public static void notifySuccess(String message, int delay) {
    showToast(message, delay, "green");
  }
  
  public static void notifyFailure(String message) {
    showToast(message, 1000, "maroon");
  }
  
  private static void showToast(final String message, final int delay, final String color) {
    final PaperToastElement toast = ApplicationView.toast;
    final int _delay = delay > 0 ? delay : new Double(toast.getDuration()).intValue();
    if(toast.getVisible()) {
      Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
        @Override
        public boolean execute() {
          showToast(message, delay, color);
          return false;
        }
      }, _delay + 100);
    }
    else {
      Polymer.ready(toast, new Function<Object, Object>() {
        @Override
        public Object call(Object arg) {
          toast.setText(message);
          toast.setDuration(delay);
          toast.getStyle().setProperty("background-color", color);
          toast.show();
          return null;
        }
      });
    }
  }
  
  public static void showLoading() {
    //ApplicationView.contentContainer.add(new HTML("<center>Loading..</center>"));
  }

  public static void stopLoading() {
    ApplicationView.contentContainer.clear();
  }
  
}
