package Pertemuan3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FromBiodata extends JFrame {

    // Komponen yang akan digunakan
    JTextField namaTextField, nomorhpTextField;
    JRadioButton lakilakiRadioButton, perempuanRadioButton;
    JCheckBox wargaNegaraAsingCheckBox;
    JTextArea outputTextArea;

    public FromBiodata() {
        // Set judul dan ukuran jendela
        setTitle("Form Biodata");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        // GridBagConstraints untuk mengatur posisi komponen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Bagian header
        JLabel headerLabel = new JLabel("Form Biodata", JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(headerLabel, gbc);

        // Panel untuk input nama dan nomor HP
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel namaLabel = new JLabel("Nama:");
        inputPanel.add(namaLabel);

        namaTextField = new JTextField();
        inputPanel.add(namaTextField);

        JLabel nomorhpLabel = new JLabel("Nomor HP:");
        inputPanel.add(nomorhpLabel);

        nomorhpTextField = new JTextField();
        inputPanel.add(nomorhpTextField);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(inputPanel, gbc);

        // Panel untuk jenis kelamin
        JPanel genderPanel = new JPanel(new GridLayout(1, 2));
        lakilakiRadioButton = new JRadioButton("Laki-Laki");
        perempuanRadioButton = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(lakilakiRadioButton);
        genderGroup.add(perempuanRadioButton);
        genderPanel.add(lakilakiRadioButton);
        genderPanel.add(perempuanRadioButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(genderPanel, gbc);

        // Panel untuk status kewarganegaraan
        wargaNegaraAsingCheckBox = new JCheckBox("Warga Negara Asing");

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(wargaNegaraAsingCheckBox, gbc);

        // Tombol Simpan
        JButton simpanButton = new JButton("Simpan");

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(simpanButton, gbc);

        // Area untuk menampilkan hasil input
        outputTextArea = new JTextArea(5, 20);
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(outputScrollPane, gbc);

        // ActionListener untuk tombol simpan
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mengambil data dari form
                String nama = namaTextField.getText();
                String nomorhp = nomorhpTextField.getText();
                String jenisKelamin = lakilakiRadioButton.isSelected() ? "Laki-Laki" : "Perempuan";
                boolean isAsing = wargaNegaraAsingCheckBox.isSelected();

                // Format output
                String hasil = "Nama: " + nama + "\n"
                             + "Nomor HP: " + nomorhp + "\n"
                             + "Jenis Kelamin: " + jenisKelamin + "\n"
                             + (isAsing ? "Status: Warga Negara Asing" : "Status: Warga Negara Indonesia");

                // Menampilkan hasil di area output
                outputTextArea.setText(hasil);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FromBiodata form = new FromBiodata();
            form.setVisible(true);
        });
    }
}
