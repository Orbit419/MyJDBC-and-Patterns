package mate.academy.myJdbc.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Project {
    private int id;
    private Date birthday;
    private String name;
    private int cost;
    private Set<Developer> developers = new HashSet<>();
    private int countDevs;

    public Project(int id, Date birthday, String name, int cost,  int countDevs) {
        this.id = id;
        this.birthday = birthday;
        this.name = name;
        this.cost = cost;
        this.countDevs = countDevs;
    }

    public static class ProjectBuilder {
        private int id;
        private Date birthday;
        private String name;
        private int cost;
        private int countDevs;

        public ProjectBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ProjectBuilder setBirthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public ProjectBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProjectBuilder setCost(int cost) {
            this.cost = cost;
            return this;
        }

        public ProjectBuilder setCountDevs(int countDevs) {
            this.countDevs = countDevs;
            return this;
        }

        public Project build() {
            return new Project(id, birthday, name, cost, countDevs);
        }
    }

    public int getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }

    public Set<Developer> getDevelopers(){
        return developers;
    }

    public void addDeveloper(Developer developer) {
        developers.add(developer);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountDevs() {
        return countDevs;
    }

    public void setCountDevs(int countDevs) {
        this.countDevs = countDevs;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", birthday=" + birthday +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", developers=" + developers +
                '}';
    }
}
