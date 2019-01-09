package mate.academy.myJdbc.model;

import java.util.HashSet;
import java.util.Set;

public class Developer {
    private int id;
    private String name;
    private int age;
    private int salary;
    Set<Skill> skills = new HashSet<>();
    Set<Project> projects = new HashSet<>();

    public Developer(int id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Developer(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Developer() {
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", skills=" + skills +
                ", projects=" + projects +
                '}';
    }
}
