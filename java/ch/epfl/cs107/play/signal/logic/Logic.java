package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal {

    /**
     * dépend du type de signal implémenté
     * @return boolean
     */
   boolean isOn();

    /**
     * retourne 1.0f si isOn() / 0.0f si !isOn()
     * @return float
     */
    float getIntensity();

    /**
     * Overridden method
     * @param t (float): the time at which we want the intensity
     * @return getIntensity()
     */
    @Override
    default float getIntensity(float t){
        return getIntensity();
    }

    Logic TRUE = new Logic() {
        @Override
        public boolean isOn() {
            return true;
        }

        @Override
        public float getIntensity() {
            return 1;
        }
    };

    Logic FALSE = new Logic() {
        @Override
        public boolean isOn() {
            return false;
        }

        @Override
        public float getIntensity() {
            return 0;
        }
    };
}
