package jogo;

import jplay.Scene;
import jplay.Window;

public class Vitoria {
	
    private Window janela; // Janela do jogo
    private Scene cena_vit; // Cena de vitória
    
    public Vitoria(Window window) {
        // Inicializa a cena de vitória
        janela = window;
        cena_vit = new Scene();
        cena_vit.loadFromFile("src//scn//vitoria.png");
        run(); // Inicia o loop da cena de vitória
    }
    
    private void run() {
        while (true) {
            cena_vit.draw(); // Desenha a cena de vitória
            janela.update(); // Atualiza a janela
        }
    }
}
