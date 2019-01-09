package mate.academy.Patterns.FactoryMethod;

public class BuilderPatternExample implements PatternExample {
    @Override
    public Pattern makePattern() {
        return BuilderPattern.getInstance();
    }
}
