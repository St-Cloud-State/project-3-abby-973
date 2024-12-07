import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon extends Item {
  private List<Point> points;
  private boolean isClosed;

  public Polygon() {
    points = new ArrayList<>();
    isClosed = false;
  }

  public void close() {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }

  public void addPoint(Point point) {
    points.add(point);
  }

  public List<Point> getPoints() {
    return points;
  }

  // Determines if a point is inside the polygon
  public boolean includes(Point point) {

    int crossings = 0;
    int count = points.size();
    for (int i = 0; i < count; i++) {
      Point a = points.get(i);
      Point b = points.get((i + 1) % count);
      if (rayCrossesSegment(point, a, b)) {
        crossings++;
      }
    }
    return (crossings % 2 == 1);
  }

  private boolean rayCrossesSegment(Point point, Point a, Point b) {
    double px = point.getX();
    double py = point.getY();
    double ax = a.getX();
    double ay = a.getY();
    double bx = b.getX();
    double by = b.getY();

    if (ay > by) {
      double tempX = ax;
      double tempY = ay;
      ax = bx;
      ay = by;
      bx = tempX;
      by = tempY;
    }

    if (py == ay || py == by) {
      py += 0.0001;
    }

    if (py < ay || py > by) {
      return false;
    } else if (px >= Math.max(ax, bx)) {
      return false;
    } else {
      double red = (px - ax) / (bx - ax);
      double blue = (py - ay) / (by - ay);
      return red >= blue;
    }
  }

  @Override
  public void render(UIContext uiContext) {
      int pointCount = points.size();

      // Draw lines between consecutive points
      for (int i = 0; i < pointCount - 1; i++) {
          Point p1 = points.get(i);
          Point p2 = points.get(i + 1);
          uiContext.drawLine(p1, p2);
      }

      // If the polygon is closed, connect the last point to the first
      if (isClosed && pointCount > 2) {
          Point firstPoint = points.get(0);
          Point lastPoint = points.get(pointCount - 1);
          uiContext.drawLine(lastPoint, firstPoint);
      }

      // Draw dots at each point
      for (Point point : points) {
          uiContext.drawDot(point);
      }
  }

  public void translate(int dx, int dy) {
    for (Point point : points) {
      point.translate(dx, dy);
    }
  }
}
