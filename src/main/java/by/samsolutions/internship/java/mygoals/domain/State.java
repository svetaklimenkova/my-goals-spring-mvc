package by.samsolutions.internship.java.mygoals.domain;

public enum State {
    NOT_STARTED(0),
    IN_PROGRESS(1),
    FINISHED(2);

    private int i;

    State(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    private void setI(int i) {
        this.i = i;
    }

    public static State get(int i) {
        switch (i) {
            case 0 : return State.NOT_STARTED;
            case 1 : return State.IN_PROGRESS;
            case 2 : return State.FINISHED;
            default: throw  new RuntimeException("Unknown index of State: " + i);
        }
    }
}
