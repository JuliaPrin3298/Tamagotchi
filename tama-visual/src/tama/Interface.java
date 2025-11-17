package tama;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Interface extends JFrame {

    private JLabel imagemLabel;
    private JLabel tempoLabel;
    private Jogo jogo;
    private Animal animalAtual;

    public Interface() {
        // ======= CONFIGURAÇÃO JANELA =======
        setTitle("Tamagotchi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        JLabel titulo = new JLabel("Tamagotchi", SwingConstants.CENTER);
        titulo.setFont(new Font("DynaPuff", Font.BOLD, 36));
        titulo.setForeground(Color.BLACK);

        JLabel subtitulo = new JLabel("Escolha o seu animal", SwingConstants.CENTER);
        subtitulo.setFont(new Font("DynaPuff", Font.PLAIN, 20));
        subtitulo.setForeground(Color.DARK_GRAY);

        topo.add(titulo);
        topo.add(subtitulo);

        // ======= TELA DO ANIMAL =======
        JPanel tela = new JPanel(new GridBagLayout());
        tela.setPreferredSize(new Dimension(300, 300));
        tela.setBackground(Color.BLACK);
        tela.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3, true));

        imagemLabel = new JLabel("Nenhum animal selecionado");
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagemLabel.setForeground(Color.WHITE);
        tela.add(imagemLabel);

        // ======= LABEL TEMPO =======
        tempoLabel = new JLabel("Tempo: 0s", SwingConstants.CENTER);
        tempoLabel.setFont(new Font("DynaPuff", Font.PLAIN, 16));
        tempoLabel.setForeground(Color.BLACK);

        // ======= BOTÕES DE ANIMAIS =======
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        botoes.setBackground(new Color(0xFFF5F5));

        String[] nomes = {"Cachorro", "Gato", "Panda", "Corvo"};
        for (String nome : nomes) {
            JButton btn = new JButton(nome);
            btn.setFocusPainted(false);
            btn.setFont(new Font("DynaPuff", Font.PLAIN, 14));
            btn.setBackground(new Color(0xCA88EB));
            btn.setForeground(Color.BLACK);
            btn.setBorder(BorderFactory.createLineBorder(new Color(0xCA88EB), 2, true));
            btn.setPreferredSize(new Dimension(90, 40));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Hover
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(0x9760B3));
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(new Color(0xCA88EB));
                }
            });

            // Clique
            btn.addActionListener(e -> selecionarAnimal(nome));

            botoes.add(btn);
        }

        // ======= MONTAGEM FINAL =======
        container.add(topo, BorderLayout.NORTH);
        container.add(tela, BorderLayout.CENTER);
        container.add(botoes, BorderLayout.SOUTH);

        background.add(container); // centraliza o container

        // ======= CRIAR OBJETO JOGO =======
        jogo = new Jogo();

        // Timer para atualizar tempo de jogo
        Timer timer = new Timer(1000, e -> {
            if (jogo != null) {
                tempoLabel.setText("Tempo: " + jogo.tempoDeJogo + "s");
            }
        });
        timer.start();

        setVisible(true);
    }

    // ======= MÉTODO PARA SELECIONAR ANIMAL =======
    private void selecionarAnimal(String nomeAnimal) {
        switch (nomeAnimal) {
            case "Cachorro":
                animalAtual = new Cachorro();
                animalAtual.nome = "Rex";
                new interfaceCachorro((Cachorro) animalAtual);  // ← abre a janela do CAchorro
                break;
            case "Gato":
                animalAtual = new Gato();
                animalAtual.nome = "Mimi";
                new interfaceGato((Gato) animalAtual);  // ← abre a janela do Gato
                break;
            case "Panda":
                animalAtual = new Panda();
                animalAtual.nome = "Pelin";
                new interfacePanda((Panda) animalAtual);  // ← abre a janela do Panda
                break;
            case "Corvo":
                animalAtual = new Corvo();
                animalAtual.nome = "Coro";
                new interfaceCorvo((Corvo) animalAtual);
                break;
        }

        imagemLabel.setText(animalAtual.nome + " está ativo! Som: " + animalAtual.emitirSom());
        jogo.iniciar(animalAtual);
        System.out.println(animalAtual.nome + " selecionado!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interface::new);
    }
}
