import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;

public class KaloriTracker extends JFrame {
    private JTextField namaField, porsiField, kaloriField, catatanField;
    private JLabel totalLabel;
    private JTable table;
    private DefaultTableModel tableModel;
    private int totalKalori = 0;
    private final String folderData = "data";
    private String tanggalFile = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    private String filePath = folderData + "/" + tanggalFile + ".csv";
    private JComboBox<String> presetBox;
    private JComboBox<String> tanggalCombo;

    public KaloriTracker() {
        setTitle("\uD83C\uDF7D️ Kalori Tracker Harian");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        loadTanggalList();
        loadData();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("\uD83D\uDCC5 Pilih Tanggal:"));
        tanggalCombo = new JComboBox<>();
        tanggalCombo.addItem(tanggalFile); // Menambahkan tanggal hari ini terlebih dahulu
        inputPanel.add(tanggalCombo);

        inputPanel.add(new JLabel("\uD83C\uDF74 Nama Makanan:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("\uD83D\uDD22 Jumlah Porsi:"));
        porsiField = new JTextField();
        inputPanel.add(porsiField);

        inputPanel.add(new JLabel("\uD83D\uDD25 Kalori per Porsi:"));
        kaloriField = new JTextField();
        inputPanel.add(kaloriField);

        inputPanel.add(new JLabel("\uD83D\uDCDD Catatan:"));
        catatanField = new JTextField();
        inputPanel.add(catatanField);

        inputPanel.add(new JLabel("\uD83D\uDCE6 Pilih Preset:"));
        presetBox = new JComboBox<>(new String[]{"", "Nasi - 200", "Ayam - 250", "Teh Manis - 100"});
        inputPanel.add(presetBox);

        JButton tambahBtn = new JButton("\u2795 Tambah");
        inputPanel.add(tambahBtn);
        totalLabel = new JLabel("Total Kalori: 0");
        inputPanel.add(totalLabel);

        add(inputPanel, BorderLayout.NORTH);

        // Tabel untuk menampilkan data makanan
        tableModel = new DefaultTableModel(new String[]{"Nama", "Porsi", "Kalori", "Total", "Catatan"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel bawah untuk tombol hapus
        JPanel bottomPanel = new JPanel();
        JButton hapusBtn = new JButton("\uD83D\uDDD1️ Hapus");
        bottomPanel.add(hapusBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event listener untuk tombol dan combo box
        tambahBtn.addActionListener(e -> tambahData());
        hapusBtn.addActionListener(e -> hapusData());
        presetBox.addActionListener(e -> isiPreset());
        tanggalCombo.addActionListener(e -> gantiTanggal());
    }

    // Menambahkan data ke tabel dan menyimpan ke file CSV
    private void tambahData() {
        try {
            String nama = namaField.getText();
            int porsi = Integer.parseInt(porsiField.getText());
            int kalori = Integer.parseInt(kaloriField.getText());
            String catatan = catatanField.getText();
            int total = porsi * kalori;

            tableModel.addRow(new Object[]{nama, porsi, kalori, total, catatan});
            totalKalori += total;
            totalLabel.setText("Total Kalori: " + totalKalori);
            simpanDataCSV();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid!", "\u274C Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Menghapus baris data dari tabel
    private void hapusData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int total = (int) tableModel.getValueAt(selectedRow, 3);
            totalKalori -= total;
            tableModel.removeRow(selectedRow);
            totalLabel.setText("Total Kalori: " + totalKalori);
            simpanDataCSV();
        }
    }

    // Mengisi form otomatis dari preset makanan
    private void isiPreset() {
        String item = (String) presetBox.getSelectedItem();
        if (item != null && !item.isEmpty()) {
            String[] parts = item.split(" - ");
            namaField.setText(parts[0]);
            kaloriField.setText(parts[1]);
            porsiField.setText("1");
        }
    }

    // Menyimpan semua data dari tabel ke file CSV
    private void simpanDataCSV() {
        try {
            Files.createDirectories(Paths.get(folderData));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.write(tableModel.getValueAt(i, j).toString());
                    if (j != tableModel.getColumnCount() - 1) writer.write(",");
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Memuat data dari file CSV ke tabel
    private void loadData() {
        try {
            File file = new File(filePath);
            if (!file.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    tableModel.addRow(new Object[]{parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4]});
                    totalKalori += Integer.parseInt(parts[3]);
                }
            }
            reader.close();
            totalLabel.setText("Total Kalori: " + totalKalori);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Menampilkan daftar tanggal file CSV yang valid di combo box
    private void loadTanggalList() {
        File folder = new File(folderData);
        if (!folder.exists()) return;

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));
        if (files != null) {
            Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
            for (File f : files) {
                String name = f.getName().replace(".csv", "");

                // Validasi bahwa nama file benar-benar format tanggal YYYY-MM-DD
                if (name.matches("\\d{4}-\\d{2}-\\d{2}") && !name.equals(tanggalFile)) {
                    tanggalCombo.addItem(name);
                }
            }
        }
    }

    // Ganti file data dan muat ulang sesuai tanggal yang dipilih
    private void gantiTanggal() {
        String selectedTanggal = (String) tanggalCombo.getSelectedItem();
        if (selectedTanggal != null) {
            filePath = folderData + "/" + selectedTanggal + ".csv";
            tableModel.setRowCount(0);
            totalKalori = 0;
            loadData();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KaloriTracker().setVisible(true));
    }
}