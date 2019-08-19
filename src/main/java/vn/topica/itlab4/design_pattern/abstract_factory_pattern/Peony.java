package vn.topica.itlab4.design_pattern.abstract_factory_pattern;

public class Peony implements Tree {
    @Override
    public Tree grow() {
        return new Peony();
    }
}
