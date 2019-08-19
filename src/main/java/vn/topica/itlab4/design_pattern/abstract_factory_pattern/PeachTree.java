package vn.topica.itlab4.design_pattern.abstract_factory_pattern;

public class PeachTree implements Tree {
    @Override
    public PeachTree grow() {
        return new PeachTree();
    }
}
