import java.awt.Graphics;
import java.awt.*;
public class NewSwingUI implements UIContext {
  private Graphics graphics;
  private static NewSwingUI swingUI;
  private NewSwingUI() {
  }
  public static NewSwingUI getInstance() {
    if (swingUI == null) {
      swingUI = new NewSwingUI();
    }
    return swingUI;
  }
  public  void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }
  public void drawLabel(String s, Point p) {
    if (p != null) {
      if (s != null) {
        graphics.drawString(s, (int) p.getX(), (int) p.getY());
      }
    }
    int length = graphics.getFontMetrics().stringWidth(s);
    graphics.drawString("_", (int)p.getX() + length, (int)p.getY());
  }
  public void drawLine(Point point1,  Point point2) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (point1 != null) {
      i1 = Math.round((float) (point1.getX()));
      i2 = Math.round((float) (point1.getY()));
      if (point2 != null) {
        i3 = Math.round((float) (point2.getX()));
        i4 = Math.round((float) (point2.getY()));
      } else {
        i3 = i1;
        i4 = i2;
      }
      graphics.drawLine(i1, i2, i3, i4);
    }
  }

public void drawDot(Point p) {
    if (p != null) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        graphics.fillOval(x - 2, y - 2, 4, 4); // Draws a small circle with a diameter of 4 pixels
    }
}

  public void drawPolygon(Point[] points) {
  if (points != null && points.length > 0) {
    int[] xPoints = new int[points.length];
    int[] yPoints = new int[points.length];
    for (int i = 0; i < points.length; i++) {
      xPoints[i] = (int) points[i].getX();
      yPoints[i] = (int) points[i].getY();
    }
    graphics.drawPolygon(xPoints, yPoints, points.length);
  }
}
public void drawTriangle(Point p1, Point p2, Point p3) {
  Point[] points = new Point[3];
  points[0] = p1;
  points[1] = p2;
  points[2] = p3;
  if (points != null && points.length > 0) {
    int[] xPoints = new int[points.length];
    int[] yPoints = new int[points.length];
    for (int i = 0; i < points.length; i++) {
      xPoints[i] = (int) points[i].getX();
      yPoints[i] = (int) points[i].getY();
    }
    graphics.drawPolygon(xPoints, yPoints, points.length);
  }
}
 
}
