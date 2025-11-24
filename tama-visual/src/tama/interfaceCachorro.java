package tama;

import javax.swing.*;
import java.awt.*;

public class interfaceCachorro extends JFrame {

    private JLabel imagemLabel;
    private JLabel tempoLabel;
    private Jogo jogo;
    private Cachorro cachorro;

    public interfaceCachorro(Cachorro cachorro) {
        this.cachorro = cachorro;

        // ======= CONFIGURAÇÃO JANELA =======
        setTitle("Cachorro - " + cachorro.nome);
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
                        getWidth(), getHeight(), new Color(232, 45, 253));
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
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        // ======= TOPO (Título + Subtítulo) =======
        JPanel topo = new JPanel(new GridLayout(2, 1));
        topo.setBackground(new Color(0xFFF5F5));

        JLabel titulo = new JLabel("Cachorro", SwingConstants.CENTER);
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
        JButton btnRoer = criarBotao("Roer Osso");
        JButton btnLatir = criarBotao("Latir");

        // AÇÕES
        btnAlimentar.addActionListener(e -> {
            cachorro.alimentar();
            imagemLabel.setText(cachorro.nome + " está comendo.");
            atualizarStatus();
        });

        btnDormir.addActionListener(e -> {
            cachorro.dormir();
            imagemLabel.setText(cachorro.nome + " está dormindo.");
            atualizarStatus();
        });

        btnBrincar.addActionListener(e -> {
            cachorro.brincar();
            imagemLabel.setText(cachorro.nome + " está brincando!");
            atualizarStatus();
        });

        btnVeterinario.addActionListener(e -> {
            cachorro.irVeterinario();
            imagemLabel.setText(cachorro.nome + " foi ao veterinário.");
            atualizarStatus();
        });

        btnPassear.addActionListener(e -> {
            cachorro.passear();
            imagemLabel.setText(cachorro.nome + " está passeando.");
            atualizarStatus();
        });

        btnRoer.addActionListener(e -> {
            imagemLabel.setText(cachorro.nome + " está roendo um osso!");
        });

        btnLatir.addActionListener(e -> {
            imagemLabel.setText(cachorro.nome + " diz: " + cachorro.emitirSom());
        });

        botoes.add(btnAlimentar);
        botoes.add(btnDormir);
        botoes.add(btnBrincar);
        botoes.add(btnVeterinario);
        botoes.add(btnPassear);
        botoes.add(btnRoer);
        botoes.add(btnLatir);

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

        // Atualiza a tela com os valores iniciais do cachorro
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

        // ===== HOVER =====
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0x9760B3)); // cor quando passa mouse
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0xCA88EB)); // cor normal
            }
        });

        return btn;
    }

    // ATUALIZA TEXTO DA TELA PRETA
    private void atualizarStatus() {
        if (cachorro == null)
            return;

        imagemLabel.setText(
                "<html><center>" +
                        "<h3>" + cachorro.nome + "</h3>" +
                        "Som: " + cachorro.emitirSom() + "<br><br>" +
                        "Energia: " + cachorro.energia + "<br>" +
                        "Fome: " + cachorro.fome + "<br>" +
                        "Sede: " + cachorro.sede + "<br>" +
                        "Felicidade: " + cachorro.felicidade + "<br>" +
                        "Saúde: " + cachorro.saude +
                        "</center></html>");
    }

    // MAIN PARA TESTE ISOLADO
    public static void main(String[] args) {
        Cachorro rex = new Cachorro();
        rex.nome = "rex";
        rex.energia = 50;
        rex.fome = 50;
        rex.sede = 50;
        rex.felicidade = 50;
        rex.saude = 50;

        SwingUtilities.invokeLater(() -> new interfaceCachorro(rex));
    }
}
