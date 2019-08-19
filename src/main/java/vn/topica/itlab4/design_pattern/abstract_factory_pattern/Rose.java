package vn.topica.itlab4.design_pattern.abstract_factory_pattern;

public class Rose implements Tree {
    @Override
    public Tree grow() {
        System.out.println("rose grow");
        return new Rose();
    }
}
