import java.awt.*;

public class Triangle extends Item {
  private Point point1;
  private Point point2;
  private Point point3;
  private int clickCount;

  public Triangle() {
    point1 = null;
    point2 = null;
    point3 = null;
    clickCount = 0;
  }

    public void addPoint(Point point) {
    clickCount++;

    if (clickCount == 1) {
      point1 = point;
    } else if (clickCount == 2) {
      point2 = point;
    } else if (clickCount == 3) {
      point3 = point;
    }
  }

  public boolean includes(Point point) {
    return (distance(point, point1) < 10.0) || 
           (distance(point, point2) < 10.0) || 
           (distance(point, point3) < 10.0);
  }

  @Override
  public void render(UIContext uiContext) {
    
      uiContext.drawLine(point1, point2);
      uiContext.drawLine(point2, point3);
      uiContext.drawLine(point3, point1);

      uiContext.drawDot(point1);
      uiContext.drawDot(point2);
      uiContext.drawDot(point3);
  }
  public void translate(int dx, int dy) {
      point1.translate(dx, dy);
      point2.translate(dx, dy);
      point3.translate(dx, dy);
  }
  public void setPoint1(Point point) {
    point1 = point;
  }
  public void setPoint2(Point point) {
    point2 = point;
  }
  public void setPoint3(Point point) {
    point3 = point;
  }
  public Point getPoint1() {
    return point1;
  }
  public Point getPoint2() {
    return point2;
  }  
  public Point getPoint3() {
    return point3;
  }
  public String toString() {
    return "Triangle with points: " + point1 + ", " + point2 + ", " + point3;
  }
}

