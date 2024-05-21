package jogo;

import jplay.Scene;
import jplay.Window;
import jplay.Keyboard;
import jplay.Sprite;
import java.awt.Color;
import java.awt.Font;

public class Cenario1 {

    private Window janela; // Janela do jogo
    private Scene cena; // Cena do jogo
    private Jogador jogador; // Instância do jogador
    private Keyboard teclado; // Teclado para controle
    private Inimigo1[] inimigo1; // Array de inimigos
    private int zumbisEliminados = 0; // Contador de zumbis eliminados
    private long lastEnemySpawnTime = 0; // Tempo da última aparição do inimigo
    private long enemySpawnInterval = 400; // Intervalo entre aparições dos inimigos
    private Font f = new Font("arial", Font.BOLD, 30); // Fonte para texto
    private boolean gameOver = false; // Indica se o jogo acabou
    private boolean victory = false; // Indica se o jogador venceu
    private int level = 1; // Nível do jogo

    public Cenario1(Window window) {
        // Inicializa os componentes do cenário
        janela = window;
        cena = new Scene();
        cena.loadFromFile("src//scn//Cenario1.scn");
        jogador = new Jogador(400, 450);
        teclado = janela.getKeyboard();
        inimigo1 = new Inimigo1[10];
        run(); // Inicia o loop do jogo
    }

    private void run() {
        while (true) {
            if (!gameOver && !victory) {
                // Desenha a cena e atualiza o estado do jogador
                cena.draw();
                jogador.energia(janela);
                jogador.controle(janela, teclado);
                jogador.draw();

                // Cria um novo inimigo se o intervalo for atingido e o jogador estiver vivo
                if (System.currentTimeMillis() - lastEnemySpawnTime >= enemySpawnInterval && jogador.energia > 0) {
                    spawnEnemy();
                    lastEnemySpawnTime = System.currentTimeMillis();
                }

                // Atualiza o estado de cada inimigo
                for (int i = 0; i < inimigo1.length; i++) {
                    if (inimigo1[i] != null) {
                        inimigo1[i].draw();
                        inimigo1[i].perseguir(jogador.x, jogador.y);
                        inimigo1[i].morrer();
                        inimigo1[i].atacar(jogador);
                        jogador.atirar(janela, cena, teclado, inimigo1[i]);

                        // Remove o inimigo se a energia for zero
                        if (inimigo1[i].energia <= 0) {
                            inimigo1[i] = null;
                            zumbisEliminados++;
                            if (zumbisEliminados >= 100) {
                                victory = true;
                            } else if (zumbisEliminados % 10 == 0) {
                                level++;
                                enemySpawnInterval = Math.max(100, enemySpawnInterval - 50); // Aumenta a dificuldade mais rapidamente
                            }
                        }
                    }
                }

                // Desenha o número de zumbis eliminados
                janela.drawText("Eliminados: " + zumbisEliminados, janela.getWidth() - 300, 30, Color.GREEN, f);
                janela.update();

                // Verifica se o jogador morreu
                if (jogador.energia <= 0) {
                    gameOver = true;
                }

            } else if (gameOver) {
                // Desenha a tela de game over
                drawGameOver();
                if (teclado.keyDown(Keyboard.ENTER_KEY)) {
                    gameOver = false;
                    resetGame();
                }
            } else if (victory) {
                // Desenha a tela de vitória
                drawVictory();
                if (teclado.keyDown(Keyboard.ENTER_KEY)) {
                    janela.exit(); // Termina o jogo
                }
            }
        }
    }

    // Reinicia o jogo
    private void resetGame() {
        zumbisEliminados = 0;
        jogador.energia = 250;
        for (int i = 0; i < inimigo1.length; i++) {
            inimigo1[i] = null;
        }
        enemySpawnInterval = 400;
        cena.loadFromFile("src//scn//Cenario1.scn");
    }

    // Desenha a tela de game over
    private void drawGameOver() {
        Sprite gameOverImage = new Sprite("src//imagens//gameover.png");
        gameOverImage.x = (janela.getWidth() - gameOverImage.width) / 2;
        gameOverImage.y = (janela.getHeight() - gameOverImage.height) / 2;
        gameOverImage.draw();
        janela.update();
    }

    // Desenha a tela de vitória
    private void drawVictory() {
        Sprite victoryImage = new Sprite("src//imagens//vitoria.png");
        victoryImage.x = (janela.getWidth() - victoryImage.width) / 2;
        victoryImage.y = (janela.getHeight() - victoryImage.height) / 2;
        victoryImage.draw();
        janela.update();
    }

    // Cria um novo inimigo
    private void spawnEnemy() {
        for (int i = 0; i < inimigo1.length; i++) {
            if (inimigo1[i] == null) {
                int spawnX = (int) (Math.random() * 2);
                int spawnY = 450;
                if (spawnX == 0) {
                    inimigo1[i] = new Inimigo1(0, spawnY);
                } else {
                    inimigo1[i] = new Inimigo1(janela.getWidth() - 64, spawnY);
                }
                break;
            }
        }
    }
}
