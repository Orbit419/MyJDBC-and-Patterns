package mate.academy.Patterns;

public class Prototype {
    public void prototypeTest() {
        User user = new User("Ruslan", 21);
        User userCopy = (User)user.copy();

        System.out.println(user);
        System.out.println(userCopy);
    }
}

interface Copyable {
    Object copy();
}

class User implements Copyable {
    private String name;
    private int age;

    @Override
    public Object copy() {
        return new User(name, age);
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
