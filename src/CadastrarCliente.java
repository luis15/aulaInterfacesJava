import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastrarCliente extends JFrame{
    private JTextField txtNome;
    private JTextField txtEmail;
    private JButton btnCadastrar;
    private JPanel panel;
    private JLabel lblNome;
    private JLabel lblEmail;

    private static final String URL = "jdbc:mysql://localhost/cliente_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";

    public CadastrarCliente(){
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                try {
                    connection = DriverManager.getConnection(URL, USUARIO, SENHA);
                    inserirCliente(connection, txtNome.getText(), txtEmail.getText());

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }

    private static void inserirCliente(Connection connection, String nome, String email) throws SQLException {
        String insertQuery = "INSERT INTO clientes (nome, email) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            System.out.println("Cliente inserido com sucesso!");
        }
    }



}
