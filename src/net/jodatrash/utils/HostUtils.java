package net.jodatrash.utils;

import java.net.InetAddress;

public class HostUtils {
  public static String getHostname() throws Exception {
    return InetAddress.getLocalHost().getHostName();
  }
}
