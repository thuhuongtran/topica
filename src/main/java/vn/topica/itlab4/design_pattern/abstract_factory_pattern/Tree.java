package vn.topica.itlab4.design_pattern.abstract_factory_pattern;

public interface Tree {
    enum Type{
        rose,
        apple,
        peach,
        peony
    }
    Tree grow();
}
