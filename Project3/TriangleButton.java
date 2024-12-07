import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TriangleButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private TriangleCommand triangleCommand;
  private UndoManager undoManager;

  public TriangleButton(UndoManager undoManager, View view, JPanel drawingPanel) {
    super("Triangle");
    this.undoManager = undoManager;
    this.view = view;
    this.drawingPanel = drawingPanel;
    addActionListener(this);
}

@Override
public void actionPerformed(ActionEvent event) {
    view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    triangleCommand = new TriangleCommand();
    undoManager.beginCommand(triangleCommand);
    mouseHandler = new MouseHandler();
    drawingPanel.addMouseListener(mouseHandler);
}

  private class MouseHandler extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        int pointCounter = 0;
        Point point = view.mapPoint(e.getPoint());

        if (SwingUtilities.isLeftMouseButton(e)) {
            pointCounter++;
            if(pointCounter < 3)
            {
                triangleCommand.addPoint(point);
                Model.model.setChanged();
            }
            else {
                triangleCommand.addPoint(point);
                Model.model.setChanged();
                drawingPanel.removeMouseListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(triangleCommand);
            }
        }
    }
  }
}