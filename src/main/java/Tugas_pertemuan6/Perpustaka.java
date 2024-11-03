package Tugas_pertemuan6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Perpustaka extends JFrame {
    public Perpustaka() {
        setTitle("Form Peminjaman Buku");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); 

        //menu bar
        JMenuBar menuBar = new JMenuBar();

        // Menu Pengaturan
        JMenu menuFile = new JMenu("Pengaturan");
        JMenuItem menuItemTambahBuku = new JMenuItem("Tambah Buku");
        JMenuItem menuItemDaftarBuku = new JMenuItem("Daftar Buku");
        JMenuItem menuItemKeluar = new JMenuItem("Keluar");
        menuFile.add(menuItemTambahBuku);
        menuFile.add(menuItemDaftarBuku);
        menuFile.addSeparator();
        menuFile.add(menuItemKeluar);

        // Menu Peminjaman
        JMenu menuPeminjaman = new JMenu("Peminjaman");
        JMenuItem menuItemPinjamBuku = new JMenuItem("Pinjam Buku");
        JMenuItem menuItemRiwayatPeminjaman = new JMenuItem("Riwayat Peminjaman");
        menuPeminjaman.add(menuItemPinjamBuku);
        menuPeminjaman.add(menuItemRiwayatPeminjaman);

        // menu ke menu bar
        menuBar.add(menuFile);
        menuBar.add(menuPeminjaman);
        setJMenuBar(menuBar);

        
        JLabel labelInput = new JLabel("Nama Peminjam:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(labelInput, gbc);

        JTextField textField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField, gbc);

        
        JLabel labelKategori = new JLabel("Kategori Buku:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(labelKategori, gbc);

        String[] kategoriBuku = {"Komik","Self Improvement", "Fiksi"};
        JComboBox<String> comboBoxKategori = new JComboBox<>(kategoriBuku);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(comboBoxKategori, gbc);

        
        JLabel labelJenisBuku = new JLabel("Jenis Buku:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(labelJenisBuku, gbc);

        JRadioButton radioButton1 = new JRadioButton("Komik");
        JRadioButton radioButton2 = new JRadioButton("Self Improvement");
        JRadioButton radioButton3 = new JRadioButton("Fiksi");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(radioButton1, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(radioButton2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(radioButton3, gbc);

        
        JLabel labelDaftarBuku = new JLabel("Pilih Buku:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(labelDaftarBuku, gbc);

        String[] daftarBuku = {"Naruto", "Rich Dad Poor Dad", "Atomic Habits", "Harry Potter", "Sapiens"};
        JList<String> listDaftarBuku = new JList<>(daftarBuku);
        listDaftarBuku.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listDaftarBuku);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(scrollPane, gbc);

        
        JCheckBox checkBox = new JCheckBox("Apakah data yang dipilih sudah benar?");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        add(checkBox, gbc);

        
        JButton button = new JButton("Simpan");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(button, gbc);
        
        String[] columnNames = {"Nama Peminjam", "Kategori Buku", "Jenis Buku", "Buku yang Dipinjam"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
       
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100); 
        columnModel.getColumn(1).setPreferredWidth(80);  
        columnModel.getColumn(2).setPreferredWidth(80);  
        columnModel.getColumn(3).setPreferredWidth(150); 
        
        JScrollPane scrollTable = new JScrollPane(table);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        add(scrollTable, gbc);


        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected()) {
                    String namaPeminjam = textField.getText();
                    String kategori = (String) comboBoxKategori.getSelectedItem();
                    String jenisBuku = "";
                    if (radioButton1.isSelected()) jenisBuku = radioButton1.getText();
                    if (radioButton2.isSelected()) jenisBuku = radioButton2.getText();
                    if (radioButton3.isSelected()) jenisBuku = radioButton3.getText();
                    String bukuDipilih = listDaftarBuku.getSelectedValue();

         tableModel.addRow(new Object[]{namaPeminjam, kategori, jenisBuku, bukuDipilih});
                } else {
                    JOptionPane.showMessageDialog(null, "Harap konfirmasi data terlebih dahulu.");
                }
            }
     
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Perpustaka frame = new Perpustaka();
            frame.setVisible(true);
        });
    }
}
