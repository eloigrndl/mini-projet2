package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.window.Window;
import com.sun.istack.internal.localization.NullLocalizable;
import java.util.PrimitiveIterator;

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

            }
            return Demo2CellType.NULL;
        }
    }

    public Demo2Behavior(Window window, String fileName){
        super(window, fileName);
        for (int i = 0; i<getBehaviorMapSize()[0]; ++i){
            for(int j = 0; j<getBehaviorMapSize()[1]; ++j){
                Demo2CellType cellType = Demo2CellType.toType(getBehaviorMap().getRGB(getBehaviorMapSize()[1]-1-j,i));
            }
        }
    }

    public class Demo2Cell extends Cell{
        private Demo2Cell(int x, int y, Demo2CellType type){}
    }
}
