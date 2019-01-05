package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;

import java.util.Set;

public interface MyUserDao {
    void printSumSalaryForProject(String project);

    Set<Developer> getAllDevsOnProject(String project);

    Set<Developer> getAllJavaDevs();

    Set<Developer> getAllMiddleDevs();

    Set<Project> getAllProjects();
}
