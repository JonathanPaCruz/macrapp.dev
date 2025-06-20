package net.jodatrash.ui;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.text.*;

public class StyleUtil {
  private static final Map<String, Style> styleCache = new HashMap<>();

  public static void appendStyled(JTextPane textPane, String text, Color color, boolean bold)
      throws BadLocationException {
    StyledDocument doc = textPane.getStyledDocument();
    String key = color.toString() + bold;
    Style style = styleCache.computeIfAbsent(key, k -> {
      Style s = textPane.addStyle(k, null);
      StyleConstants.setForeground(s, color);
      StyleConstants.setBold(s, bold);
      return s;
    });
    doc.insertString(doc.getLength(), text, style);
  }
}
