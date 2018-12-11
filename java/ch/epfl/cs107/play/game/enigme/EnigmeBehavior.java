package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior {

    public enum EnigmeCellType {
        NULL(0),
        WALL(-16777216),
        DOOR(-65536),
        WATER(-16776961),
        INDOOR_WALKABLE(-1),
        OUTDOOR_WALKABLE(-14112955);

        final int type;

        EnigmeCellType (int type){
            this.type = type;
        }

        static EnigmeBehavior.EnigmeCellType toType(int type){
            switch (type) {
                case 0 :  return EnigmeBehavior.EnigmeCellType.NULL;
                case -1 : return EnigmeBehavior.EnigmeCellType.INDOOR_WALKABLE;
                case -65536 : return EnigmeBehavior.EnigmeCellType.DOOR;
                case -14112955 : return EnigmeBehavior.EnigmeCellType.OUTDOOR_WALKABLE;
                case -16776961 : return EnigmeBehavior.EnigmeCellType.WATER;
                case -16777216 : return EnigmeBehavior.EnigmeCellType.WALL;
                default: return EnigmeBehavior.EnigmeCellType.NULL;
            }
        }
    }

    public EnigmeBehavior(Window window, String fileName){

        super(window, fileName);

        for (int i = 0; i<getBehaviorMapSize()[0]; ++i){
            for(int j = 0; j<getBehaviorMapSize()[1]; ++j){
                EnigmeBehavior.EnigmeCellType cellType = EnigmeBehavior.EnigmeCellType.toType(getBehaviorMap().getRGB(getBehaviorMapSize()[1]-1-j,i));
                EnigmeBehavior.EnigmeCell cell = new EnigmeBehavior.EnigmeCell(i,j,cellType);
                setCell(i,j,cell);
            }
        }
    }

    public class EnigmeCell extends Cell {

        private EnigmeBehavior.EnigmeCellType value;

        private EnigmeCell(int x, int y, EnigmeBehavior.EnigmeCellType type){
            super(x,y);
            value = type;
        }

        protected EnigmeBehavior.EnigmeCellType getValue() {
            return value;
        }

        @Override
        public boolean isViewInteractable() {
            return false;
        }

        @Override
        public boolean isCellInteractable() {
            return true;
        }

        @Override
        public boolean takeCellSpace() {
            return false;
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }

        @Override
        protected boolean canEnter(Interactable entity) {

            if (value.equals(EnigmeBehavior.EnigmeCellType.NULL) || value.equals(EnigmeBehavior.EnigmeCellType.WALL)) {
                return false;
            }
            for(Interactable i : getInteractableSet()) {
                if (i.takeCellSpace()) {
                    return false;
                }
            }

            return true;

        }

        @Override
        protected boolean canPassDoor(Interactable entity) {
            if (value.equals(EnigmeBehavior.EnigmeCellType.DOOR)) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {
            ((EnigmeInteractionVisitor) v).interactWith(this);
        }
    }

    @Override
    public void cellInteractionOf(Interactor interactor) {
        super.cellInteractionOf(interactor);
    }

    @Override
    public void viewInteractionOf(Interactor interactor) {
        super.viewInteractionOf(interactor);
    }
}
