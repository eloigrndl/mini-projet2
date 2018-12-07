package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.window.Window;

import java.util.List;

public class Demo2Behavior extends AreaBehavior {

    public enum Demo2CellType {
        NULL(0),
        WALL(-16777216),
        DOOR(-65536),
        WATER(-16776961),
        INDOOR_WALKABLE(-1),
        OUTDOOR_WALKABLE(-14112955);

        final int type;

        Demo2CellType (int type){
            this.type = type;
        }

        static Demo2CellType toType(int type){
            switch (type) {
                case 0 :  return Demo2CellType.NULL;
                case -1 : return Demo2CellType.INDOOR_WALKABLE;
                case -65536 : return Demo2CellType.DOOR;
                case -14112955 : return Demo2CellType.OUTDOOR_WALKABLE;
                case -16776961 : return Demo2CellType.WATER;
                case -16777216 : return Demo2CellType.WALL;
                default: return Demo2CellType.NULL;
            }
        }
    }

    public Demo2Behavior(Window window, String fileName){
        super(window, fileName);

        for (int i = 0; i<getBehaviorMapSize()[0]; ++i){
            for(int j = 0; j<getBehaviorMapSize()[1]; ++j){
                Demo2CellType cellType = Demo2CellType.toType(getBehaviorMap().getRGB(getBehaviorMapSize()[1]-1-j,i));
                Demo2Cell cell = new Demo2Cell(i,j,cellType);
                setCell(i,j,cell);
            }
        }
    }

    public class Demo2Cell extends Cell {

        private Demo2CellType value;

        private Demo2Cell(int x, int y, Demo2CellType type){
            super(x,y);
            value = type;
        }

        protected Demo2CellType getValue() {
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
            if (value.equals(Demo2CellType.NULL) || value.equals(Demo2CellType.WALL)) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        protected boolean canPassDoor(Interactable entity) {
            if (value.equals(Demo2CellType.DOOR)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
