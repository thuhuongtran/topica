package vn.topica.itlab4.design_pattern.abstract_factory_pattern;

public class FlowerFactory extends TreeFactory {
    @Override
    public Tree harvest(String name) {
        if (name.equals(Tree.Type.rose.name())) {
            return (new Rose()).grow();
        } else if (name.equals(Tree.Type.peony.name())) {
            return (new Peony()).grow();
        }
        return null;
    }
}
