package mate.academy.myJdbc.model;

import java.util.Date;

public class Project {
    private Date birthday;
    private String name;
    private int countDevs;

    public Project(Date birthday, String name, int countDevs) {
        this.birthday = birthday;
        this.name = name;
        this.countDevs = countDevs;
    }

    public Project() {
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
                "birthday=" + birthday +
                ", name='" + name + '\'' +
                ", countDevs=" + countDevs +
                '}';
    }
}
