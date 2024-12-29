package controller;

import model.Book;
import model.BookMapper;
import org.apache.ibatis.session.SqlSession;
import view.BookView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class BookController {
    private final BookView view;
    private final BookMapper mapper;
    private final SqlSession session;

    public BookController(BookView view, BookMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;

        // Tambahkan listener ke view
        this.view.addRefreshBookListener(e -> loadBooks());
        this.view.addAddBookListener(e -> addBook());
        this.view.addEditBookListener(e -> editBook());
        this.view.addDeleteBookListener(e -> deleteBook());

        // Muat data awal
        loadBooks();
    }

    /**
     * Memuat semua data buku dan menampilkannya di tabel.
     */
    private void loadBooks() {
        view.showLoading(true); // Tampilkan loading
        SwingWorker<List<Book>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Book> doInBackground() throws Exception {
                return mapper.getAllBooks(); // Ambil data dari database
            }

            @Override
            protected void done() {
                try {
                    List<Book> books = get();
                    view.setBookTable(books); // Tampilkan data di tabel
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(view, "Gagal memuat data: " + e.getMessage());
                } finally {
                    view.showLoading(false); // Sembunyikan loading
                }
            }
        };
        worker.execute();
    }

    /**
     * Menambahkan buku baru ke database.
     */
    private void addBook() {
        try {
            Book book = view.getBookFromForm(); // Ambil data dari form
            mapper.insertBook(book); // Simpan ke database
            session.commit(); // Commit perubahan
            JOptionPane.showMessageDialog(view, "Buku berhasil ditambahkan!");
            view.clearForm(); // Bersihkan form
            loadBooks(); // Refresh tabel
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal menambahkan buku: " + e.getMessage());
        }
    }

    /**
     * Mengedit buku yang dipilih di tabel.
     */
    private void editBook() {
        try {
            // Ambil baris yang dipilih
            int selectedRow = view.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Pilih buku yang ingin diedit terlebih dahulu!");
                return;
            }

            // Ambil data buku dari form
            Book book = view.getBookFromForm();
            mapper.updateBook(book); // Perbarui data di database
            session.commit(); // Commit perubahan
            JOptionPane.showMessageDialog(view, "Buku berhasil diperbarui!");
            view.clearForm(); // Bersihkan form
            loadBooks(); // Refresh tabel
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal mengedit buku: " + e.getMessage());
        }
    }

    /**
     * Menghapus buku yang dipilih di tabel.
     */
    private void deleteBook() {
        try {
            // Ambil baris yang dipilih
            int selectedRow = view.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Pilih buku yang ingin dihapus terlebih dahulu!");
                return;
            }

            // Konfirmasi penghapusan
            int confirm = JOptionPane.showConfirmDialog(view, "Apakah Anda yakin ingin menghapus buku ini?");
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Ambil ID buku dari baris yang dipilih
            int bookId = view.getBookIdFromSelectedRow(selectedRow);
            mapper.deleteBook(bookId); // Hapus dari database
            session.commit(); // Commit perubahan
            JOptionPane.showMessageDialog(view, "Buku berhasil dihapus!");
            loadBooks(); // Refresh tabel
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal menghapus buku: " + e.getMessage());
        }
    }
}
