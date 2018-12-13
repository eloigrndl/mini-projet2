package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Gelly extends AreaEntity {

    //(Gelly) properties
    private boolean collected;
    Sprite gelly;

    /**
     * Gelly Constructor
     * @param area current area
     * @param orientation current orientation
     * @param position current position
     */
    public Gelly(Area area, Orientation orientation, DiscreteCoordinates position){
        super(area, orientation, position);
        super.setOrientation(Orientation.DOWN);
        gelly = new Sprite("gelly.red.2", 1, 1.f, this);
        collected = false;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        if (!collected) {
            gelly.draw(canvas);
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }

    /**
     * Setter of the 'collected' parameter
     * @param collected is the Gelly collected
     */
    protected void setCollected(boolean collected) {
        this.collected = collected;
    }

}
