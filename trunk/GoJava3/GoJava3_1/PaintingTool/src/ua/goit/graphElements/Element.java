package ua.goit.graphElements;

import java.util.ArrayList;

public interface Element {

    public void addPoint(Point point);
    public ArrayList<Point> getPoints();
    public String getName();
    public String getType();
  }