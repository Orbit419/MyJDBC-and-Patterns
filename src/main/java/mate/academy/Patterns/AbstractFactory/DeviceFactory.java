package mate.academy.Patterns.AbstractFactory;

public interface DeviceFactory {
    Mouse getMouse();
    Keyboard getKeyboard();
    Laptop getLaptop();
}

class RazerDeviceFactory implements DeviceFactory {
    @Override
    public Mouse getMouse() {
        return new RazerMouse();
    }

    @Override
    public Keyboard getKeyboard() {
        return new RazerKeyboard();
    }

    @Override
    public Laptop getLaptop() {
        return new RazerLaptop();
    }
}

class GeniusDeviceFactory implements DeviceFactory {
    @Override
    public Mouse getMouse() {
        return new GeniusMouse();
    }

    @Override
    public Keyboard getKeyboard() {
        return new GeniusKeyboard();
    }

    @Override
    public Laptop getLaptop() {
        return new GeniusLaptop();
    }
}
