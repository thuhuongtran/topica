package vn.topica.itlab4.exercises.hieu_trainer.filter_pattern;

import java.util.ArrayList;
import java.util.List;

public class CocaFilter implements Filter {

    public List<Drink> meetFilter(List<Drink> drinks) {
        List<Drink> rtDrinks = new ArrayList<Drink>();
        for (Drink d : drinks) {
            if (Drink.Type.COCA.name().equals(d.getType().name())) {
                rtDrinks.add(d);
            }
        }
        return rtDrinks;
    }
}
