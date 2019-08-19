package vn.topica.itlab4.design_pattern.bridge_pattern;

public class CircleShape extends Shape {
    private int r;

    public CircleShape(Draw draw) {
        super(draw);
    }

    public CircleShape(Draw draw, int r) {
        super(draw);
        this.r = r;
    }

    @Override
    public void drawShape() {
        System.out.println("Drawing circle. Radix="+r);
        draw.draw();
    }
}
