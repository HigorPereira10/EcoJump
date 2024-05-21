package jogo;

import jplay.Sprite;

public class Tiro extends Sprite {

    public static final int LEFT = 1, RIGHT = 2, STOP = 3;
    protected static final double VELOCIDADE_TIRO = 0.05; // Velocidade do tiro
    protected int caminho = STOP; // Direção inicial do tiro
    protected boolean movendo = false; // Indica se o tiro está se movendo
    protected int direcao = 3; // Direção do tiro

    public Tiro(double x, double y, int caminho) {
        super("src//imagens//tiro.png", 16);
        this.caminho = caminho;
        this.x = x;
        this.y = y;
    }

    // Move o tiro na direção definida
    public void mover() {
        if (caminho == LEFT) {
            this.x -= VELOCIDADE_TIRO;
            if (direcao != 1) {
                setSequence(4, 8);
            }
            movendo = true;
        }

        if (caminho == RIGHT) {
            this.x += VELOCIDADE_TIRO;
            if (direcao != 2) {
                setSequence(9, 12);
            }
            movendo = true;
        }

        // Atualiza a animação do tiro se ele estiver se movendo
        if (movendo) {
            update();
            movendo = false;
        }
    }
}
