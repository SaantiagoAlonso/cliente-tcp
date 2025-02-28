package co.scastillos;

import co.scastillos.vista.ClienteGUI;

import javax.swing.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SwingUtilities.invokeLater(() -> new ClienteGUI().setVisible(true));
    }
}
