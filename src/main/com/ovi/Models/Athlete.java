package main.com.ovi.Models;

import java.util.List;
import java.util.Objects;

public class Athlete {
    private String name;
    private List<Double> performance;
    private Rank rank;
    private int mark;

    public Athlete(String fullName, List<Double> performance) {
        this.name = fullName;
        this.performance = performance;
    }

    public String getName() {
        return name;
    }

    public List<Double> getPerformance() {
        return performance;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        return Objects.equals(mark, ((Athlete)o).mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark);
    }
}
