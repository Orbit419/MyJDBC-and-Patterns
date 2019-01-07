CREATE SCHEMA `developing` DEFAULT CHARACTER SET utf8 ;

<--- create table "developers" --->
CREATE TABLE `developing`.`developers` (
  `developer_id` INT NOT NULL AUTO_INCREMENT,
  `developer_name` VARCHAR(255) NULL,
  `developer_age` INT NULL,
  `developer_salary` INT NULL,
  PRIMARY KEY (`developer_id`),
  UNIQUE INDEX `developer_id_UNIQUE` (`developer_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

<--- create table "skills" --->
CREATE TABLE `developing`.`skills` (
  `skill_id` INT NOT NULL AUTO_INCREMENT,
  `skill_branch` VARCHAR(45) NULL,
  `skill_level` VARCHAR(45) NULL,
  PRIMARY KEY (`skill_id`),
  UNIQUE INDEX `skill_id_UNIQUE` (`skill_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

<--- create table "projects" --->
CREATE TABLE `developing`.`projects` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `project_name` VARCHAR(255) NULL,
  `project_cost` INT NULL,
  `project_birthday` DATE NULL,
  PRIMARY KEY (`project_id`),
  UNIQUE INDEX `project_id_UNIQUE` (`project_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

<--- create table "companies" --->
CREATE TABLE `developing`.`companies` (
  `company_id` INT NOT NULL AUTO_INCREMENT,
  `company_name` VARCHAR(45) NULL,
  `company_country` VARCHAR(45) NULL,
  PRIMARY KEY (`company_id`),
  UNIQUE INDEX `company_id_UNIQUE` (`company_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

<--- create table "customers" --->
CREATE TABLE `developing`.`customers` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `customer_name` VARCHAR(255) NULL,
  `customer_country` VARCHAR(45) NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE INDEX `customer_id_UNIQUE` (`customer_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

<------------------------- ManyToMany ------------------------->


<--- create table "developer_projects"--->
CREATE TABLE `developing`.`developer_projects` (
  `developer_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`developer_id`, `project_id`),
  INDEX `project_id_idx` (`project_id` ASC) VISIBLE,
  CONSTRAINT `developer_id`
    FOREIGN KEY (`developer_id`)
    REFERENCES `developing`.`developers` (`developer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `project_id`
    FOREIGN KEY (`project_id`)
    REFERENCES `developing`.`projects` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

<--- create table "developers_skills" --->
CREATE TABLE `developing`.`developers_skills` (
  `developer_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  PRIMARY KEY (`developer_id`, `skill_id`),
  INDEX `developers.skills_on_skills_idx` (`skill_id` ASC) VISIBLE,
  CONSTRAINT `developers.skills_on_developers`
    FOREIGN KEY (`developer_id`)
    REFERENCES `developing`.`developers` (`developer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `developers.skills_on_skills`
    FOREIGN KEY (`skill_id`)
    REFERENCES `developing`.`skills` (`skill_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

<--- create table "projects_developers" --->
CREATE TABLE `developing`.`projects_developers` (
  `project_id` INT NOT NULL,
  `developer_id` INT NOT NULL,
  PRIMARY KEY (`project_id`, `developer_id`),
  INDEX `developer_id_idx` (`developer_id` ASC) VISIBLE,
    FOREIGN KEY (`project_id`)
    REFERENCES `developing`.`projects` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`developer_id`)
    REFERENCES `developing`.`developers` (`developer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

<--- create table "companies_projects" --->
CREATE TABLE `developing`.`companies_projects` (
  `company_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`company_id`, `project_id`),
  INDEX `project_id_idx` (`project_id` ASC) VISIBLE,
    FOREIGN KEY (`company_id`)
    REFERENCES `developing`.`companies` (`company_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`project_id`)
    REFERENCES `developing`.`projects` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

<--- create table "customers_projects" --->
CREATE TABLE `developing`.`customers_projects` (
  `customer_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`customer_id`, `project_id`),
  INDEX `project_id_idx` (`project_id` ASC) VISIBLE,
    FOREIGN KEY (`customer_id`)
    REFERENCES `developing`.`customers` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`project_id`)
    REFERENCES `developing`.`projects` (`project_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

<------------------------- TASK 2 ------------------------->

<--- the most expensive project based on developer's salary --->
SELECT projects.project_id, projects.project_name, sum(developers.developer_salary)
FROM projects
JOIN projects_developers ON (projects.project_id = projects_developers.project_id)
JOIN developers ON (developers.developer_id = projects_developers.developer_id)
GROUP BY projects.project_id
ORDER BY sum(developers.developer_salary) DESC
LIMIT 1

<--- total salary of Java developers --->
SELECT skills.skill_branch, SUM(developers.developer_salary) AS java_salary
FROM skills
JOIN developers_skills ON(skills.skill_id = developers_skills.skill_id)
JOIN developers ON (developers.developer_id = developers_skills.developer_id)
GROUP BY skills.skill_branch
HAVING skills.skill_branch = 'Java';

<--- the most inexpensive project based on 'cost' --->
SELECT projects.project_id, projects.project_name, projects.project_cost
FROM projects
WHERE projects.project_cost = (SELECT MIN(project_cost) FROM projects);

<--- average salary of most inexpensive project --->
SELECT projects.project_id, projects.project_name, projects.project_cost, avg(developers.developer_salary) AS AvgSalary
FROM projects
JOIN projects_developers ON (projects.project_id = projects_developers.project_id)
JOIN developers ON (developers.developer_id = projects_developers.developer_id)
GROUP BY projects.project_id
HAVING projects.project_cost = (SELECT MIN(project_cost) FROM projects);