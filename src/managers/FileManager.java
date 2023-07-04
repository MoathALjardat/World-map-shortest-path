package managers;

import models.Edge;
import models.Vertex;
import models.country;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static graph.Graph.getDistance;

public class FileManager {
    public static ArrayList<Vertex> countries = new ArrayList<>();

    public static ArrayList<Vertex> readCountries() throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/assets/data/countries.txt"));

        String line;
        String tokens[];

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            tokens = line.split("/");
            country country = new country(tokens[0], Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
            Vertex v = new Vertex(country);
            System.out.println(country.toString());
            countries.add(v);
        }

        return countries;

    }

    public static void readEdges() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/assets/data/data.txt"));

        String line;
        String tokens[];

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            tokens = line.split("/");
            System.out.println(tokens[0]+"/"+tokens[1]);

            Vertex v1 = Search(tokens[0]);
            Vertex v2 = Search(tokens[1]);
            Double distance = getDistance(v1, v2);
            Edge e = new Edge(v1, v2, distance);
            v1.addNeighbour(e);

        }


    }

    public static Vertex Search(String name) throws FileNotFoundException {
        Vertex v = null;
        for (int i = 0; i < countries.size(); i++) {
            if (name.compareTo(countries.get(i).getcountry().getName()) == 0) {
                v = countries.get(i);
            }
        }
        return v;

    }

}
