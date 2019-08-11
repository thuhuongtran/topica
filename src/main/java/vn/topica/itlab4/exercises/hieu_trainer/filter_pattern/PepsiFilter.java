package vn.topica.itlab4.exercises.hieu_trainer.filter_pattern;

import java.util.ArrayList;
import java.util.List;

public class PepsiFilter implements Filter {

    public List<Drink> meetFilter(List<Drink> drinks) {
        List<Drink> rtDrinks = new ArrayList<Drink>();
        for (Drink d : drinks) {
            if (Drink.Type.PEPSI.name().equals(d.getType().name())) {
                rtDrinks.add(d);
            }
        }
        return rtDrinks;
    }
}
