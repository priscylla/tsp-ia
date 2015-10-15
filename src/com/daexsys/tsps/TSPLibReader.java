package com.daexsys.tsps;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TSPLibReader {

    public List<Node> nodes = new ArrayList<Node>();

    public static void main(String[] args) {

        TSPLibReader tspLibReader = new TSPLibReader("./data/tsp/pr76.tsp");

        System.out.println(tspLibReader.nodes.size());
        
    }

    public TSPLibReader(String loc) {

        System.out.println("Loading: " + loc);

        File file = new File(loc);
        try {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            Scanner scanner = new Scanner(dataInputStream);

            boolean readType = false;

            while(scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if(readType && !line.equalsIgnoreCase("EOF")) {
                    System.out.println(line);
                    handleNode(line);
                }

                if(line.equalsIgnoreCase("NODE_COORD_SECTION")) {
                    readType = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void handleNode(String line) {

        String[] spl = line.split("\\s+");

        double x = Double.parseDouble(spl[1]);
        double y = Double.parseDouble(spl[2]);

        nodes.add(new Node(x, y, "", 0));
    }
}