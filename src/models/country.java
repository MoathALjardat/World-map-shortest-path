package models;

public class country {
    private String name;
    private int x;
    private int y;


    private double Latitude;
    private double Longitude;

    public country(String name, double latitude, double longitude, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        Latitude = latitude;
        Longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    @Override
    public String toString() {
        return "country{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                '}';
    }
}
