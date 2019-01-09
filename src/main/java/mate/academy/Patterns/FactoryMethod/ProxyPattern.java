package mate.academy.Patterns.FactoryMethod;

public class ProxyPattern implements Pattern, User {
    User user = new UserImpl();

    @Override
    public String getPatternName() {
        return "I am Proxy pattern!";
    }

    public void foo() {
        System.out.println("foo() proxy start!");
        user.foo();
    }

    public static ProxyPattern getInstance() {
        return new ProxyPattern();
    }
}

interface User {
    void foo();
}

class UserImpl implements User {
    public void foo() {
        System.out.println("foo() finished!");
    }
}
