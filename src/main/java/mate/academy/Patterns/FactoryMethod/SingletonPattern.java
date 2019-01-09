package mate.academy.Patterns.FactoryMethod;

public class SingletonPattern implements Pattern {
    private static SingletonPattern instance = null;

    @Override
    public String getPatternName() {
        return "I am Singleton pattern!";
    }

    public static synchronized SingletonPattern getInstance() {
        if (instance == null)
            instance = new SingletonPattern();
        return instance;
    }
}
