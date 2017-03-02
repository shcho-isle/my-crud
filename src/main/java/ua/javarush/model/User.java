package ua.javarush.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.sql.Timestamp;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Length(min = 3, max = 25)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]*$")
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @NotNull
    @Range(min = 1, max = 120)
    @Column(name = "AGE", nullable = false)
    private Integer age;

    @Column(name = "IS_ADMIN", nullable = false)
    private boolean admin;

    @Column(name = "CREATED_DATE")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Timestamp createdDate;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        if (id != other.id) {
            return false;
        }

        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String isAdmin = "";
        if (admin) {
            isAdmin = ", admin";
        }
        return "User [id=" + id +
                ", name=" + name +
                ", age=" + age +
                isAdmin + "]";
    }
}
