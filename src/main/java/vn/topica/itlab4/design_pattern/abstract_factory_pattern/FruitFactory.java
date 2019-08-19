package vn.topica.itlab4.design_pattern.abstract_factory_pattern;

public class FruitFactory extends TreeFactory {
    @Override
    public Tree harvest(String name) {
        if (name.equals(Tree.Type.apple.name())) {
            return (new AppleTree()).grow();
        } else if (name.equals(Tree.Type.peach.name())) {
            return (new PeachTree()).grow();
        }
        return null;
    }
}
