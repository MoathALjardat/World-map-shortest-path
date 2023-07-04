package graph;

import managers.FileManager;
import models.Edge;
import models.Vertex;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import static managers.FileManager.readEdges;

public class Graph {
    public static ArrayList<Vertex> countries;

    static {
        try {
            countries = FileManager.readCountries();
            readEdges();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void dijikstra(Vertex sourceVertex, Vertex target) {

        for (int i = 0; i < countries.size(); i++) {
            countries.get(i).setDistance(Double.MAX_VALUE);
            countries.get(i).setKnown(false);
            countries.get(i).setPrev(null);

        }
        sourceVertex.setDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(sourceVertex);
        sourceVertex.setKnown(true);

        while (!priorityQueue.isEmpty()) {

            Vertex currVertex = priorityQueue.poll();
            if (currVertex == target)
                break;
            if (currVertex != null) {// not adj
                for (Edge edge : currVertex.getAdj()) {

                    Vertex v = edge.getTargetVertex();
                    if (!v.isKnown()) {

                        if (currVertex.getDistance() + edge.getWeight() < v.getDistance()) {
                            priorityQueue.remove(v);
                            v.setDistance(currVertex.getDistance() + edge.getWeight());
                            v.setPrev(currVertex);
                            priorityQueue.add(v);
                        }
                    }
                }
                currVertex.setKnown(true);
            }


        }
    }


    public ArrayList<String> PrintPath(Vertex source, Vertex targetVertex) {
        ArrayList<String> path = new ArrayList<>();
        if (source.getcountry().getName().equals(targetVertex.getcountry().getName())) {
            path.add(source.getcountry().getName() + " " + String.format("%.2f", source.getDistance())+" km");
        } else if (targetVertex.getPrev() == null) {
            path.add("No Path");
        } else {
            for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPrev()) {

                path.add(vertex.getcountry().getName() + " " + String.format("%.2f", vertex.getDistance())+" km");

            }

            Collections.reverse(path);
        }
        return path;
    }

    public static Double getDistance(Vertex A, Vertex B) {
        double lat1 = Math.toRadians(A.getcountry().getLatitude());
        double lat2 = Math.toRadians(B.getcountry().getLatitude());
        double lon1 = Math.toRadians(A.getcountry().getLongitude());
        double lon2 = Math.toRadians(B.getcountry().getLongitude());


        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;
        return (c * r);
    }
}