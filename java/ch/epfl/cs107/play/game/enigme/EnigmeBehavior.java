package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior {

    public enum EnigmeCellType {
        NULL(0),
        WALL(-16777216),
        DOOR(-65536),
        WATER(-16776961),
        INDOOR_WALKABLE(-1),
        OUTDOOR_WALKABLE(-14112955),
        YELLOW(-256);

        final int type;

        /**
         * EnigmeCellType Constructor
         * @param type type of the Cell
         */
        EnigmeCellType (int type){
            this.type = type;
        }

        /**
         * Enum function, switching an Int to a CellType
         * @param type int of type of the cell
         * @return (EnigmeCellType) type of the cell
         */
        static EnigmeBehavior.EnigmeCellType toType(int type){
            switch (type) {
                case -1 : return EnigmeBehavior.EnigmeCellType.INDOOR_WALKABLE;
                case -65536 : return EnigmeBehavior.EnigmeCellType.DOOR;
                case -14112955 : return EnigmeBehavior.EnigmeCellType.OUTDOOR_WALKABLE;
                case -16776961 : return EnigmeBehavior.EnigmeCellType.WATER;
                case -16777216 : return EnigmeBehavior.EnigmeCellType.WALL;
                case -256 : return EnigmeCellType.YELLOW;
                default: return EnigmeBehavior.EnigmeCellType.NULL;
            }
        }
    }

    /**
     * EnigmeBehavior Constructor
     * @param window current window
     * @param fileName current filename
     */
    public EnigmeBehavior(Window window, String fileName){
        super(window, fileName);
        for (int i = 0; i< getBehaviorMap().getWidth(); ++i){
            for(int j = 0; j<getBehaviorMap().getHeight(); ++j){
                EnigmeBehavior.EnigmeCellType cellType = EnigmeBehavior.EnigmeCellType.toType(getBehaviorMap().getRGB(getBehaviorMapSize()[1]-1-j,i));
                EnigmeBehavior.EnigmeCell cell = new EnigmeBehavior.EnigmeCell(i,j,cellType);
                setCell(i,j,cell);
            }
        }
    }

    public class EnigmeCell extends Cell {

        private final EnigmeBehavior.EnigmeCellType value;

        /**
         * EnigmeCell Constructor
         * @param x coordinate
         * @param y coordinate
         * @param type CellType
         */
        private EnigmeCell(int x, int y, EnigmeBehavior.EnigmeCellType type){
            super(x,y);
            value = type;
        }

        /**
         * Returns the value of the current cell type
         * @return (EnigmeCellType)
         */
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
            if (value.equals(EnigmeBehavior.EnigmeCellType.NULL) || value.equals(EnigmeBehavior.EnigmeCellType.WALL)
                    || value.equals(EnigmeCellType.WATER)) {
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
            return value.equals(EnigmeCellType.DOOR);
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
