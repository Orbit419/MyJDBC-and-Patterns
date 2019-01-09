package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;
import mate.academy.myJdbc.model.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DeveloperDaoImpl extends AbstractDao implements DeveloperDao {
    public DeveloperDaoImpl(Connection connection) {
        super(connection);
    }

    public Set<Developer> getAllJavaDevs() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT developers.developer_id, developers.developer_name, developers.developer_age, developers.developer_salary, skills.skill_branch\n" +
                    "FROM developers\n" +
                    "JOIN developers_skills ON (developers.developer_id = developers_skills.developer_id)\n" +
                    "JOIN skills ON (skills.skill_id = developers_skills.skill_id)\n" +
                    "WHERE skills.skill_branch = 'Java';");
            ResultSet rs = statement.executeQuery();
            Set<Developer> set = new HashSet<>();

            while (rs.next()) {
                Developer dev = getDeveloper(rs);
                set.add(dev);
            }
            return set;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Developer> getAllMiddleDevs() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT developers.developer_id, developers.developer_name, developers.developer_age, developers.developer_salary, skills.skill_level\n" +
                    "FROM developers\n" +
                    "JOIN developers_skills ON (developers.developer_id = developers_skills.developer_id)\n" +
                    "JOIN skills ON (skills.skill_id = developers_skills.skill_id)\n" +
                    "WHERE skills.skill_level = 'Middle';");
            ResultSet rs = statement.executeQuery();
            Set<Developer> set = new HashSet<>();

            while (rs.next()) {
                Developer dev = getDeveloper(rs);
                set.add(dev);
            }
            return set;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int addNewDeveloper(Developer developer) {
        try {
            //insert dev
            PreparedStatement statement = connection.prepareStatement("INSERT INTO developers(developer_name, developer_age, developer_salary) VALUES(?, ?, ?)");
            statement.setString(1, developer.getName());
            statement.setInt(2, developer.getAge());
            statement.setInt(3, developer.getSalary());
            statement.execute();

            //get id of developer
            statement = connection.prepareStatement("SELECT MAX(developer_id) FROM developers;");
            ResultSet rs = statement.executeQuery();
            rs.next();
            int lastDevId = rs.getInt("MAX(developer_id)");

            return lastDevId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Developer findDeveloper(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT developer_id, developer_name, developer_age" +
                    ", developer_salary FROM developers WHERE developer_id=?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            Developer developer = getDeveloper(rs);

            //add skills to developer
            statement = connection.prepareStatement("SELECT skills.skill_id, skills.skill_branch, skills.skill_level, developers.developer_id\n" +
                    "FROM skills\n" +
                    "JOIN developers_skills ON (skills.skill_id = developers_skills.skill_id)\n" +
                    "JOIN developers ON (developers.developer_id = developers_skills.developer_id)\n" +
                    "HAVING developer_id = ?;");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill(rs.getInt("skill_id"),
                        rs.getString("skill_branch"),
                        rs.getString("skill_level"));
                developer.addSkill(skill);
            }

            //add projects to developer
            statement = connection.prepareStatement("SELECT projects.project_id, projects.project_name, projects.project_cost, projects.project_birthday, developers.developer_id\n" +
                    "FROM projects\n" +
                    "JOIN projects_developers ON (projects.project_id = projects_developers.project_id)\n" +
                    "JOIN developers ON (developers.developer_id = projects_developers.developer_id)\n" +
                    "HAVING developer_id = ?;");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                Project project = new Project.ProjectBuilder().setId(rs.getInt("project_id")).setBirthday(rs.getDate("project_birthday")).setName(rs.getString("project_name")).build();
                developer.addProject(project);
            }

            return developer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateDeveloper(Developer developer) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE developers SET developer_name=?" +
                    ", developer_age=?, developer_salary=? WHERE developer_id=?;");
            statement.setString(1, developer.getName());
            statement.setInt(2, developer.getAge());
            statement.setInt(3, developer.getSalary());
            statement.setInt(4, developer.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDeveloper(int id) {
        try {
            deleteDeveloperSkillRelations(id);
            deleteDeveloperProjectRelations(id);

            PreparedStatement statement = connection.prepareStatement("DELETE FROM developers WHERE developer_id=?;");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getSkillId(Skill skill) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT skill_id FROM skills WHERE skill_branch=? " +
                    "AND skill_level=?;");
            statement.setString(1, skill.getBranch());
            statement.setString(2, skill.getLevel());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int skillId = rs.getInt("skill_id");

            return skillId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void insertDevelopersSkillsRelation(int devId, int skillId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO developers_skills VALUES (?, ?);");
            statement.setInt(1, devId);
            statement.setInt(2, skillId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getProjectId(Project project) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT project_id FROM projects WHERE project_name=?;");
            statement.setString(1, project.getName());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int projectId = rs.getInt("project_id");
            return projectId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void insertDevelopersProjectsRelation(int projectId, int devId) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO projects_developers VALUES (?, ?)");
            statement.setInt(1, projectId);
            statement.setInt(2, devId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDeveloperSkillRelations(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM developers_skills WHERE developer_id=?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDeveloperProjectRelations(int id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM projects_developers WHERE developer_id=?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Developer getDeveloper(ResultSet rs) throws SQLException {
        Developer developer = new Developer(rs.getInt("developer_id"),
                rs.getString("developer_name"),
                rs.getInt("developer_age"),
                rs.getInt("developer_salary"));
        return developer;
    }
}
