package vn.topica.itlab4.design_pattern.factory_pattern;

public class Bicycle implements Vehicle{
    @Override
    public String run() {
        return ("Bicycle runs at the speed of 60 miles");
    }
}
