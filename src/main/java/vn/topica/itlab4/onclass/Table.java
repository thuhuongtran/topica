package vn.topica.itlab4.onclass;

import java.util.ArrayList;
import java.util.List;

public abstract class Table {
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

    @Deprecated
    public void testAnnotation() {
        System.out.println("deprecated annotation");
    }

    protected abstract void draw();
    protected void table() {

    }
}
