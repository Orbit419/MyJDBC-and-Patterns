package mate.academy.Patterns;

import mate.academy.Patterns.AbstractFactory.AbstractFactory;
import mate.academy.Patterns.FactoryMethod.BuilderPatternExample;
import mate.academy.Patterns.FactoryMethod.Pattern;
import mate.academy.Patterns.FactoryMethod.PatternExample;
import mate.academy.Patterns.FactoryMethod.ProxyPatternExample;
import mate.academy.Patterns.FactoryMethod.SingletonPatternExample;

public class Main {
    public static void main(String[] args) {
        PatternExample builderPatternExample = new BuilderPatternExample();
        PatternExample proxyPatternExample = new ProxyPatternExample();
        PatternExample singletonPatternExample = new SingletonPatternExample();

        Pattern builder = builderPatternExample.makePattern();
        Pattern proxy = proxyPatternExample.makePattern();
        Pattern singleton = singletonPatternExample.makePattern();

        System.out.println(builder.getPatternName());
        System.out.println(proxy.getPatternName());
        System.out.println(singleton.getPatternName());

        Drawing drawer = new Adapter();
        System.out.println("\nAdapter!");
        drawer.drawLine();

        System.out.println("\n");
        new Flyweight().testFlyweight();

        System.out.println("\n");
        new Prototype().prototypeTest();

        System.out.println("\n");
        new AbstractFactory().testAbstractFabric();
    }
}
