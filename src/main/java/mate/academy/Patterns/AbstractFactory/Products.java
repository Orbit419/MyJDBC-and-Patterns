package mate.academy.Patterns.AbstractFactory;

public interface Products {}

interface Mouse {
    void printMark();
}

interface Keyboard {
    void printMark();
}

interface Laptop {
    void printMark();
}

class RazerMouse implements Mouse{
    @Override
    public void printMark() {
        System.out.println("Razer mouse!");
    }
}

class RazerKeyboard implements Keyboard{
    @Override
    public void printMark() {
        System.out.println("Razer keyboard!");
    }
}

class RazerLaptop implements Laptop {
    @Override
    public void printMark() {
        System.out.println("Razer laptop!");
    }
}

class GeniusMouse implements Mouse {
    @Override
    public void printMark() {
        System.out.println("Genius mouse!");
    }
}

class GeniusKeyboard implements Keyboard {
    @Override
    public void printMark() {
        System.out.println("Genius keyboard!");
    }
}

class GeniusLaptop implements Laptop {
    @Override
    public void printMark() {
        System.out.println("Genius laptop!");
    }
}


