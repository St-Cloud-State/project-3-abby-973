import java.awt.*;
public interface UIContext {
  //  public abstract void drawCircle(Circle circle);
  public abstract void drawLine(Point point1, Point point2 );
  public abstract void drawLabel(String s, Point p);
  public abstract void drawDot(Point p);
  public abstract void drawPolygon(Point[] points);
  public abstract void drawTriangle(Point point1, Point point2, Point point3);
}
