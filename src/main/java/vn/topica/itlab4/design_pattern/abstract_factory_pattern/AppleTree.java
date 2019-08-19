package vn.topica.itlab4.design_pattern.abstract_factory_pattern;

public class AppleTree implements Tree {
    @Override
    public Tree grow() {
        System.out.println("apple grows");
        return new AppleTree();
    }
}
