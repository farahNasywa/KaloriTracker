class TablePanel extends JPanel implements ITableManager, ITableUpdater {
    JTable table;
    DefaultTableModel tableModel;
    JLabel totalLabel;
    int totalKalori = 0;

    public TablePanel(JLabel totalLabel) {
        this.totalLabel = totalLabel;
        tableModel = new DefaultTableModel(new String[]{"Nama", "Porsi", "Kalori", "Total", "Catatan"}, 0);
        table = new JTable(tableModel);
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    @Override
    public void tambahData() {
        // Implementasi ditangani dari luar
    }

    @Override
    public void hapusData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int total = (int) tableModel.getValueAt(selectedRow, 3);
            totalKalori -= total;
            tableModel.removeRow(selectedRow);
            totalLabel.setText("Total Kalori: " + totalKalori);
        }
    }

    @Override
    public void updateTabel() {
        totalKalori = 0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            totalKalori += (int) tableModel.getValueAt(i, 3);
        }
        totalLabel.setText("Total Kalori: " + totalKalori);
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
