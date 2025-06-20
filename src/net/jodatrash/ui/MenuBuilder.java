package net.jodatrash.ui;

import net.jodatrash.config.AppConfig;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class MenuBuilder {
  private final JTextPane textPane;

  public MenuBuilder(JTextPane textPane) {
    this.textPane = textPane;
  }

  public void build(String hostname) throws BadLocationException {
    String equipo = "EQUIPO: \\" + hostname;

    appendEmptyLine();
    appendCentered(equipo, AppConfig.HOST_TEXT_COLOR, AppConfig.BOLD_MENU_TEXT);
    appendBorderLine();
    appendMenuItem("MENU DE OPCIONES:");
    appendMenuItem("1.- INSTALACION.");
    appendMenuItem("2.- VALIDACION.");
    appendMenuItem("3.- DESINSTALACION DE ARCHIVOS.");
    appendBorderLine();
    appendMenuItem("0.- TERMINAR MACRO.");
    appendBorderLine();
  }

  private void appendBorderLine() throws BadLocationException {
    String border = String.valueOf(AppConfig.BORDER_CHAR).repeat(AppConfig.WIDTH);
    StyleUtil.appendStyled(textPane, border + "\n", AppConfig.BORDER_COLOR, AppConfig.BOLD_BORDER_CHAR);
  }

  private void appendMenuItem(String text) throws BadLocationException {
    String left = AppConfig.BORDER_SIDE + " ";
    String right = " " + AppConfig.BORDER_SIDE;
    int totalSides = left.length() + right.length();
    int padding = AppConfig.WIDTH - totalSides - text.length();
    String spaces = " ".repeat(Math.max(0, padding));

    StyleUtil.appendStyled(textPane, left, AppConfig.BORDER_COLOR, AppConfig.BOLD_BORDER_SIDE);
    StyleUtil.appendStyled(textPane, text, AppConfig.MENU_TEXT_COLOR, AppConfig.BOLD_MENU_TEXT);
    StyleUtil.appendStyled(textPane, spaces + right + "\n", AppConfig.BORDER_COLOR, AppConfig.BOLD_BORDER_SIDE);
  }

  private void appendCentered(String text, Color color, boolean bold) throws BadLocationException {
    int padding = (AppConfig.WIDTH - text.length()) / 2;
    String line = " ".repeat(Math.max(0, padding)) + text;
    StyleUtil.appendStyled(textPane, line + "\n", color, bold);
  }

  private void appendEmptyLine() throws BadLocationException {
    StyleUtil.appendStyled(textPane, "\n", AppConfig.MENU_TEXT_COLOR, false);
  }
}
