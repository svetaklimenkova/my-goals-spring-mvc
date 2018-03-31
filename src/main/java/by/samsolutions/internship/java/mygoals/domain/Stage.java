package by.samsolutions.internship.java.mygoals.domain;


import javax.persistence.*;
import java.util.List;

@javax.persistence.Entity
@Table(name = "stage")
public class Stage extends Entity {
    @Column(name = "idstage")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sortOrder")
    private int order;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage")
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal")
    private Goal goal;

    public Stage() { }

    public Stage(String name, int order, State state) {
        super(name, state);
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Stage{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", order=" + order +
                ", state=" + getState() +
                ", tasks=" + tasks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Stage stage = (Stage) o;

        if (id != stage.id) return false;
        if (order != stage.order) return false;
        return goal != null ? goal.equals(stage.goal) : stage.goal == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + order;
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        return result;
    }
}
