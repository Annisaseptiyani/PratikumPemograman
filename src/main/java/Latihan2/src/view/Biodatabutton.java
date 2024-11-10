package view;

import model.Biodata;
import dao.BiodataDao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Biodatabutton implements ActionListener {
    private FormBiodata biodataForm;
    private BiodataDao biodataDao;

    public Biodatabutton(FormBiodata biodataForm, BiodataDao biodataDao) {
        this.biodataForm = biodataForm;
        this.biodataDao = biodataDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = biodataForm.getNama();
        String nomorhp = biodataForm.getNomorHp();
        String jeniskelamin = biodataForm.getjeniskelamin();
        Boolean warganegaraasing = biodataForm.getwna();

        // Validasi input
        if (nama == null || nama.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong.");
            return;
        }
        if (nomorhp == null || nomorhp.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nomor HP tidak boleh kosong.");
            return;
        }
        if (jeniskelamin == null) {
            JOptionPane.showMessageDialog(null, "Jenis Kelamin harus dipilih.");
            return;
        }

        // Set data ke objek Biodata
        Biodata biodata = new Biodata();
        biodata.setNama(nama);
        biodata.setNomorhp(nomorhp);
        biodata.setJeniskelamin(jeniskelamin);
        biodata.setWna(warganegaraasing);

        // Insert ke database
        biodataDao.insertBiodata(biodata);

        // Update output text di JTextArea
        String outputText = "Nama: " + nama + "\nNomor HP: " + nomorhp + "\nJenis Kelamin: " + jeniskelamin;
        if (warganegaraasing != null && warganegaraasing) {
            outputText += "\nWarga Negara Asing: Ya";
        } else {
            outputText += "\nWarga Negara Asing: Tidak";
        }

        biodataForm.setOutputText(outputText);

        // Tampilkan pesan berhasil
        JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
    }
}
