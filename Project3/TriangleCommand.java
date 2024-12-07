import java.awt.Point;

public class TriangleCommand extends Command {
    private Triangle triangle;

    public TriangleCommand() {
        triangle = new Triangle();
    }

    public void addPoint(Point point) {
        triangle.addPoint(point);
    }

    public Triangle getTriangle() {
        return triangle;
    }

   @Override
public void execute() {
    System.out.println("Executing TriangleCommand");
    model.addItem(triangle);
    model.setChanged();
}

@Override
public boolean undo() {
    System.out.println("Undoing TriangleCommand");
    model.removeItem(triangle);
    model.setChanged();
    return true;
}

@Override
public boolean redo() {
    System.out.println("Redoing TriangleCommand");
    model.addItem(triangle);
    model.setChanged();
    return true;
}

    @Override
    public boolean end() {
        if (triangle.getPoint3() != null) {
            execute();
            return true;
        }
        return false;
    }
}
