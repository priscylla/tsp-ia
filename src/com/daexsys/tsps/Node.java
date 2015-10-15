package com.daexsys.tsps;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Node {
    private double x, y;
    private int id;

    public int getId() {
        return id;
    }

    public Node(double x, double y, String name, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double distanceTo(Node node) {
        double x = node.getX() - this.getX();
        double y = node.getY() - this.getY();
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Node)) return false;

        Node other = (Node) o;

        if(other.getX() == getX() && other.getY() == getY()) return true;
        return false;

    }

//    public Edge getLineTo(Concept concept) {
////        if(concept == null) {
////            if (concept instanceof Node) {
////                Node node = (Node) concept;
//        return new Edge(this, ((Node) concept));
////            } else {
////                Group group = (Group) concept;
////                Node nearest = group.getNearestEndTo(this);
////                return new Edge(this, nearest);
////            }
////        } else {
////            System.out.println("uh");
////        }
////        return null;
//    }

    public Node getNearestEndTo(Node node) {
        return this;
    }

    public String toString() {
        return this.getX() + " " + this.getY();
    }

    public List<Node> getComponents() {
        List<Node> list = new ArrayList<Node>();
        list.add(this);
        return list;
    }

    public Point getPoint2D() {
        return new Point((int) getX(), (int) getY());
    }

    public static Node getFromPoint2D(Point2D.Double point) {
        return new Node(point.x, point.y, "", 0);
    }
}