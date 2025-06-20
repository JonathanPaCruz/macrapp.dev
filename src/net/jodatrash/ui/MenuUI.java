package net.jodatrash.ui;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class MenuUI {
  public static void show(String hostname) throws BadLocationException {
    JTextPane textPane = new JTextPane();
    textPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
    textPane.setEditable(false);
    textPane.setBackground(net.jodatrash.config.AppConfig.BACKGROUND_COLOR);

    new MenuBuilder(textPane).build(hostname);

    JScrollPane scroll = new JScrollPane(textPane);
    scroll.getViewport().setBackground(textPane.getBackground());

    String input;
    do {
      input = JOptionPane.showInputDialog(null, scroll, "Menú de Opciones", JOptionPane.PLAIN_MESSAGE);
      if (input == null) {
        showMessage("Macro finalizada.");
        return;
      }
      input = input.trim();
    } while (!input.matches("[0-3]"));

    switch (input) {
      case "0" -> showMessage("Macro finalizada.");
      case "1" -> showMessage("Has seleccionado:\n1.- INSTALACION.");
      case "2" -> showMessage("Has seleccionado:\n2.- VALIDACION.");
      case "3" -> showMessage("Has seleccionado:\n3.- DESINSTALACION DE ARCHIVOS.");
    }
  }

  private static void showMessage(String msg) {
    JOptionPane.showMessageDialog(null, msg);
  }
}
