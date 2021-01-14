package MusicTag;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class test2 extends JFrame{
    private JTable table = new JTable();
    private JScrollPane scroll = new JScrollPane(table);
    private DefaultTableModel tm = new DefaultTableModel(new String[]{"a","b","c"},2);

    public test2() {
        table.setModel(tm);
        table.setDropTarget(new DropTarget(){
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                Point point = dtde.getLocation();
                int column = table.columnAtPoint(point);
                int row = table.rowAtPoint(point);
                // handle drop inside current table
                super.drop(dtde);
                

				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.addRow(new String[] {"","",""});
            }
        });
        scroll.setDropTarget(new DropTarget(){
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                // handle drop outside current table (e.g. add row)
                super.drop(dtde);

				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.addRow(new String[] {"","",""});
            }
        });
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(scroll);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new test2();
    }
}