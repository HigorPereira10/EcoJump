package jogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import jplay.Keyboard;
import jplay.Scene;
import jplay.Window;

public class Jogador extends Ator {

    double velocidade = 0.03; // Velocidade do jogador
    protected int direcao = 3; // Direção inicial do jogador
    boolean movendo = false; // Indica se o jogador está se movendo

    public Jogador(int x, int y) {
        super("src//imagens//jogador.png", 20);
        this.x = x;
        this.y = y;
        this.setTotalDuration(2000); // Duração total da animação
    }

    ControleTiros tiros = new ControleTiros(); // Controle dos tiros do jogador

    // Verifica se o jogador está atirando
    public void atirar(Window janela, Scene cena, Keyboard teclado, Ator inimigo) {
        if (teclado.keyDown(KeyEvent.VK_A)) {
            tiros.adicionaTiro(x + 5, y + 11, direcao, cena);
        }
        tiros.run(inimigo);
    }

    // Controla o movimento do jogador
    public void controle(Window janela, Keyboard teclado) {
        if (teclado.keyDown(Keyboard.LEFT_KEY)) {
            if (this.x > 0)
                this.x -= velocidade;
            if (direcao != 1) {
                setSequence(4, 8);
                direcao = 1;
            }
            movendo = true;
        } else if (teclado.keyDown(Keyboard.RIGHT_KEY)) {
            if (this.x < janela.getWidth() - 60)
                this.x += velocidade;
            if (direcao != 2) {
                setSequence(8, 12);
                direcao = 2;
            }
            movendo = true;
        }

        // Atualiza a animação do jogador se ele estiver se movendo
        if (movendo) {
            update();
            movendo = false;
        }
    }

    Font f = new Font("arial", Font.BOLD, 30); // Fonte para o texto da energia

    // Desenha a energia do jogador na tela
    public void energia(Window janela) {
        janela.drawText("Vida: " + this.energia, 30, 30, Color.GREEN, f);
    }
}
