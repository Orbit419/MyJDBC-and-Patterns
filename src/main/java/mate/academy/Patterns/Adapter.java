package mate.academy.Patterns;

public class Adapter extends MyDrawer implements Drawing{
    @Override
    public void drawLine() {
        myDrawLine();
    }

    @Override
    public void drawCub() {
        myDrawCub();
    }
}

interface Drawing {
    void drawLine();
    void drawCub();
}

class MyDrawer {
    public void myDrawLine() {
        System.out.println("Line");
    }

    public void myDrawCub() {
        System.out.println("Cub");
    }
}
