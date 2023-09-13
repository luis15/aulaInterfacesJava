import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Clientes");
        frame.setContentPane(new Clientes().getPanel());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}