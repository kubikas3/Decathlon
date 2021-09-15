package main.com.ovi.Models;

public class Rank {
    int start;
    int end;

    public Rank(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        if (start == end) {
            return String.valueOf(start);
        }

        return String.format("%d - %d", start, end);
    }
}
