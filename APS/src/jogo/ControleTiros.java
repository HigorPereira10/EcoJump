package jogo;

import java.util.LinkedList;
import jplay.Scene;
import jplay.Sound;

public class ControleTiros {
	
    // Lista de tiros ativos
	LinkedList<Tiro> tiros = new LinkedList<>();
	
    // Adiciona um tiro na cena
	public void adicionaTiro(double x, double y, int caminho, Scene cena) {
		Tiro tiro = new Tiro(x, y, caminho);
		tiros.addFirst(tiro);
        // Adiciona o tiro como sobreposição na cena
		cena.addOverlay(tiro);
		somDisparo();
	}
	
    // Atualiza o estado dos tiros e verifica colisões com inimigos
	public void run(Ator inimigo) {
		for (int i = 0; i < tiros.size(); i++) {
			Tiro tiro = tiros.removeFirst();
			tiro.mover();
			tiros.addLast(tiro);
		
			if(tiro.collided(inimigo)) {
				tiro.x = 10_000;
				inimigo.energia -= 250;
			}
		}
	}
	
    // Toca o som de disparo
	private void somDisparo() {
		new Sound("src//audio//tiro.wav").play();
	}
}
