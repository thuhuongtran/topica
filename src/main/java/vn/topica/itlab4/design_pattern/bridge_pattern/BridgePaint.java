package vn.topica.itlab4.design_pattern.bridge_pattern;

public class BridgePaint {
    private static final BridgePaint instance = new BridgePaint();

    private BridgePaint(){}

    public static BridgePaint getInstance(){
        return instance;
    }
    public void drawAndPaint() {
        Shape redCircle = new CircleShape(new RedDraw(), 9);
        Shape greenCircle = new CircleShape(new RedDraw(), 11);
        redCircle.drawShape();
        greenCircle.drawShape();
    }
}
