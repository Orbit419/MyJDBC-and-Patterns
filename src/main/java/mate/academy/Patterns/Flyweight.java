package mate.academy.Patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flyweight {
    public void testFlyweight() {
        List<Computer> computers = new ArrayList<>();
        ComputerFactory factory = new ComputerFactory();

        computers.add(factory.getComputer("Notepad"));
        computers.add(factory.getComputer("PC"));
        computers.add(factory.getComputer("Notepad"));
        computers.add(factory.getComputer("PC"));

        computers.stream()
                .forEach(Computer::printComputer);
        System.out.println(factory.computers);
    }
}

interface Computer {
    void printComputer();
}

class Notepad implements Computer {
    private String name = "Notepad";
    @Override
    public void printComputer() {
        System.out.println(name);
    }

    @Override
    public String toString() {
        return "Notepad{" +
                "name='" + name + '\'' +
                '}';
    }
}

class PC implements Computer {
    private String name = "PC";
    @Override
    public void printComputer() {
        System.out.println(name);
    }

    @Override
    public String toString() {
        return "PC{" +
                "name='" + name + '\'' +
                '}';
    }
}

class ComputerFactory {
    public Map<String, Computer> computers = new HashMap<>();
    public Computer getComputer(String computerName) {
        Computer computer = computers.get(computerName);

        if(computer == null) {
            switch(computerName) {
                case "Notepad":
                    computer = new Notepad();
                    break;
                case "PC":
                    computer = new PC();
                    break;
            }
            computers.put(computerName, computer);
        }
        return computer;
    }
}


