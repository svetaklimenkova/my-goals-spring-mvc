package by.samsolutions.internship.java.mygoals.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Column(name = "iduser")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String mail;

    @Column(name = "birthdate")
    private Date birthdayDate;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column
    private String country;

    @Column
    private String city;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    private List<Goal> goals;

    public User() {
    }

    public User(int id, String login, String password, String mail, Date birthdayDate, Date creationDate, String country, String city) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.birthdayDate = birthdayDate != null ? new Date(birthdayDate.getTime()) : null;
        this.creationDate = creationDate != null ? new Date(creationDate.getTime()) : null;
        this.country = country;
        this.city = city;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getBirthdayDate() {
        return birthdayDate != null ? new Date(birthdayDate.getTime()) : null;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate != null ? new Date(birthdayDate.getTime()) : null;
    }

    public Date getCreationDate() {
        return creationDate != null ? new Date(creationDate.getTime()) : null;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate != null ? new Date(creationDate.getTime()) : null;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", creationDate=" + creationDate +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}
