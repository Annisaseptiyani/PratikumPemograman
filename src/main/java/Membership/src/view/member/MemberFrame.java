package view.member;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import model.*;
import dao.MemberDao;
import dao.JenisMemberDao;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = this.jenisMemberDao.findAll();
        this.memberList = this.memberDao.findAll();  // Mengambil daftar anggota

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 30);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 350, 30);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 150, 30);
        populateComboJenis(); // Mengisi JComboBox dengan data jenis member

        JButton button = new JButton("Simpan");
        button.setBounds(15, 160, 100, 40);

        // Mengatur JTable dan JScrollPane
        tableModel = new MemberTableModel(memberList);
        JTable table = new JTable(tableModel);
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 220, 350, 200);

        MemberButtonSimpanActionListener actionListener = new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);

        this.add(button);
        this.add(textFieldNama);
        this.add(labelInput);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(scrollableTable);  // Menambahkan scrollableTable ke JFrame

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member) {
        tableModel.addMember(member);  // Menambahkan member ke dalam table model
        textFieldNama.setText("");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
