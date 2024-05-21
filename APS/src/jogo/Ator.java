package jogo;

import jplay.Sprite;

public class Ator extends Sprite {

    // Define a velocidade do ator
    double velocidade = 0.05;
    
    // Define a direção inicial do ator
    protected int direcao = 3;

    // Indica se o ator está se movendo
    boolean movendo = false;
    
    // Define a energia inicial do ator
    public int energia = 250;

    // Construtor que inicializa o sprite com o arquivo e número de frames
    public Ator(String fileName, int numFrames) {
        super(fileName, numFrames);
    }
}
