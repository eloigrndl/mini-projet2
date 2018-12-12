package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;

public interface Animation extends Positionable {




    default Sprite animPerso(Orientation orientationPlayer, int frame, Sprite spritePerso){


        Vector anchor = new Vector(0f, 0.15f);

        Sprite[] spritesDown = new Sprite[4];
        for (int i = 0; i < spritesDown.length; ++i) {
            spritesDown[i] = new Sprite("max.new.1", 1f, 1f, this, new RegionOfInterest(0, i * 21, 16, 21), anchor);
        }

        Sprite[] spritesLeft = new Sprite[4];
        for (int i = 0; i < spritesLeft.length; ++i) {
            spritesLeft[i] = new Sprite("max.new.1", 1f, 1f, this, new RegionOfInterest(16, i * 21, 16, 21), anchor);
        }

        Sprite[] spritesUp = new Sprite[4];
        for (int i = 0; i < spritesUp.length; ++i) {
            spritesUp[i] = new Sprite("max.new.1", 1f, 1f, this, new RegionOfInterest(32, i * 21, 16, 21), anchor);
        }

        Sprite[] spritesRight = new Sprite[4];
        for (int i = 0; i < spritesRight.length; ++i) {
            spritesRight[i] = new Sprite("max.new.1", 1f, 1f, this, new RegionOfInterest(48, i * 21, 16, 21), anchor);
        }


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
