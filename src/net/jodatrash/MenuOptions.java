package net.jodatrash;

import java.net.InetAddress;
import java.util.Scanner;

public class MenuOptions {
  public static void main(String[] args) throws Exception {
    // Obtener nombre del equipo
    String hostname = "EQUIPO: \\" + InetAddress.getLocalHost().getHostName();

    // Contenido del menú
    String[] menu = {
            "MENU DE OPCIONES:",
            "1.- INSTALACION.",
            "2.- VALIDACION.",
            "3.- DESINSTALACION."
    };
    String opcionFinal = "0.- TERMINAR MACRO.";

    // Calcular ancho del cuadro
    int ancho = Math.max(
            Math.max(getMaxLength(menu), opcionFinal.length()),
            hostname.length()) + 16; // 2 para bordes + 2 para espacio

    // Mostrar nombre del equipo centrado (fuera del cuadro)
    System.out.println();
    printCentered(hostname, ancho);

    // Imprimir cuadro completo
    printFullBox(menu, opcionFinal, ancho);

    // Leer y validar opción
    Scanner scanner = new Scanner(System.in);
    String opcion;
    do {
      System.out.print("\nIngrese una opción [0-3]: ");
      opcion = scanner.nextLine().trim();
    } while (!opcion.matches("[0-3]"));

    if (opcion.equals("0")) {
      System.out.println("Macro finalizada.");
    } else {
      System.out.println("Opción seleccionada: " + opcion);
    }
  }

  private static int getMaxLength(String[] lines) {
    int max = 0;
    for (String line : lines) {
      if (line.length() > max)
        max = line.length();
    }
    return max;
  }

  private static void printCentered(String text, int width) {
    int padding = (width - text.length()) / 2;
    System.out.println(" ".repeat(Math.max(0, padding)) + text);
  }

  private static void printFullBox(String[] menu, String finalOption, int width) {
    String borde = "*".repeat(width);
    System.out.println(borde);

    for (String line : menu) {
      int espacios = width - 2 - line.length();
      System.out.println("* " + line + " ".repeat(espacios - 1) + "*");
    }

    // Línea divisoria
    System.out.println("*" + "*".repeat(width - 2) + "*");

    // Opción final
    int espacios = width - 2 - finalOption.length();
    System.out.println("* " + finalOption + " ".repeat(espacios - 1) + "*");

    System.out.println(borde);
  }
}
