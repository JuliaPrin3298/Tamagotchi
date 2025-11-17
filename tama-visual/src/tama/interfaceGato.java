package tama;

import javax.swing.*;
import java.awt.*;

public class interfaceGato extends JFrame {

    private JLabel imagemLabel;
    private JLabel tempoLabel;
    private Jogo jogo;
    private Gato gato; // usamos tipo Gato diretamente

    public interfaceGato(Gato gato) {
        this.gato = gato;

        // ======= CONFIGURAÇÃO JANELA =======
        setTitle("Gato - " + gato.nome);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // ======= FUNDO COM GRADIENTE =======
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradiente = new GradientPaint(
                        0, 0, new Color(34, 133, 195),
                        getWidth(), getHeight(), new Color(232, 45, 253)
                );
                g2d.setPaint(gradiente);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setLayout(new GridBagLayout()); // centraliza o container
        add(background);

        // ======= CONTAINER CENTRAL =======
        JPanel container = new JPanel(new BorderLayout(10, 20));
        container.setPreferredSize(new Dimension(520, 580));
        container.setBackground(new Color(0xFFF5F5));
        container.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // ======= TOPO (Título + Subtítulo) =======
        JPanel topo = new JPanel(new GridLayout(2, 1));
        topo.setBackground(new Color(0xFFF5F5));

        JLabel titulo = new JLabel("Gato", SwingConstants.CENTER);
        titulo.setFont(new Font("DynaPuff", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);

        JLabel subtitulo = new JLabel("Escolha sua ação", SwingConstants.CENTER);
        subtitulo.setFont(new Font("DynaPuff", Font.PLAIN, 20));
        subtitulo.setForeground(Color.DARK_GRAY);

        topo.add(titulo);
        topo.add(subtitulo);

        // ======= TELA DO ANIMAL =======
        JPanel tela = new JPanel(new GridBagLayout());
        tela.setPreferredSize(new Dimension(300, 300));
        tela.setBackground(Color.BLACK);
        tela.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3, true));

        imagemLabel = new JLabel("Nenhuma Ação");
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagemLabel.setForeground(Color.WHITE);
        tela.add(imagemLabel);

        // ======= LABEL TEMPO =======
        tempoLabel = new JLabel("Tempo: 0s", SwingConstants.CENTER);
        tempoLabel.setFont(new Font("DynaPuff", Font.PLAIN, 16));
        tempoLabel.setForeground(Color.BLACK);

        // ======= BOTÕES DE AÇÕES =======
       JPanel botoes = new JPanel(new GridLayout(3, 3, 10, 10));
        botoes.setBackground(new Color(0xFFF5F5));

        JButton btnAlimentar = criarBotao("Alimentar");
        JButton btnDormir = criarBotao("Dormir");
        JButton btnBrincar = criarBotao("Brincar");
        JButton btnVeterinario = criarBotao("Veterinário");
        JButton btnPassear = criarBotao("Passear");
        JButton btnRato = criarBotao("Caçar Rato");
        JButton btnMiado = criarBotao("Miado");

        // AÇÕES
        btnAlimentar.addActionListener(e -> {
            gato.alimentar();
            imagemLabel.setText(gato.nome + " está comendo.");
            atualizarStatus();
        });

        btnDormir.addActionListener(e -> {
            gato.dormir();
            imagemLabel.setText(gato.nome + " está dormindo.");
            atualizarStatus();
        });

        btnBrincar.addActionListener(e -> {
            gato.brincar();
            imagemLabel.setText(gato.nome + " está brincando!");
            atualizarStatus();
        });

        btnVeterinario.addActionListener(e -> {
            gato.irVeterinario();
            imagemLabel.setText(gato.nome + " foi ao veterinário.");
            atualizarStatus();
        });

        btnPassear.addActionListener(e -> {
            gato.passear();
            imagemLabel.setText(gato.nome + " está passeando.");
            atualizarStatus();
        });

        btnRato.addActionListener(e -> {
            imagemLabel.setText(gato.nome + " está correndo atrás do rato!");
        });

        btnMiado.addActionListener(e -> {
            imagemLabel.setText(gato.nome + " diz: " + gato.emitirSom());
        });

        botoes.add(btnAlimentar);
        botoes.add(btnDormir);
        botoes.add(btnBrincar);
        botoes.add(btnVeterinario);
        botoes.add(btnPassear);
        botoes.add(btnRato);
        botoes.add(btnMiado);

        // ======= MONTAGEM FINAL =======
        container.add(topo, BorderLayout.NORTH);
        container.add(tela, BorderLayout.CENTER);

        // adiciona o tempoLabel acima dos botões
        JPanel sul = new JPanel(new BorderLayout());
        sul.setBackground(new Color(0xFFF5F5));
        sul.add(tempoLabel, BorderLayout.NORTH);
        sul.add(botoes, BorderLayout.SOUTH);

        container.add(sul, BorderLayout.SOUTH);

        background.add(container); // centraliza o container

        // ======= CRIAR OBJETO JOGO (opcional) =======
        jogo = new Jogo();

        // Timer para atualizar tempo de jogo (só atualiza label de tempo local)
        Timer timer = new Timer(1000, e -> {
            if (jogo != null) {
                tempoLabel.setText("Tempo: " + jogo.tempoDeJogo + "s");
            }
        });
        timer.start();

        // Atualiza a tela com os valores iniciais do gato
        atualizarStatus();
        setVisible(true);
    }

    // CRIA BOTÃO PADRÃO
    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setFont(new Font("DynaPuff", Font.PLAIN, 14));
        btn.setBackground(new Color(0xCA88EB));
        btn.setForeground(Color.BLACK);
        btn.setBorder(BorderFactory.createLineBorder(new Color(0xCA88EB), 2, true));
        btn.setPreferredSize(new Dimension(90, 40));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }

    // ATUALIZA TEXTO DA TELA PRETA
    private void atualizarStatus() {
        if (gato == null) return;

        imagemLabel.setText(
                "<html><center>" +
                        "<h3>" + gato.nome + "</h3>" +
                        "Som: " + gato.emitirSom() + "<br><br>" +
                        "Energia: " + gato.energia + "<br>" +
                        "Fome: " + gato.fome + "<br>" +
                        "Sede: " + gato.sede + "<br>" +
                        "Felicidade: " + gato.felicidade + "<br>" +
                        "Saúde: " + gato.saude +
                "</center></html>"
        );
    }

    // MAIN PARA TESTE ISOLADO
    public static void main(String[] args) {
        Gato mimi = new Gato();
        mimi.nome = "Mimi";
        mimi.energia = 50;
        mimi.fome = 50;
        mimi.sede = 50;
        mimi.felicidade = 50;
        mimi.saude = 50;

        SwingUtilities.invokeLater(() -> new interfaceGato(mimi));
    }
}
