package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;

import java.util.Set;

public interface DeveloperDao {
    Set<Developer> getAllJavaDevs();

    Set<Developer> getAllMiddleDevs();

    void addNewDeveloper(Developer developer);

    Developer findDeveloper(int id);

    void updateDeveloper(Developer developer);

    void deleteDeveloper(int id);
}
