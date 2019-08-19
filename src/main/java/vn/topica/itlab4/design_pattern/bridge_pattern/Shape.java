package vn.topica.itlab4.design_pattern.bridge_pattern;

public abstract class Shape {
     Draw draw;

    public Shape(Draw draw) {
        this.draw = draw;
    }

    public abstract void drawShape();
}
