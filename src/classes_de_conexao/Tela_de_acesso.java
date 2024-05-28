package classes_de_conexao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tela_de_acesso extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfUsuario;
    private JPasswordField pfsSenha;

    // Método principal para executar a aplicação
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tela_de_acesso frame = new Tela_de_acesso();
                    frame.setVisible(true); // Torna a janela visível
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Construtor para criar a interface
    public Tela_de_acesso() {
        setTitle("Tela de acesso"); // Define o título da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define a operação de fechamento
        setBounds(100, 100, 358, 249); // Define a posição e tamanho da janela
        contentPane = new JPanel(); // Cria um painel para conteúdos
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Define a borda do painel
        setContentPane(contentPane); // Define o painel como conteúdo principal da janela
        contentPane.setLayout(null); // Define o layout do painel como nulo para posicionamento manual

        // Label para o nome de usuário
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setForeground(new Color(0, 128, 255)); // Define a cor do texto
        lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16)); // Define a fonte do texto
        lblUsuario.setBounds(37, 60, 76, 53); // Define a posição e tamanho do label
        contentPane.add(lblUsuario); // Adiciona o label ao painel

        // Label para a senha
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(new Color(0, 128, 255));
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSenha.setBounds(37, 110, 76, 53);
        contentPane.add(lblSenha);

        // Campo de texto para o usuário
        tfUsuario = new JTextField();
        tfUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
        tfUsuario.setBounds(140, 78, 184, 20);
        contentPane.add(tfUsuario);
        tfUsuario.setColumns(10);

        // Campo de senha para a senha
        pfsSenha = new JPasswordField();
        pfsSenha.setBounds(140, 128, 184, 20);
        contentPane.add(pfsSenha);

        // Botão para entrar
        JToggleButton tglbtnEntrar = new JToggleButton("Entrar");
        tglbtnEntrar.addActionListener(e -> { // Adiciona ação ao clicar no botão
            try {
                Connection con = conexao.faz_conexao(); // Obtém a conexão com o banco de dados
                String sql = "SELECT * FROM dados_senhas WHERE usuario=? AND senha=?"; // SQL query
                PreparedStatement stmt = con.prepareStatement(sql); // Prepara a declaração SQL
                stmt.setString(1, tfUsuario.getText()); // Configura o primeiro parâmetro da query
                stmt.setString(2, new String(pfsSenha.getPassword())); // Configura o segundo parâmetro da query
                // Aqui você deve executar a query e tratar o resultado
                
                ResultSet rs = stmt.executeQuery();
                
                if(rs.next()) {
                	JOptionPane.showMessageDialog(null,"Esse usuario existe");
                }else {
                	JOptionPane.showMessageDialog(null,"Usuário/Senha incorreto");
                }
                
                stmt.close();
                con.close();
                
            } catch (SQLException ex) {
                ex.printStackTrace(); // Imprime o stack trace do erro
            }
        });

        tglbtnEntrar.setBackground(new Color(0, 0, 0)); // Define a cor de fundo do botão
        tglbtnEntrar.setForeground(new Color(0, 128, 255)); // Define a cor do texto do botão
        tglbtnEntrar.setFont(new Font("Tahoma", Font.BOLD, 16)); // Define a fonte do botão
        tglbtnEntrar.setBounds(178, 176, 118, 23); // Define a posição e tamanho do botão
        contentPane.add(tglbtnEntrar); // Adiciona o botão ao painel
    }
}
