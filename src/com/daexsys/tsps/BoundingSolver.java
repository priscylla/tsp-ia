package com.daexsys.tsps;

import java.util.Collection;

public class BoundingSolver {

    public double shortestDistance = 150000;
    public double result;

    public BoundingSolver(Collection<Node> collection, double upperBound) {
        this.shortestDistance = upperBound;
        double[][] nodeData = new double[2][];
        nodeData[0] = new double[collection.size()];
        nodeData[1] = new double[collection.size()];

        double[] distances = new double[collection.size() * collection.size()];

        int i = 0;
        for (Node tspNode : collection) {
            nodeData[0][i] = tspNode.getX();
            nodeData[1][i] = tspNode.getY();
            i++;
        }

        for (i = 0; i < collection.size(); i++) {
            for (int k = 0; k < collection.size(); k++) {
                double x = nodeData[0][i] - nodeData[0][k];
                double y = nodeData[1][i] - nodeData[1][k];
                distances[i * collection.size() + k] = Math.sqrt(x * x + y * y);
            }
        }

        int[] z = new int[collection.size() - 1];
        for (int j = 0; j < z.length; j++) {
            z[j] = j;
        }

        result = permute(z, distances);
    }

    private int barrier = 0;
    private int perms = 0;

    private double permute(final int[] array, final double[] distances) {
        final int arrLength = array.length;
        final int mul = arrLength + 1;
        final int limit = mul - 2;
        final int eA = arrLength * mul;
        final int nlim = limit - 5; // 5 standard, 4 >= 14

        final int[] p = new int[arrLength];
        handle(array, distances, p, limit, eA, mul, nlim);

        perms++;

        int i = 1;
        while (i < arrLength) {

            while (p[i] < i) {
                int j = i % 2 * p[i];

                // Swap array values
                int buffer = array[i];
                array[i] = array[j];
                array[j] = buffer;
                // End swap

                if(perms > barrier) {
                    // Handle permutation
                    handle(array, distances, p, limit, eA, mul, nlim);
                }

                perms++;

                p[i]++;
                i = 1;
            }

            p[i++] = 0;
        }

        return shortestDistance;
    }

    private void handle(final int array[], final double[] distances2, final int[] p, final int limit, final int eA, final int mul, final int nlim) {
        double v = 0;

        int z;

        v = distances2[eA + array[0]];
        v += distances2[eA + array[limit]];

        for (z = array.length - 1; z > nlim; z--) {
            v += distances2[(array[z] * mul) + (array[z - 1])];
        }

        for (z = nlim; z > 0; z--) {
            if((v += distances2[(array[z] * mul) + (array[z - 1])]) > shortestDistance) {
                // If is too high!
                switch (z) {
                    case 4:   barrier = ((perms / 2) + 1) * 2; return;
                    case 5:   barrier = ((perms / 6) + 1) * 6; return;
                    case 6:   barrier = ((perms / 24) + 1) * 24; return;
                    case 7:   barrier = ((perms / 120) + 1) * 120; return;
                    case 8:   barrier = ((perms / 720) + 1) * 720; return;
                    case 9:   barrier = ((perms / 5040) + 1) * 5040; return;
                    case 10:  barrier = ((perms / 40320) + 1) * 40320; return;
                    case 11:  barrier = ((perms / 362880) + 1) * 362880; return;
                    case 12:  barrier = ((perms / 3628800) + 1) * 3628800; return;
                    case 13:  barrier = ((perms / 39916800) + 1) * 39916800; return;
                    case 14:  barrier = ((perms / 479001600) + 1) * 479001600; return;
                }
            }
        }

        if (v < shortestDistance)
            this.shortestDistance = v;
    }

    public double getResult() {
        return result;
    }
}