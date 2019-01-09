package mate.academy.Patterns.AbstractFactory;

public class AbstractFactory {
    public static DeviceFactory getFactoryByMark(String mark) {
        switch(mark){
            case "Razer":
                return new RazerDeviceFactory();
            case "Genius":
                return new GeniusDeviceFactory();
            default:
                throw new RuntimeException("This mark doesn't exist!");
        }
    }

    public void testAbstractFabric() {
        DeviceFactory razerDeviceFactory = AbstractFactory.getFactoryByMark("Razer");
        razerDeviceFactory.getMouse().printMark();

        DeviceFactory geniusDeviceFactory = AbstractFactory.getFactoryByMark("Genius");
        geniusDeviceFactory.getMouse().printMark();
    }
}
