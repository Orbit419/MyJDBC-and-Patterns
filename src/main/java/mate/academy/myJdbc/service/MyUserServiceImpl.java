package mate.academy.myJdbc.service;

import mate.academy.myJdbc.dao.MyUserDao;
import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;

import java.util.Set;

public class MyUserServiceImpl implements MyUserService {
    private final MyUserDao myUserDao;

    public MyUserServiceImpl(MyUserDao myUserDao) {
        this.myUserDao = myUserDao;
    }

    public void printSumSalaryForProject(String project) {
        myUserDao.printSumSalaryForProject(project);
    }

    public Set<Developer> getAllDevsOnProject(String project) {
        return myUserDao.getAllDevsOnProject(project);
    }

    public Set<Developer> getAllJavaDevs() {
        return myUserDao.getAllJavaDevs();
    }

    public Set<Developer> getAllMiddleDevs() {
        return myUserDao.getAllMiddleDevs();
    }

    public Set<Project> getAllProjects() {
        return myUserDao.getAllProjects();
    }
}
