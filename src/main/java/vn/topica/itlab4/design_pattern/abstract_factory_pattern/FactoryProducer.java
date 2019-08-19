package vn.topica.itlab4.design_pattern.abstract_factory_pattern;

public class FactoryProducer {
    public static TreeFactory growTree(boolean isFruit) {
        if (isFruit) {
            return (new FruitFactory());
        }
        else{
            return (new FlowerFactory());
        }
    }
}
