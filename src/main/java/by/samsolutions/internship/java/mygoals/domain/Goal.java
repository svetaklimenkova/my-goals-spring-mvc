package by.samsolutions.internship.java.mygoals.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@DynamicUpdate
@javax.persistence.Entity
@Table(name = "goal")
public class Goal extends Entity {

    @Column(name = "idgoal")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String description;

    @NotNull
    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "goal")
    private List<Stage> stages;

    public Goal(){

    }

    public Goal(String name, String description, Date creationDate, State state) {
        super(name, state);
        this.description = description;
        this.creationDate =  creationDate != null ? new Date(creationDate.getTime()) : null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate != null ? new Date(creationDate.getTime()) : null;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate != null ? new Date(creationDate.getTime()) : null;
    }

    public Date getStartDate() {
        return startDate != null ? new Date(startDate.getTime()) : null;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate != null ? new Date(startDate.getTime()) : null;
    }

    public Date getEndDate() {
        return endDate != null ? new Date(endDate.getTime()) : null;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate != null ? new Date(endDate.getTime()) : null;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Goal goal = (Goal) o;

        if (id != goal.id) return false;
        if (description != null ? !description.equals(goal.description) : goal.description != null) return false;
        if (creationDate != null ? !creationDate.equals(goal.creationDate) : goal.creationDate != null) return false;
        if (startDate != null ? !startDate.equals(goal.startDate) : goal.startDate != null) return false;
        if (endDate != null ? !endDate.equals(goal.endDate) : goal.endDate != null) return false;
        return user != null ? user.equals(goal.user) : goal.user == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", state=" + getState() +
                ", stages=" + stages +
                '}';
    }
}
