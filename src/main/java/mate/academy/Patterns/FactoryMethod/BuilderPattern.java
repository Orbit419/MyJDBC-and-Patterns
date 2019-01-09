package mate.academy.Patterns.FactoryMethod;

public class BuilderPattern implements Pattern {
    @Override
    public String getPatternName() {
        return "I am Builder pattern!";
    }

    public static BuilderPattern getInstance() {
        return new BuilderPattern();
    }
}

class UserClass {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserClass{" +
                "name='" + name + '\'' +
                '}';
    }
}

class UserClassBuilder {
    private String name;

    public UserClassBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public UserClass build() {
        UserClass user = new UserClass();
        user.setName(name);
        return user;
    }
}
