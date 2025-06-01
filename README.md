# 🥗 KaloriTracker

Aplikasi desktop sederhana berbasis **Java Swing** untuk mencatat dan memantau kalori harian dari makanan yang dikonsumsi.

---

## 🛠️ Fitur Utama

* ✅ Menambahkan makanan (nama, porsi, kalori per porsi, dan catatan)
* ✅ Total kalori otomatis dihitung
* ✅ Penyimpanan data otomatis ke file CSV harian (`YYYY-MM-DD.csv`)
* ✅ Riwayat kalori harian multi tanggal
* ✅ Preset makanan untuk input cepat
* ✅ Catatan tambahan per makanan
* ✅ Menghapus baris data dari tabel
* ✅ Menampilkan total kalori harian

---

## 📦 Struktur Proyek

```
KaloriTracker/
├── data/
│   ├── 2025-04-09.csv
│   ├── 2025-04-10.csv
│   └── ... (data harian)
├── KaloriTracker.java
└── README.md
```

---

## ▶️ Cara Menjalankan

1. Pastikan sudah menginstal **Java JDK** (minimal versi 8)
2. Kompilasi file Java:

   ```bash
   javac KaloriTracker.java
   ```
3. Jalankan aplikasi:

   ```bash
   java KaloriTracker
   ```

---

## 📁 Format Data CSV

* Disimpan otomatis di folder `data/`
* Format file: `YYYY-MM-DD.csv`
* Format isi:

  ```
  Nama,Porsi,Kalori,TotalKalori,HariIniCatatan
  Nasi,1,200,200,Makan siang
  ```

---

## 🧪 Preset Makanan

Preset bisa disesuaikan di kode Java:

```java
new String[]{"", "Nasi - 200", "Ayam - 250", "Teh Manis - 100"};
```

---

## ✏️ Panduan Penggunaan

* Pilih tanggal dari dropdown untuk melihat data sebelumnya
* Input makanan → otomatis tersimpan ke file CSV
* Preset mengisi otomatis nama & kalori (porsi default = 1)
* Tombol *Hapus* menghapus baris makanan dari tabel
* Total kalori diperbarui secara otomatis

---

## 🖼️ Screenshot

![Screenshot](https://github.com/user-attachments/assets/72c8af3c-f925-40f8-8f72-647a832734a9)

---

## 🧱 Arsitektur Sistem

### ✅ Interface (Layanan Disediakan)

| Interface         | Fungsi                                                |
| ----------------- | ----------------------------------------------------- |
| `IDataSaver`      | Menyimpan dan memuat data makanan ke/dari file CSV    |
| `ITableManager`   | Mengakses/mengatur isi tabel dari luar komponen tabel |
| `IPresetProvider` | Mengambil daftar preset makanan                       |
| `ITableUpdater`   | Memperbarui tampilan tabel kalori                     |

### 🧩 Kelas dan Komponennya

| Kelas           | Peran                             | Menggunakan / Menyediakan Interface                         |
| --------------- | --------------------------------- | ----------------------------------------------------------- |
| `KaloriTracker` | JFrame utama aplikasi             | Memerlukan `IDataSaver`, `IPresetProvider`, `ITableManager` |
| `InputPanel`    | Panel input makanan pengguna      | Memerlukan `IPresetProvider`, `ITableUpdater`               |
| `TablePanel`    | Menampilkan tabel makanan         | Menyediakan `ITableUpdater`, `ITableManager`                |
| `FileService`   | Menyimpan/memuat data ke file     | Mengimplementasikan `IDataSaver`                            |
| `PresetService` | Menyediakan daftar preset makanan | Mengimplementasikan `IPresetProvider`                       |

---

## 🔌 Provide & Require Interface Diagram

![Provide Require Interface](https://github.com/user-attachments/assets/928d9e78-dd75-49ab-8202-8a045ad10a53)

---

## 📐 UML Class Diagram

![UML Diagram](https://github.com/user-attachments/assets/1bbb4018-7693-4892-afef-571f9ca5e6ec)

---

## 🔁 Workflow Pengembangan

### 01. Component Identification

* Use Case Diagram
* Use Case Descriptions
* System Interface
* Business Concept Model
* Business Type Model
* Business Interfaces
* Component Architecture

### 02. Component Interaction

* Component Interaction Diagram

### 03. Component Specification

* Interface Specification & OCL

---

## 👩‍💻 Pengembang

* **Farah Nasywa** 2208107010051
* **Siska Auliani** 2208107010065

📚 Tugas: UTS Java Components 2025

---

## 📄 Lisensi

**MIT License**
Bebas digunakan untuk keperluan tugas, pengembangan pribadi, dan pembelajaran.

---

## 🖥️ Presentasi Proyek

[Slide](https://www.canva.com/design/DAGkIYbqhSg/YclBqyjxIxn3rxpzZcX6gA/edit?utm_content=DAGkIYbqhSg&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
I
[Video](https://www.youtube.com/watch?v=NAkMgZiPYrI)

---

