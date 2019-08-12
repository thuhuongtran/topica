package vn.topica.itlab4.exercises.hieu_trainer.filter_pattern;

import java.util.ArrayList;
import java.util.List;

public class FilterPatternMain {
    public static void main(String[] args) {
        List<Drink> drinks = new ArrayList<Drink>();
        drinks.add(new Drink(45, "coca_1", Drink.Type.COCA));
        drinks.add(new Drink(42, "coca_2", Drink.Type.COCA));
        drinks.add(new Drink(43, "coca_3", Drink.Type.COCA));
        drinks.add(new Drink(44, "coca_4", Drink.Type.COCA));
        drinks.add(new Drink(46, "coca_5", Drink.Type.COCA));
        drinks.add(new Drink(47, "coca_6", Drink.Type.COCA));

        drinks.add(new Drink(12, "PEPSI_1", Drink.Type.PEPSI));
        drinks.add(new Drink(8, "PEPSI_2", Drink.Type.PEPSI));
        drinks.add(new Drink(7, "PEPSI_3", Drink.Type.PEPSI));
        drinks.add(new Drink(6, "PEPSI_4", Drink.Type.PEPSI));

        drinks.add(new Drink(23, "bohuc_1", Drink.Type.BOHUC));
        drinks.add(new Drink(21, "bohuc_2", Drink.Type.BOHUC));
        drinks.add(new Drink(22, "bohuc_3", Drink.Type.BOHUC));
        drinks.add(new Drink(15, "bohuc_4", Drink.Type.BOHUC));
        drinks.add(new Drink(14, "bohuc_5", Drink.Type.BOHUC));

        Filter filter;
        // coca filter_pattern
        filter = new CocaFilter();
        System.out.println("coca------------");
        for (Drink d : filter.meetFilter(drinks)) {
            System.out.println(d.getName()+" "+d.getType().name()+ " "+d.getPrice());
        }

        // pepsi filter_pattern
        filter = new PepsiFilter();
        System.out.println("pepsi--------------------");
        for (Drink d : filter.meetFilter(drinks)) {
            System.out.println(d.getName()+" "+d.getType().name()+ " "+d.getPrice());
        }

        // bohuc and coca filter_pattern
        filter = new AndFilter(new BohucFilter(), new CocaFilter());
        System.out.println("bohuc and coca------------");
        for (Drink d : filter.meetFilter(drinks)) {
            System.out.println(d.getName()+" "+d.getType().name()+ " "+d.getPrice());
        }
    }
}
