package jogo;

public class Inimigo1 extends Ator {

    private static final double VELOCIDADE_INIMIGOS = 0.16; // Velocidade dos inimigos
    private double ataque = 1; // Dano de ataque do inimigo

    public Inimigo1(int x, int y) {
        super("src//imagens//inimigo1.png", 8);
        this.x = x;
        this.y = y;
        this.setTotalDuration(2000); // Duração total da animação
        this.velocidade = VELOCIDADE_INIMIGOS;
        this.setSequence(4, 8); // Define a sequência de frames da animação
    }

    // Move o inimigo para os lados
    public void mover() {
        if (direcao != 2) {
            setSequence(4, 8);
            direcao = 2;
        }
        moveX(-velocidade);
        update();
    }

    // Remove o inimigo da tela quando sua energia é zero
    public void morrer() {
        if (this.energia <= 0) {
            this.velocidade = 0;
            this.direcao = 0;
            this.x = 1_000_000;
        }
    }

    // Faz o inimigo perseguir o jogador
    public void perseguir(double jogadorX, double jogadorY) {
        double dx = jogadorX - this.x;
        double dy = jogadorY - this.y;
        double angle = Math.atan2(dy, dx);

        double vx = Math.cos(angle) * velocidade;
        double vy = Math.sin(angle) * velocidade;

        this.x += vx;
        this.y += vy;

        // Atualiza a direção do inimigo com base na direção do movimento
        if (vx > 0 && direcao != 2) {
            setSequence(4, 8);
            direcao = 2;
        } else if (vx < 0 && direcao != 1) {
            setSequence(0, 4);
            direcao = 1;
        }

        update();
    }

    // Faz o inimigo atacar o jogador
    public void atacar(Jogador jogador) {
        if (this.collided(jogador)) {
            jogador.energia -= this.ataque;
        }
    }
}
