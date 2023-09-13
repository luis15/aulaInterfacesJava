import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Clientes  extends JFrame{
    private JPanel panel;
    private JTable tableClientes;
    private JButton btnAddCliente;

    private Connection connection;

    private DefaultTableModel tableModel;
    private static final String URL = "jdbc:mysql://localhost/cliente_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "root";



    public Clientes() {
        try {
            connection = DriverManager.getConnection(URL, USUARIO, SENHA);

            Object[][] data = consultarClientes();
            System.out.println(data[0][0]);
            String[] columns = {"id", "nome", "email"};
            tableClientes.setEnabled(false);
            //tableClientes = new JTable(data, columns);
            tableClientes.setModel(new DefaultTableModel(data, columns));


           // JScrollPane scrollPane = new JScrollPane(tableClientes);
            tableClientes.setEnabled(true);
          //  add(scrollPane);

            connection.close();
        }catch(Exception e ){
            throw new RuntimeException(e);
        }

        btnAddCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Cadastrar Clientes");
                frame.setContentPane(new CadastrarCliente().getPanel());
                frame.setSize(500, 500);
                frame.setVisible(true);
            }
        });

    }

    private String[][]  consultarClientes() throws SQLException {
        String selectQuery = "SELECT * FROM clientes";
        String[][] query;
        try (
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

        ){

            int i=0;
            query = new String[100][3];

            while (resultSet.next()) {
                query[i][0] = resultSet.getString("id");
                query[i][1] =  resultSet.getString("nome");
                query[i][2] = resultSet.getString("email");

                i++;
            }
            return query;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public JPanel getPanel(){
        return panel;
    }
}
