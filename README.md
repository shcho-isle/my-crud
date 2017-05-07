#### CRUD application

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/519be71d86c8494fb7043780270026c0)](https://www.codacy.com/app/pavlo-plynko/MyCRUD?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=shcho-isle/MyCRUD&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://dependencyci.com/github/shcho-isle/my-crud/badge)](https://dependencyci.com/github/shcho-isle/my-crud)
[![Build Status](https://travis-ci.org/shcho-isle/my-crud.svg?branch=master)](https://travis-ci.org/shcho-isle/my-crud)

Application uses one table.

It displays list of all users (with pagination), can add, remove, edit, and search. 

#### Technology used:
- Maven
- Spring
- Hibernate
- Testing: Tomcat 9
- Frontend: Spring MVC
- Logging: SLF4J & LogBack

#### To run application you need:
- MySQL instance on `localhost:3306`
- Login and password should be `root`
- Then run `init_script.sql`, it will create db, table and fill it with data. 


