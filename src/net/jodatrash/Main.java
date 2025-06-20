package net.jodatrash;

import net.jodatrash.ui.MenuUI;
import net.jodatrash.utils.HostUtils;

public class Main {
  public static void main(String[] args) throws Exception {
    String hostname = HostUtils.getHostname();
    MenuUI.show(hostname);
  }
}