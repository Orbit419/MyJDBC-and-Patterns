package mate.academy.myJdbc.service;

import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;

import java.util.Set;

public interface MyUserService {
    void printSumSalaryForProject(String project);

    Set<Developer> getAllDevsOnProject(String project);

    Set<Developer> getAllJavaDevs();

    Set<Developer> getAllMiddleDevs();

    Set<Project> getAllProjects();
}
