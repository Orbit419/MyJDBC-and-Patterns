package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class ProjectDaoImpl extends AbstractDao implements ProjectDao{
    public ProjectDaoImpl(Connection connection) {
        super(connection);
    }
    public int getSumSalaryForProject(String project) {
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

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
                Developer dev = getDeveloper(rs);
                devs.add(dev);
            }
            return devs;
        } catch (SQLException e) {
            System.out.println("Something wrong!");
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
                Project project = new Project.ProjectBuilder().setBirthday(rs.getDate("project_birthday")).setName(rs.getString("project_name")).setCountDevs(rs.getInt("Devs")).build();
                set.add(project);
            }
            return set;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addProject(Project project) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO projects(project_name, project_cost, project_birthday)" +
                    "VALUES (?, ?, ?)");
            statement.setString(1, project.getName());
            statement.setInt(2, project.getCost());
            statement.setDate(3, project.getBirthday());
            statement.execute();

            statement = connection.prepareStatement("SELECT MAX(project_id) FROM projects;");
            ResultSet rs = statement.executeQuery();
            rs.next();
            int projectId = rs.getInt("MAX(project_id)");

            for(Developer dev : project.getDevelopers()) {
                statement = connection.prepareStatement("INSERT INTO projects_developers VALUES (?, ?)");
                statement.setInt(1, projectId);
                statement.setInt(2, dev.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project findProject(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT project_id, project_name, project_cost," +
                    " project_birthday FROM projects WHERE project_id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            Project project = new Project.ProjectBuilder()
                    .setId(rs.getInt("project_id"))
                    .setName(rs.getString("project_name"))
                    .setCost(rs.getInt("project_cost"))
                    .setBirthday(rs.getDate("project_birthday"))
                    .build();

            statement = connection.prepareStatement("SELECT developers.developer_id, developers.developer_name, developers.developer_age, developers.developer_salary, projects.project_id\n" +
                    "FROM developers\n" +
                    "JOIN projects_developers ON (developers.developer_id = projects_developers.developer_id)\n" +
                    "JOIN projects ON (projects.project_id = projects_developers.project_id)\n" +
                    "HAVING project_id = ?;");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while(rs.next()) {
                Developer developer = getDeveloper(rs);
                project.addDeveloper(developer);
            }

            return project;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateProject(Project project) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE projects SET project_name = ?, project_cost = ?" +
                    ", project_birthday = ? WHERE project_id = ?;");
            statement.setString(1, project.getName());
            statement.setInt(2, project.getCost());
            statement.setDate(3, project.getBirthday());
            statement.setInt(4, project.getId());
            statement.execute();

            deleteProjectsDevelopersRelations(project.getId());

            for(Developer dev : project.getDevelopers()) {
                statement = connection.prepareStatement("INSERT INTO projects_developers VALUES (?, ?)");
                statement.setInt(1, project.getId());
                statement.setInt(2, dev.getId());
                statement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProject(int id) {
        try {
            deleteProjectsDevelopersRelations(id);

            PreparedStatement statement = connection.prepareStatement("DELETE FROM projects WHERE project_id = ?");
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

    private void deleteProjectsDevelopersRelations (int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM projects_developers WHERE project_id = ?;");
        statement.setInt(1, id);
        statement.execute();
    }
}
