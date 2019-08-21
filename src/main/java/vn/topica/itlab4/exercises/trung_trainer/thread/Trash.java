package vn.topica.itlab4.exercises.trung_trainer.thread;

import java.util.*;

/**
 * Store lamps which their statuses are OFF
 */
public class Trash {

    private final List<Lamp> lamps = Collections.synchronizedList(new ArrayList<>());

    protected Lamp get(int index) {
        return lamps.get(index);
    }

    protected Lamp set(int index, Lamp newLamp) {
        Lamp oldLamp = lamps.get(index);
        lamps.set(index, newLamp);
        return oldLamp;
    }

    protected int size() {
        return lamps.size();
    }

    protected boolean add(Lamp lamp) {
        if (lamp.getStatus().equals(Lamp.Status.OFF.name())) {
            lamps.add(lamp);
            return true;
        }
        return false;
    }

    protected boolean addRepairAndOff(Lamp lamp) {
        if (lamp.getStatus().equals(Lamp.Status.OFF.name())
                || lamp.getStatus().equals(Lamp.Status.REPAIR.name())) {
            lamps.add(lamp);
            return true;
        }
        return false;
    }

    protected boolean remove(Lamp lamp) {
        if (lamp != null || lamps.contains(lamp)) {
            lamps.remove(lamp);
            return true;
        }
        return false;
    }

    protected boolean remove(int index) {
        if (lamps.get(index) != null) {
            lamps.remove(index);
            return true;
        }
        return false;
    }

    protected List<Lamp> getLamps() {
        return lamps;
    }
}
