package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class SignalRock extends AreaEntity implements Logic {

    //(SignalRock) properties
    Sprite rock;
    Logic signal;

    /**
     * SignalRock Constructor
     * @param area the current area
     * @param orientation the current orientation
     * @param position the current position
     * @param signal the signal linked to the rock
     */
    public SignalRock(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal){

        super(area, orientation, position);
        super.setOrientation(Orientation.DOWN);

        this.rock = new Sprite("rock.3", 1, 1.f, this);
        this.signal = signal;
    }

    @Override
    public void draw(Canvas canvas) {
        if (!signal.isOn()) {
            rock.draw(canvas);
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return !signal.isOn();
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor) v).interactWith(this);
    }

    @Override
    public boolean isOn() {
        return signal.isOn();
    }

    @Override
    public float getIntensity() {
        if (signal.isOn()) {
            return 1;
        } else {
            return 0;
        }
    }
}
