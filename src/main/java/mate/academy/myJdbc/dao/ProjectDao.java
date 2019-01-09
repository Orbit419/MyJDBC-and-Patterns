package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;

import java.util.Set;

public interface ProjectDao {
    int getSumSalaryForProject(String project);

    Set<Developer> getAllDevsOnProject(String project);

    Set<Project> getAllProjects();

    void addProject(Project project);

    Project findProject(int id);

    void updateProject(Project project);

    void deleteProject(int id);
}
