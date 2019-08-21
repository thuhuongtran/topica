package vn.topica.itlab4.exercises.trung_trainer.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Store all kind of lamps: ON & OFF
 */
public class Store {

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
        return lamps.add(lamp);
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

    protected boolean removeAll(Trash trash) {
        if (trash.size() == 0) return false;
        for (int i = 0; i < trash.size(); i++) {
            Lamp lamp = trash.get(i);
            if (lamps.remove(lamp)) {
                System.out.println(String.format("THREAD_2 trash: Remove lamp from STORE: %s", lamp.toString()));
            }
        }
        return true;
    }
}
