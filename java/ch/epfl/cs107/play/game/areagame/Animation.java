package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.math.Positionable;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;

public interface Animation extends Positionable {


    Vector anchor = new Vector(0f, 0f);
    default Sprite animPerso(Orientation orientationPlayer, int frame, Sprite spritePerso){

        if (orientationPlayer == Orientation.UP) {
            Sprite[] spritesUp = new Sprite[4];
            for (int i = 0; i < spritesUp.length; ++i) {
                spritesUp[i] = new Sprite("max.ghost", 1f, 1f, this, new RegionOfInterest(32, i * 21, 16, 21), anchor);
            }
            spritePerso = spritesUp[frame];
        }
        if (orientationPlayer == Orientation.DOWN) {
            Sprite[] spritesDown = new Sprite[4];
            for (int i = 0; i < spritesDown.length; ++i) {
                spritesDown[i] = new Sprite("max.ghost", 1f, 1f, this, new RegionOfInterest(0, i * 21, 16, 21), anchor);
            }
            spritePerso = spritesDown[frame];
        }

        if (orientationPlayer == Orientation.RIGHT) {
            Sprite[] spritesRight =  new Sprite[4];
            for (int i = 0; i < spritesRight.length; ++i) {
                spritesRight[i] = new Sprite("max.ghost", 1f, 1f, this, new RegionOfInterest(48, i * 21, 16, 21), anchor);
            }
            spritePerso = spritesRight[frame];
        }

        if (orientationPlayer == Orientation.LEFT) {
            Sprite[] spritesLeft = new Sprite[4];
            for (int i = 0; i < spritesLeft.length; ++i) {
                spritesLeft[i] = new Sprite("max.ghost", 1f, 1f, this, new RegionOfInterest(16, i * 21, 16, 21), anchor);
            }
            spritePerso = spritesLeft[frame];
        }

        return spritePerso;

    }

    default Sprite animTorch(Sprite spriteTorch){
        char sprite = spriteTorch.getName().charAt(31);
        boolean changeSprite = sprite == '1';
        if(changeSprite){
            spriteTorch = new Sprite("torch.ground.on.2", 1, 1.f, this);
            return spriteTorch;
        }

        spriteTorch = new Sprite("torch.ground.on.1", 1, 1.f, this);
        return spriteTorch;
    }

    default Sprite animHelpingPerso(String name, Sprite spritePerson, Orientation orientation){

        if(orientation == Orientation.DOWN){
            spritePerson = new Sprite(name, 1f,1f, this, new RegionOfInterest(0, 0, 16, 21));
        }
        if(orientation == Orientation.LEFT){
            spritePerson = new Sprite(name, 1f,1f, this, new RegionOfInterest(16, 0, 16, 21));
        }
        if(orientation == Orientation.UP){
            spritePerson = new Sprite(name, 1f,1f, this, new RegionOfInterest(32, 0, 16, 21));
        }
        if(orientation == Orientation.RIGHT){
            spritePerson = new Sprite(name, 1f,1f, this, new RegionOfInterest(48, 0, 16, 21));
        }


        return spritePerson;
    }
}
