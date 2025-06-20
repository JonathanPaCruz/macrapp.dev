package net.jodatrash;

import java.awt.*;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.text.*;

public class TestMenuOptimizado {

  // =========================
  // CONFIGURACIÓN GLOBAL
  // =========================

  private static final int WIDTH = 48;

  // Colores
  private static final Color BACKGROUND_COLOR = Color.BLACK;
  private static final Color BORDER_COLOR = Color.GREEN;
  private static final Color MENU_TEXT_COLOR = Color.GREEN;
  private static final Color HOST_TEXT_COLOR = Color.RED;

  // Negrita para distintos elementos
  private static final boolean BOLD_BORDER_CHAR = false;
  private static final boolean BOLD_BORDER_SIDE = true;
  private static final boolean BOLD_MENU_TEXT = false;

  // Decoradores visuales
  private static final char BORDER_CHAR = '='; // Borde superior/inferior
  private static final String BORDER_SIDE = "*"; // Laterales

  // Componentes de interfaz
  private static final JTextPane textPane = new JTextPane();
  private static final StyledDocument doc;
  private static final Map<String, Style> styleCache = new HashMap<>();

  // =========================
  // INICIALIZACIÓN ESTÁTICA
  // =========================

  static {
    textPane.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
    textPane.setEditable(false);
    textPane.setBackground(BACKGROUND_COLOR);
    doc = textPane.getStyledDocument();
  }

  // =========================
  // MÉTODO PRINCIPAL
  // =========================

  public static void main(String[] args) throws Exception {
    String hostname = InetAddress.getLocalHost().getHostName();
    String equipo = "EQUIPO: \\" + hostname;

    buildMenu(equipo);
    showMenuDialog();
  }

  // =========================
  // CONSTRUCCIÓN DE MENÚ
  // =========================

  private static void buildMenu(String equipo) throws BadLocationException {
    appendEmptyLine();
    appendCentered(equipo, HOST_TEXT_COLOR, BOLD_MENU_TEXT);
    appendBorderLine();
    appendMenuItem("MENU DE OPCIONES:", MENU_TEXT_COLOR, BOLD_MENU_TEXT);
    appendMenuItem("1.- INSTALACION.", MENU_TEXT_COLOR, BOLD_MENU_TEXT);
    appendMenuItem("2.- VALIDACION.", MENU_TEXT_COLOR, BOLD_MENU_TEXT);
    appendMenuItem("3.- DESINSTALACION DE ARCHIVOS.", MENU_TEXT_COLOR, BOLD_MENU_TEXT);
    appendBorderLine();
    appendMenuItem("0.- TERMINAR MACRO.", MENU_TEXT_COLOR, BOLD_MENU_TEXT);
    appendBorderLine();
  }

  private static void showMenuDialog() {
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

    // Acciones según opción
    switch (input) {
      case "0" -> showMessage("Macro finalizada.");
      case "1" -> showMessage("Has seleccionado:\n1.- INSTALACION.");
      case "2" -> showMessage("Has seleccionado:\n2.- VALIDACION.");
      case "3" -> showMessage("Has seleccionado:\n3.- DESINSTALACION DE ARCHIVOS.");
    }
  }

  // =========================
  // ELEMENTOS VISUALES
  // =========================

  private static void appendBorderLine() throws BadLocationException {
    String border = String.valueOf(BORDER_CHAR).repeat(WIDTH);
    appendStyled(border + "\n", BORDER_COLOR, BOLD_BORDER_CHAR);
  }

  private static void appendMenuItem(String text, Color textColor, boolean bold) throws BadLocationException {
    String leftSide = BORDER_SIDE + " ";
    String rightSide = " " + BORDER_SIDE;

    int totalSides = leftSide.length() + rightSide.length();
    int padding = WIDTH - totalSides - text.length();
    String spaces = " ".repeat(Math.max(0, padding));

    appendStyled(leftSide, BORDER_COLOR, BOLD_BORDER_SIDE);
    appendStyled(text, textColor, bold);
    appendStyled(spaces + rightSide + "\n", BORDER_COLOR, BOLD_BORDER_SIDE);
  }

  private static void appendCentered(String text, Color color, boolean bold) throws BadLocationException {
    int padding = (WIDTH - text.length()) / 2;
    String line = " ".repeat(Math.max(0, padding)) + text;
    appendStyled(line + "\n", color, bold);
  }

  private static void appendEmptyLine() throws BadLocationException {
    appendStyled("\n", MENU_TEXT_COLOR, false);
  }

  // =========================
  // UTILIDAD DE ESTILOS
  // =========================

  private static void appendStyled(String text, Color color, boolean bold) throws BadLocationException {
    String key = color.toString() + bold;
    Style style = styleCache.computeIfAbsent(key, k -> {
      Style s = textPane.addStyle(k, null);
      StyleConstants.setForeground(s, color);
      StyleConstants.setBold(s, bold);
      return s;
    });
    doc.insertString(doc.getLength(), text, style);
  }

  // =========================
  // MENSAJE EMERGENTE
  // =========================

  private static void showMessage(String msg) {
    JOptionPane.showMessageDialog(null, msg);
  }
}
