package vn.topica.itlab4.exercises.trung_trainer.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Store lamps which their statuses are OFF
 */
public class Trash {

    private final List<Lamp> lamps = Collections.synchronizedList(new ArrayList<>());

    protected Lamp get(int index) {
        return lamps.get(index);
    }

    protected int size() {
        return lamps.size();
    }

    protected boolean add(Lamp lamp) {
        return lamps.add(lamp);
    }

    protected boolean remove(Lamp lamp) {
        if (lamp != null || lamps.contains(lamp)) {
            return lamps.remove(lamp);
        }
        return false;
    }
}
