package view.member;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Member;

public class MemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Jenis Member"};
    private List<Member> data;

    public MemberTableModel(List<Member> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Member rowItem = data.get(row);
        switch (col) {
            case 0:
                return rowItem.getNama();
            case 1:
                return rowItem.getJenisMember().getNama();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void addMember(Member member) {
        data.add(member);
        fireTableRowsInserted(data.size() - 1, data.size() - 1); // Memperbarui tampilan tabel
    }
}
