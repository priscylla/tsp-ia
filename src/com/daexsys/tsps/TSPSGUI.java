package com.daexsys.tsps;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class TSPSGUI {

    public static void main(String[] args) {
        new TSPSGUI();
    }

    private List<Node> nodes;
    private File fc = null;

    public TSPSGUI() {


        JFrame jFrame = new JFrame("TSP Solver GUI");
        jFrame.setSize(340, 280);
        jFrame.setLayout(null);

        final JLabel fileName = new JLabel("No file selected");
        fileName.setBounds(20, 10, 500, 20);
        jFrame.add(fileName);


        final JLabel nodeCount = new JLabel("Node count: N/A");
        nodeCount.setBounds(20, 80, 200, 20);
        jFrame.add(nodeCount);

        JButton selectFile = new JButton("Select TSPLIB file");
        selectFile.setBounds(20, 40, 150, 20);
        jFrame.add(selectFile);
        selectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showOpenDialog(jFileChooser);
//                jFileChooser.setAcceptAllFileFilterUsed(false);
//                jFileChooser.setMultiSelectionEnabled(false);
//                jFileChooser.setFileFilter(new FileNameExtensionFilter("TSPLIB file", ".tsplib"));

                fc = jFileChooser.getSelectedFile();
                fileName.setText(fc.getAbsolutePath());

                TSPLibReader tspLibReader = new TSPLibReader(fc.getAbsolutePath());
                nodes = tspLibReader.nodes;
                if(nodes.size() < 15) {
                    nodeCount.setText("Node count: " + nodes.size());
                } else {
                    nodeCount.setText("Node count: " + nodes.size() + " (probably too high!)");
                }
            }
        });

        JLabel tspAlgoToUse = new JLabel("Algorithm to use:");
        tspAlgoToUse.setBounds(20, 110, 300, 30);
        jFrame.add(tspAlgoToUse);

        JComboBox jComboBox = new JComboBox();
//        jComboBox.addItem("bruteforce");
        jComboBox.addItem("bounding bruteforcer");
//        jComboBox.addItem("nearest neighbor");
//        jComboBox.addItem("quicktsp (heuristic)");
        jComboBox.setBounds(20, 140, 300, 30);
        jFrame.add(jComboBox);

        final JLabel shortestPath = new JLabel("Result: 0");
        shortestPath.setBounds(20, 200, 500, 20);
        jFrame.add(shortestPath);

        final JLabel timeTaken = new JLabel("Time taken: N/A");
        timeTaken.setBounds(20, 220, 500, 20);
        jFrame.add(timeTaken);

        JButton solve = new JButton("Solve");
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                long startTime = System.currentTimeMillis();
                BoundingSolver boundingSolver = new BoundingSolver(nodes, 150000);
                long totalTime = System.currentTimeMillis() - startTime;
                shortestPath.setText("Result: " + boundingSolver.getResult());
                timeTaken.setText("Time taken: " + totalTime + " milliseconds");

            }
        });
        solve.setBounds(20, 260, 100, 20);
        jFrame.add(solve);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setSize(360, 340);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.repaint();

    }
}