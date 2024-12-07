import java.awt.Point;

public class PolygonCommand extends Command {
    private Polygon polygon;
    private boolean isClosed = false;

    public PolygonCommand() {
        polygon = new Polygon();
    }

    public void addPoint(Point point) {
        polygon.addPoint(point);
    }

    public boolean closePolygon() {
        if (polygon.getPoints().size() >= 3) {
            polygon.close();
            return true;
        } else {
            return false;
        }
    }


    public boolean isPolygonClosed() {
        return isClosed;
    }

    public Polygon getPolygon() {
        return polygon;
    }

   @Override
public void execute() {
    System.out.println("Executing PolygonCommand");
    model.addItem(polygon);
    model.setChanged();
}

@Override
public boolean undo() {
    System.out.println("Undoing PolygonCommand");
    model.removeItem(polygon);
    model.setChanged();
    return true;
}

@Override
public boolean redo() {
    System.out.println("Redoing PolygonCommand");
    model.addItem(polygon);
    model.setChanged();
    return true;
}

    @Override
    public boolean end() {
        if (isClosed) {
            execute();
            return true;
        }
        return false;
    }
}
