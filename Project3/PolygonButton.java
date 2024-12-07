import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PolygonButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private PolygonCommand polygonCommand;
    private UndoManager undoManager;

    public PolygonButton(UndoManager undoManager, View view, JPanel drawingPanel) {
        super("Polygon");
        this.undoManager = undoManager;
        this.view = view;
        this.drawingPanel = drawingPanel;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        polygonCommand = new PolygonCommand();
        undoManager.beginCommand(polygonCommand);
        mouseHandler = new MouseHandler();
        drawingPanel.addMouseListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e) {
        Point point = view.mapPoint(e.getPoint());

        if (SwingUtilities.isLeftMouseButton(e)) {
            if (!polygonCommand.isPolygonClosed()) {
                polygonCommand.addPoint(point);
                Model.model.setChanged();
            } else {
                JOptionPane.showMessageDialog(view, "The polygon is already closed.");
            }
        } else if (SwingUtilities.isRightMouseButton(e)) {
            if (!polygonCommand.isPolygonClosed()) {
                if (polygonCommand.closePolygon()) {
                    drawingPanel.removeMouseListener(this);
                    view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    undoManager.endCommand(polygonCommand);
                } else {
                    JOptionPane.showMessageDialog(view, "A polygon requires at least 3 points.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "The polygon is already closed.");
            }
        }
    }
  }
}
