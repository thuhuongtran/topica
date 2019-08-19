package vn.topica.itlab4.design_pattern.factory_pattern;

public interface Vehicle {
    String run();

    enum Type{
        MOTOBIKE,
        BIKE,
        BYCYCLE
    }
}
