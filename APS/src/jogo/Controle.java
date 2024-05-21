package jogo;

import jplay.GameObject;
import jplay.TileInfo;

public class Controle {

    // Verifica se há uma colisão entre um objeto do jogo e um tile
    public boolean colisao(GameObject obj, TileInfo tile) {
        // Verifica se o identificador do tile é maior ou igual a 7 e se o objeto está colidindo com o tile
        if ((tile.id >= 7) && obj.collided(tile)) {
            return true; // Há colisão
        }
        return false; // Não há colisão
    }
}
