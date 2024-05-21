package jogo;

import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;

public class Main {

    public static void main(String[] args) {
        Window janela = new Window(800, 600); // Cria uma janela do jogo
        GameImage plano = new GameImage("src//imagens//inicio.png"); // Imagem de fundo
        Keyboard teclado = janela.getKeyboard(); // Teclado para controle

        // Loop principal do jogo
        while (true) {
            plano.draw(); // Desenha o plano de fundo
            janela.update(); // Atualiza a janela

            // Inicia o cenário 1 se a tecla ENTER for pressionada
            if (teclado.keyDown(Keyboard.ENTER_KEY)) {
                new Cenario1(janela);
            }
        }
    }
}
