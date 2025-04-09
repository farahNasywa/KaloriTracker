# KaloriTracker

```markdown
🍽️ Kalori Tracker Harian

Aplikasi desktop sederhana berbasis **Java Swing** untuk mencatat dan memantau kalori harian dari makanan yang dikonsumsi. Cocok untuk tugas UTS Java Components atau penggunaan pribadi.

🛠️ Fitur Utama

- ✅ Menambahkan makanan dengan jumlah porsi dan kalori per porsi
- ✅ Total kalori otomatis dihitung
- ✅ Menyimpan data otomatis dalam file CSV berdasarkan tanggal (`YYYY-MM-DD.csv`)
- ✅ Riwayat harian multi tanggal
- ✅ Preset makanan untuk input cepat
- ✅ Catatan tambahan per makanan
- ✅ Menghapus data dari tabel
- ✅ Menampilkan total kalori harian
- ✅ (Segera) Fitur edit data makanan
- 🚧 Fitur tambahan (opsional, dalam pengembangan):
  - Notifikasi batas kalori
  - Login user sederhana
  - Grafik kalori harian
   ```

---
## 📦 Struktur Proyek

```
KaloriTracker/
├── data/
│   ├── 2025-04-09.csv
│   ├── 2025-04-10.csv
│   └── ... (file data harian)
├── KaloriTracker.java
└── README.md
```

---

## ▶️ Cara Menjalankan

1. **Pastikan sudah install Java JDK** (minimal Java 8)
2. Compile file Java:
   ```bash
   javac KaloriTracker.java
   ```
3. Jalankan aplikasi:
   ```bash
   java KaloriTracker
   ```
---

## 📁 Data Tersimpan

- Data makanan disimpan secara otomatis dalam folder `data/`
- Format file: `YYYY-MM-DD.csv`
- Format isi CSV:
  ```
  Nama,Porsi,Kalori,TotalKalori,HariIniCatatan
  Nasi,1,200,200,Makan siang
  ```

---

## 🧪 Contoh Preset Makanan

Preset bisa disesuaikan di kode Java:
```java
new String[]{"", "Nasi - 200", "Ayam - 250", "Teh Manis - 100"}
```

---

## ✏️ Catatan Penggunaan

- Pilih tanggal dari dropdown untuk melihat data sebelumnya
- Tambah makanan → otomatis tersimpan
- Preset akan mengisi otomatis nama & kalori (porsi default 1)
- Hapus untuk menghapus baris makanan dari tabel
- Total kalori langsung diperbarui

---

## ScreenShot

![Screenshot 2025-04-09 094705](https://github.com/user-attachments/assets/72c8af3c-f925-40f8-8f72-647a832734a9)

## 🧑‍💻 Author

- Nama: [Farah Nasywa & Siska Auliani]
- Tugas: UTS Java Components 2025

---

## 📃 Lisensi

MIT License. Bebas digunakan untuk tugas, pengembangan pribadi, dan belajar.
