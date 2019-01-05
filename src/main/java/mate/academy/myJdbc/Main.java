package mate.academy.myJdbc;

import mate.academy.myJdbc.dao.MyUserDao;
import mate.academy.myJdbc.dao.MyUserDaoImpl;
import mate.academy.myJdbc.model.Developer;
import mate.academy.myJdbc.model.Project;
import mate.academy.myJdbc.service.MyUserService;
import mate.academy.myJdbc.service.MyUserServiceImpl;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        MyUserDao myUserDao = new MyUserDaoImpl(MyConnectionUtil.getConnection());
        MyUserService myUserService = new MyUserServiceImpl(myUserDao);

        myUserService.printSumSalaryForProject("Astra");

        System.out.println("All devs on project Astra:");
        Set<Developer> devsOnProject = myUserService.getAllDevsOnProject("Astra");
        System.out.println(devsOnProject);

        System.out.println("All Java devs:");
        Set<Developer> javaDevs = myUserService.getAllJavaDevs();
        System.out.println(javaDevs);

        System.out.println("All middle devs:");
        Set<Developer> middleDevs = myUserService.getAllMiddleDevs();
        System.out.println(middleDevs);

        System.out.println("All projects:");
        Set<Project> allProjects = myUserService.getAllProjects();
        System.out.println(allProjects);
    }
}
