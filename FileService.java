class FileService implements IDataSaver {
    private String filePath;
    private DefaultTableModel model;
    private JLabel totalLabel;

    public FileService(String filePath, DefaultTableModel model, JLabel totalLabel) {
        this.filePath = filePath;
        this.model = model;
        this.totalLabel = totalLabel;
    }

    @Override
    public void simpanDataCSV() {
        try {
            Files.createDirectories(Paths.get("data"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    writer.write(model.getValueAt(i, j).toString());
                    if (j != model.getColumnCount() - 1) writer.write(",");
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        try {
            File file = new File(filePath);
            if (!file.exists()) return;

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            int totalKalori = 0;
            model.setRowCount(0);
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    model.addRow(new Object[]{parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4]});
                    totalKalori += Integer.parseInt(parts[3]);
                }
            }
            reader.close();
            totalLabel.setText("Total Kalori: " + totalKalori);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
