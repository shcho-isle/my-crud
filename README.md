#### CRUD application

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3ba17f27c6b24685a86afd7e59add31e)](https://www.codacy.com/app/pavlo-plynko/my-crud?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=shcho-isle/my-crud&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/shcho-isle/my-crud.svg?branch=master)](https://travis-ci.org/shcho-isle/my-crud)

Application uses one table.

It displays list of all users (with pagination), can add, remove, edit, and search. 

#### Technology used:
- Maven
- Spring MVC
- Hibernate (native API)
- Tomcat 9
- SLF4J & LogBack

#### To run application you need:
- MySQL instance on `localhost:3306`
- Login and password should be `root`
- Then run `init_script.sql`, it will create db, table and fill it with data. 
- Additional options in my.ini may be required:
```
[mysqld]
default-time-zone='+02:00'
explicit_defaults_for_timestamp=false
```


