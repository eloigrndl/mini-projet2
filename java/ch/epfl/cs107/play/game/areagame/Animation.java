package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;

public interface Animation extends Positionable {




    default Sprite animPerso(Orientation orientationPlayer, int frame, Sprite spritePerso, Sprite[] spritesUp,
                             Sprite[] spritesDown, Sprite[] spritesRight, Sprite[] spritesLeft){

        if (orientationPlayer == Orientation.UP) {
            spritePerso = spritesUp[frame];
        }
        if (orientationPlayer == Orientation.DOWN) {
            spritePerso = spritesDown[frame];
        }

        if (orientationPlayer == Orientation.RIGHT) {
            spritePerso = spritesRight[frame];
        }

        if (orientationPlayer == Orientation.LEFT) {
            spritePerso = spritesLeft[frame];
        }

        return spritePerso;

    }
}
