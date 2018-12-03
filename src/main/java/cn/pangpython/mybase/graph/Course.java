package cn.pangpython.mybase.graph;

public class Course {
    private boolean conflict;
    private int number;

    public Course(boolean conflict, int number) {
        this.conflict = conflict;
        this.number = number;
    }

    public boolean isConflict() {
        return conflict;
    }

    public void setConflict(boolean conflict) {
        this.conflict = conflict;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
