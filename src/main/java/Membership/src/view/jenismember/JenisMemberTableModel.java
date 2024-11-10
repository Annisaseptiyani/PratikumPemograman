package view.jenismember;
import javax.swing.table.*;
import java.util.List;
import model.JenisMember;

public class JenisMemberTableModel extends AbstractTableModel{
    private String[] columnNames = {"Jenis member"};
    private List<JenisMember> data;

    public JenisMemberTableModel(List<JenisMember>data){
        this.data = data;
    }

    @Override
    public int getColumnCount(){
        return columnNames.length;
    }

    @Override
    public int getRowCount(){
        return data.size();
    }

    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    public Object getValueAt(int row, int col){
        JenisMember rowItem = data.get(row);
        String value = "";
        switch (col) {
            case 0:
                value = rowItem.getNama();
        }
        return value;
    }

public boolean isCellEditable(int row, int col){
    return false;
}
 public void add(JenisMember value){
    data.add(value);
    fireTableRowsInserted(data.size() -1, data.size() -1);
 }
}
