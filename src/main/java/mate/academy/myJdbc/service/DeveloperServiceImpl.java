package mate.academy.myJdbc.service;

import mate.academy.myJdbc.dao.DeveloperDao;
import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;

import java.util.Set;

public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperDao developerDao;

    public DeveloperServiceImpl(DeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    public Set<Developer> getAllJavaDevs() {
        return developerDao.getAllJavaDevs();
    }

    public Set<Developer> getAllMiddleDevs() {
        return developerDao.getAllMiddleDevs();
    }

    @Override
    public void addNewDeveloper(Developer developer) {
        developerDao.addNewDeveloper(developer);
    }

    @Override
    public Developer findDeveloper(int id) {
        return developerDao.findDeveloper(id);
    }

    @Override
    public void updateDeveloper(Developer developer) {
        developerDao.updateDeveloper(developer);
    }

    @Override
    public void deleteDeveloper(int id) {
        developerDao.deleteDeveloper(id);
    }
}
