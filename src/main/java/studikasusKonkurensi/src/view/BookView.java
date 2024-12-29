package view;

import model.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField yearField;
    private JTextField genreField;
    private JTextField stockField;
    private JProgressBar progressBar;

    public BookView() {
        setTitle("Book Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Tabel dan model
        tableModel = new DefaultTableModel(new Object[]{"ID", "Title", "Author", "Year", "Genre", "Stock"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Panel form input
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));

        formPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        formPanel.add(titleField);

        formPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        formPanel.add(authorField);

        formPanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        formPanel.add(yearField);

        formPanel.add(new JLabel("Genre:"));
        genreField = new JTextField();
        formPanel.add(genreField);

        formPanel.add(new JLabel("Stock:"));
        stockField = new JTextField();
        formPanel.add(stockField);

        progressBar = new JProgressBar();
        progressBar.setVisible(false); // Awalnya disembunyikan
        formPanel.add(progressBar);
        formPanel.add(new JLabel()); // Kosong untuk mengisi grid layout

        mainPanel.add(formPanel, BorderLayout.EAST);

        // Panel tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        refreshButton = new JButton("Refresh");
        addButton = new JButton("Add Book");
        editButton = new JButton("Edit Book");
        deleteButton = new JButton("Delete Book");

        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Tambahkan panel utama ke frame
        add(mainPanel);
    }

    /**
     * Menampilkan atau menyembunyikan progress bar.
     *
     * @param isLoading true jika ingin menampilkan, false jika ingin menyembunyikan.
     */
    public void showLoading(boolean isLoading) {
        progressBar.setVisible(isLoading);
    }

    /**
     * Mengatur data di tabel buku.
     *
     * @param books daftar buku yang akan ditampilkan.
     */
    public void setBookTable(List<Book> books) {
        tableModel.setRowCount(0); // Reset data tabel
        for (Book book : books) {
            tableModel.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getYear(), book.getGenre(), book.getStock()});
        }
    }

    /**
     * Membersihkan form input.
     */
    public void clearForm() {
        titleField.setText("");
        authorField.setText("");
        yearField.setText("");
        genreField.setText("");
        stockField.setText("");
    }

    /**
     * Mendapatkan data buku dari form input.
     *
     * @return objek Book dengan data dari form.
     */
    public Book getBookFromForm() {
        Book book = new Book();
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setYear(Integer.parseInt(yearField.getText()));
        book.setGenre(genreField.getText());
        book.setStock(Integer.parseInt(stockField.getText()));
        return book;
    }

    /**
     * Mendapatkan indeks baris yang dipilih di tabel.
     *
     * @return indeks baris yang dipilih, atau -1 jika tidak ada yang dipilih.
     */
    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    /**
     * Mendapatkan ID buku dari baris yang dipilih di tabel.
     *
     * @param selectedRow indeks baris yang dipilih.
     * @return ID buku dari kolom pertama tabel.
     */
    public int getBookIdFromSelectedRow(int selectedRow) {
        return (int) table.getValueAt(selectedRow, 0);
    }

    /**
     * Menambahkan listener untuk tombol refresh.
     *
     * @param listener ActionListener yang akan ditambahkan.
     */
    public void addRefreshBookListener(java.awt.event.ActionListener listener) {
        refreshButton.addActionListener(listener);
    }

    /**
     * Menambahkan listener untuk tombol add.
     *
     * @param listener ActionListener yang akan ditambahkan.
     */
    public void addAddBookListener(java.awt.event.ActionListener listener) {
        addButton.addActionListener(listener);
    }

    /**
     * Menambahkan listener untuk tombol edit.
     *
     * @param listener ActionListener yang akan ditambahkan.
     */
    public void addEditBookListener(java.awt.event.ActionListener listener) {
        editButton.addActionListener(listener);
    }

    /**
     * Menambahkan listener untuk tombol delete.
     *
     * @param listener ActionListener yang akan ditambahkan.
     */
    public void addDeleteBookListener(java.awt.event.ActionListener listener) {
        deleteButton.addActionListener(listener);
    }
}
