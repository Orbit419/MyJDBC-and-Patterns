package mate.academy.Patterns.FactoryMethod;

public class SingletonPatternExample implements PatternExample {
    @Override
    public Pattern makePattern() {
        return SingletonPattern.getInstance();
    }
}
