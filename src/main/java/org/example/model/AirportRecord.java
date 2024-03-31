package org.example.model;

public class AirportRecord {
    private Airport airport;
    private int index;
    private AirportRecord next;

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public AirportRecord getNext() {
        return next;
    }

    public void setNext(AirportRecord next) {
        this.next = next;
    }
}
