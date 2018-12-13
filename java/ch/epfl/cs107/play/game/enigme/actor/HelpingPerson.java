package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Animation;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;

public class HelpingPerson extends AreaEntity implements Animation {

    String name;
    Sprite sprite;
    String whatToSay;

    public HelpingPerson(Area area, Orientation orientation, DiscreteCoordinates coordinates, String name){
        super(area, orientation, coordinates);
        this.name = name;
        sprite = animHelpingPerso(name, sprite, orientation);
        if (name.equals("old.man.1")){
            whatToSay = "Choose only one torch, choose wisely";
        }
        if(name.equals("old.man.2")){
            whatToSay = "I hope I will fish a lot of fish";
        }
        if(name.equals("old.man.3")){
            whatToSay = "First quest for a young man";
        }
        if(name.equals("girl.2")){
            whatToSay = "If I was you, I will try to go up";
        }
        if(name.equals("girl.3")){
            whatToSay = "Try to reproduce the figure";
        }
        if(name.equals("boy.2")){
            whatToSay = "Look, it's sunny today";
        }
        if(name.equals("boy.4")){
            whatToSay = "If you continue this way, you will return to the Level Selector";
        }
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        ArrayList<DiscreteCoordinates> currentCells = new ArrayList<>();
        currentCells.add(super.getCurrentMainCellCoordinates());
        return currentCells;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }


}
