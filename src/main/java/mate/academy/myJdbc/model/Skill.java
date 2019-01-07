package mate.academy.myJdbc.model;

public class Skill {
    private int id;
    private String branch;
    private String level;

    public Skill(int id, String branch, String level) {
        this.id = id;
        this.branch = branch;
        this.level = level;
    }

    public Skill(String branch, String level) {
        this.branch = branch;
        this.level = level;
    }

    public Skill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", branch='" + branch + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
