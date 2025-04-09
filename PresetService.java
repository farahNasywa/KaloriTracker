class PresetService implements IPresetProvider {
    private JTextField namaField, kaloriField, porsiField;
    private JComboBox<String> presetBox;

    public PresetService(JTextField namaField, JTextField kaloriField, JTextField porsiField, JComboBox<String> presetBox) {
        this.namaField = namaField;
        this.kaloriField = kaloriField;
        this.porsiField = porsiField;
        this.presetBox = presetBox;
    }

    @Override
    public void isiPreset() {
        String preset = (String) presetBox.getSelectedItem();
        if (preset != null && !preset.isEmpty()) {
            String[] parts = preset.split(" - ");
            namaField.setText(parts[0]);
            kaloriField.setText(parts[1]);
            porsiField.setText("1");
        }
    }
}
