package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class MyUserDaoImpl extends ConnectionDao implements MyUserDao {
    public MyUserDaoImpl(Connection connection) {
        super(connection);
    }

    public void printSumSalaryForProject(String project) {
        String sql = "SELECT projects.project_id, projects.project_name, sum(developers.developer_salary) AS SumSalary\n" +
                "FROM projects\n" +
                "JOIN projects_developers ON (projects.project_id = projects_developers.project_id)\n" +
                "JOIN developers ON (developers.developer_id = projects_developers.developer_id)\n" +
                "GROUP BY projects.project_id\n" +
                "HAVING projects.project_name = \"" + project + "\"";
        int result = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) result = rs.getInt("SumSalary");

            System.out.println("Sum of developers salary for project \"" + project + "\" is: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Set<Developer> getAllDevsOnProject(String project) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT developers.developer_id, developers.developer_name, developers.developer_age, developers.developer_salary, projects.project_name\n" +
                    "FROM developers\n" +
                    "JOIN projects_developers ON (developers.developer_id = projects_developers.developer_id)\n" +
                    "JOIN projects ON (projects.project_id = projects_developers.project_id)\n" +
                    "WHERE projects.project_name = (SELECT project_name FROM projects WHERE project_name = ?);");
            statement.setString(1, project);
            ResultSet rs = statement.executeQuery();
            Set<Developer> devs = new HashSet<>();

            while (rs.next()) {
                Developer dev = new Developer(
                        rs.getInt("developer_id"),
                        rs.getString("developer_name"),
                        rs.getInt("developer_age"),
                        rs.getInt("developer_salary"));
                devs.add(dev);
            }
            return devs;
        } catch (SQLException e) {
            System.out.println("Something wrong!");
            e.printStackTrace();
        }
        return null;
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
                Developer dev = new Developer(
                        rs.getInt("developer_id"),
                        rs.getString("developer_name"),
                        rs.getInt("developer_age"),
                        rs.getInt("developer_salary"));
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
                Developer dev = new Developer(
                        rs.getInt("developer_id"),
                        rs.getString("developer_name"),
                        rs.getInt("developer_age"),
                        rs.getInt("developer_salary"));
                set.add(dev);
            }
            return set;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Project> getAllProjects() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT projects.project_birthday, projects.project_name, COUNT(developers.developer_id) AS Devs\n" +
                    "FROM projects\n" +
                    "JOIN projects_developers ON (projects.project_id = projects_developers.project_id)\n" +
                    "JOIN developers ON (developers.developer_id = projects_developers.developer_id)\n" +
                    "GROUP BY projects.project_name;");
            ResultSet rs = statement.executeQuery();
            Set<Project> set = new HashSet<>();

            while (rs.next()) {
                Project project = new Project(
                        rs.getDate("project_birthday"),
                        rs.getString("project_name"),
                        rs.getInt("Devs")
                );
                set.add(project);
            }
            return set;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
