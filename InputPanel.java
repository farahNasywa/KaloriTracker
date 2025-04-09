class InputPanel extends JPanel {
    JTextField namaField, porsiField, kaloriField, catatanField;
    JComboBox<String> presetBox;

    public InputPanel(IPresetProvider presetProvider, ITableUpdater updater) {
        // Implementasi UI bisa dipisah, tetap kosong sesuai permintaan
        // presetProvider.isiPreset(); bisa dipanggil ketika preset dipilih
        // updater.updateTabel(); bisa dipanggil setelah tambah data
    }
}
