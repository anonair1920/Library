package newpackage;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table {
    public void table(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Published");

        JTable table = new JTable(model);
        model.addRow( new Object[]{ "Col1", "col2", "col3"});
    }
}
