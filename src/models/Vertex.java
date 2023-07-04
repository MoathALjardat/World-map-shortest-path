package models;

import java.util.ArrayList;

public class Vertex implements Comparable<Vertex> {

    private country country;
    private ArrayList<Edge> adj;
    private boolean known;
    private Vertex prev;
    private double distance;

    public Vertex(country country) {
        this.country = country;
        this.adj = new ArrayList<>();
    }

    public void addNeighbour(Edge edge) {
        this.adj.add(edge);
    }

    public country getcountry() {
        return country;
    }

    public void setcountry(country country) {
        this.country = country;
    }

    public ArrayList<Edge> getAdj() {
        return adj;
    }

    public void setAdj(ArrayList<Edge> adj) {
        this.adj = adj;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(this.distance, otherVertex.getDistance());
    }

}