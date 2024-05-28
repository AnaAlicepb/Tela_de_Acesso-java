package classes_de_conexao;  // Define o pacote onde a classe está localizada

import java.awt.EventQueue;  // Importa classe para manipulação segura de threads da GUI
import java.awt.Font;         // Importa classe para definição de fontes
import java.awt.Color;        // Importa classe para definição de cores
import javax.swing.*;         // Importa todas as classes do Swing para interface gráfica
import javax.swing.border.EmptyBorder;

import java.sql.*;            // Importa classes do SQL para operações com banco de dados

public class Tela_de_acesso extends JFrame {  // Declara a classe que herda de JFrame para criar uma janela

    private static final long serialVersionUID = 1L;  // Identificador de versão para a serialização
    private JPanel contentPane;  // Painel principal para os componentes da interface
    private JTextField tfUsuario;  // Campo de texto para o nome do usuário
    private JPasswordField pfSenha;  // Campo de senha para a senha do usuário

    // Método principal que inicia a aplicação
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {  // Garante que a GUI seja criada no Event Dispatch Thread
            try {
                Tela_de_acesso frame = new Tela_de_acesso();  // Cria o frame
                frame.setVisible(true);  // Torna o frame visível
            } catch (Exception e) {
                e.printStackTrace();  // Imprime no console o stack trace de exceções não esperadas
            }
        });
    }

    // Construtor que cria a janela e organiza os componentes
    public Tela_de_acesso() {
        setTitle("Tela de acesso");  // Define o título da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Define o comportamento padrão para fechar a janela
        setBounds(100, 100, 358, 249);  // Define a posição e o tamanho da janela
        contentPane = new JPanel();  // Cria o painel para conter os componentes
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));  // Define a borda do painel
        setContentPane(contentPane);  // Define o painel como o conteúdo principal da janela
        contentPane.setLayout(null);  // Desativa o gerenciador de layout para posicionamento manual

        JLabel lblUsuario = new JLabel("Usuario");  // Cria e configura o rótulo do nome do usuário
        lblUsuario.setForeground(new Color(0, 128, 255));  // Define a cor do texto
        lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));  // Define a fonte do texto
        lblUsuario.setBounds(37, 60, 76, 53);  // Define a posição e o tamanho do rótulo
        contentPane.add(lblUsuario);  // Adiciona o rótulo ao painel

        JLabel lblSenha = new JLabel("Senha");  // Cria e configura o rótulo da senha
        lblSenha.setForeground(new Color(0, 128, 255));  // Define a cor do texto
        lblSenha.setFont(new Font("Tahoma", Font.BOLD, 16));  // Define a fonte do texto
        lblSenha.setBounds(37, 110, 76, 53);  // Define a posição e o tamanho do rótulo
        contentPane.add(lblSenha);  // Adiciona o rótulo ao painel

        tfUsuario = new JTextField();  // Cria o campo de texto para o nome do usuário
        tfUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));  // Define a fonte do campo de texto
        tfUsuario.setBounds(140, 78, 184, 20);  // Define a posição e o tamanho do campo de texto
        contentPane.add(tfUsuario);  // Adiciona o campo de texto ao painel
        tfUsuario.setColumns(10);  // Define o número de colunas do campo de texto

        pfSenha = new JPasswordField();  // Cria o campo de senha para a senha
        pfSenha.setBounds(140, 128, 184, 20);  // Define a posição e o tamanho do campo de senha
        contentPane.add(pfSenha);  // Adiciona o campo de senha ao painel

        JToggleButton tglbtnEntrar = new JToggleButton("Entrar");  // Cria o botão de entrar
        tglbtnEntrar.addActionListener(e -> authenticateUser());  // Adiciona um ouvinte de ação que chama o método de autenticação
        tglbtnEntrar.setBackground(new Color(255, 255, 255));  // Define a cor de fundo do botão
        tglbtnEntrar.setForeground(new Color(0, 128, 255));  // Define a cor do texto do botão
        tglbtnEntrar.setFont(new Font("Tahoma", Font.BOLD, 16));  // Define a fonte do botão
        tglbtnEntrar.setBounds(178, 176, 118, 23);  // Define a posição e o tamanho do botão
        contentPane.add(tglbtnEntrar);  // Adiciona o botão ao painel
    }

    // Método para autenticar o usuário
    private void authenticateUser() {
        String usuario = tfUsuario.getText();  // Obtém o nome do usuário do campo de texto
        String senha = new String(pfSenha.getPassword());  // Obtém a senha do campo de senha
        String sql = "SELECT * FROM dados_senhas WHERE usuario=? AND senha=?";  // SQL query para verificar o usuário
        try (Connection con = conexao.faz_conexao();  // Tenta estabelecer uma conexão com o banco
             PreparedStatement stmt = con.prepareStatement(sql)) {  // Prepara a declaração SQL
            stmt.setString(1, usuario);  // Define o primeiro parâmetro da query
            stmt.setString(2, senha);  // Define o segundo parâmetro da query
            try (ResultSet rs = stmt.executeQuery()) {  // Executa a query e obtém o resultado
                if (rs.next()) {  // Verifica se há resultados
                	Tela_cadastro exibir = new Tela_cadastro();  // Corretamente cria uma nova instância da classe Tela_cadastro
                	exibir.setVisible(true);                     // Torna a nova tela de cadastro visível
                	setVisible(false);                           // Esconde a tela atual

                } else {
                    JOptionPane.showMessageDialog(this, "Usuário/Senha incorreto");  // Mostra mensagem de erro
                }
            }
        } catch (SQLException ex) {  // Captura exceções de SQL
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados: " + ex.getMessage());  // Mostra mensagem de erro de conexão
            ex.printStackTrace();  // Imprime o erro no console
        }
    }
}
