package by.samsolutions.internship.java.mygoals.domain;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "task")
public class Task extends Entity {

    @Column(name = "idtask")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage")
    private Stage stage;

    public Task(){}

    public Task(String name, State state) {
        super(name, state);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", state=" + getState() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        return stage != null ? stage.equals(task.stage) : task.stage == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        return result;
    }
}
