package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;
import mate.academy.myJdbc.model.Skill;

import java.util.Set;

public interface DeveloperDao {
    Set<Developer> getAllJavaDevs();

    Set<Developer> getAllMiddleDevs();

    int addNewDeveloper(Developer developer);

    Developer findDeveloper(int id);

    void updateDeveloper(Developer developer);

    void deleteDeveloper(int id);

    int getSkillId(Skill skill);

    void insertDevelopersSkillsRelation(int devId, int skillId);

    int getProjectId(Project project);

    void insertDevelopersProjectsRelation(int projectId, int devId);

    void deleteDeveloperSkillRelations(int id);

    void deleteDeveloperProjectRelations(int id);
}
