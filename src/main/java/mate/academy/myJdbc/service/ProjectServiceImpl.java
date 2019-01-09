package mate.academy.myJdbc.service;

import mate.academy.myJdbc.dao.ProjectDao;
import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;

import java.util.Set;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectDao projectDao;

    public ProjectServiceImpl(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public int getSumSalaryForProject(String project) {
        return projectDao.getSumSalaryForProject(project);
    }

    public Set<Developer> getAllDevsOnProject(String project) {
        return projectDao.getAllDevsOnProject(project);
    }

    public Set<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    @Override
    public void addProject(Project project) {
        projectDao.addProject(project);
    }

    @Override
    public Project findProject(int id) {
        return projectDao.findProject(id);
    }

    @Override
    public void updateProject(Project project) {
        projectDao.updateProject(project);
    }

    @Override
    public void deleteProject(int id) {
        projectDao.deleteProject(id);
    }
}
