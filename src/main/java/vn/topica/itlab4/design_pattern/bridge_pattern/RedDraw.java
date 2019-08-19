package vn.topica.itlab4.design_pattern.bridge_pattern;

public class RedDraw implements Draw {
    @Override
    public void draw() {
        System.out.println("Drawing red circle");
    }
}
