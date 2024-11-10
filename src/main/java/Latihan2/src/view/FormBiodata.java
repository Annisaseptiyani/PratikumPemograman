package view;

import model.Biodata;
import dao.BiodataDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.*;

public class FormBiodata extends JFrame {
    private JTextField namaTextField, nomorHpTextField;
    private JRadioButton lakiRadioButton, perempuanRadioButton;
    private JCheckBox asingCheckBox;
    private JTextArea outputTextArea;
    private Boolean checkBoxSelected;
    private BiodataDao biodataDao = new BiodataDao();

    public FormBiodata() {
        setTitle("Form Biodata");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Komponen untuk input data
        JLabel namaLabel = new JLabel("Nama:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(namaLabel, gbc);

        namaTextField = new JTextField(20);
        gbc.gridx = 1;
        add(namaTextField, gbc);

        JLabel nomorHpLabel = new JLabel("Nomor HP:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nomorHpLabel, gbc);

        nomorHpTextField = new JTextField(20);
        gbc.gridx = 1;
        add(nomorHpTextField, gbc);

        // Jenis kelamin
        lakiRadioButton = new JRadioButton("Laki-Laki");
        perempuanRadioButton = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(lakiRadioButton);
        genderGroup.add(perempuanRadioButton);

        JPanel genderPanel = new JPanel(new GridLayout(1, 2));
        genderPanel.add(lakiRadioButton);
        genderPanel.add(perempuanRadioButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(genderPanel, gbc);

        // Kewarganegaraan
        asingCheckBox = new JCheckBox("Warga Negara Asing");
        gbc.gridy = 3;
        add(asingCheckBox, gbc);
        asingCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                checkBoxSelected = e.getStateChange() == ItemEvent.SELECTED;
            }
        });

        // Tombol simpan
        JButton simpanButton = new JButton("Simpan");
        gbc.gridy = 4;
        add(simpanButton, gbc);

        // Output dalam JTextArea
        outputTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        gbc.gridy = 5;
        add(scrollPane, gbc);

        // Tombol Simpan ActionListener
        simpanButton.addActionListener(new Biodatabutton(this, biodataDao));
    }

    public void setOutputText(String text) {
        outputTextArea.setText(text);
    }

    public String getNama() {
        return namaTextField.getText();
    }

    public String getNomorHp() {
        return nomorHpTextField.getText();
    }

    public String getjeniskelamin() {
        if (lakiRadioButton.isSelected()) {
            return "Laki-Laki";
        } else {
            return "Perempuan";
        }
    }

    public Boolean getwna() {
        return checkBoxSelected != null && checkBoxSelected;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormBiodata form = new FormBiodata();
            form.setVisible(true);
        });
    }
}
