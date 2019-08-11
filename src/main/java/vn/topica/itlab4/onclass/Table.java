package vn.topica.itlab4.onclass;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private int cost;

    protected enum Type {
        CIRCLE,
        RECTANGLE,
        ECLIP;

        private String decorative;

        protected void doDecorative() {

        }
    }

    public static void main(String[] args) {
//        Integer first = 64;
//        Integer second = new Integer(64);
//        System.out.println(first == second);

    }

    @Deprecated
    public void testAnnotation() {
        System.out.println("deprecated annotation");
    }
}
