# 🖥📚 TUGAS BASIS DATA LANJUT – Implementasi Transaksi & Stored Procedure

*Implementasi Java JDBC untuk Transaksi dan Stored Procedure pada PostgreSQL (Java Swing GUI)*

---

## 📄 Studi Kasus
Proyek ini dibuat untuk memenuhi tugas praktikum *Basis Data Lanjut*.  
Aplikasi ini mengimplementasikan koneksi antara *Java (JDBC)* dengan *PostgreSQL* untuk mengelola transaksi dan stored procedure, mencakup fitur *tambah saldo, kurangi saldo, serta transfer saldo antar akun* menggunakan GUI berbasis *Java Swing*.

Proyek dibagi menjadi dua bagian utama:
- ⚙ *Transaksi JDBC* → menggunakan commit dan rollback untuk memastikan integritas data.
- 🧩 *Stored Procedure App* → memanggil prosedur PostgreSQL menggunakan CallableStatement untuk eksekusi otomatis operasi saldo.

---

## 🧩 Konsep yang Diterapkan
### 🟣 Transaction :
- Menggunakan commit dan rollback untuk menjaga konsistensi data.
- Memastikan saldo pengirim mencukupi sebelum transfer dilakukan.
- Menggunakan PreparedStatement untuk update saldo.

### 🔵 Stored Procedure :
- Membuat prosedur tambah_saldo, kurangi_saldo, dan transfer_saldo di PostgreSQL.
- Menggunakan CallableStatement untuk mengeksekusi prosedur dari Java.
- Mengelola logika bisnis langsung di tingkat database agar efisien.

---

## 📂 Struktur Project
📦 BasisDataLanjut_JDBC_Transaksi_StoredProcedure

 ┣ 📁 TransactionApp
 
 ┃ ┣ DBConnection.java
 
 ┃ ┣ TransactionManager.java
 
 ┃ ┗ MainApp.java
 
 ┣ 📁 StoredProcedureApp
 
 ┃ ┣ DBConnection.java
 
 ┃ ┗ StoredProcedureApp.java
 
 ┗ README.md

---



## 🖼 Tampilan TransactionApp :
- Output Form GUI TransactionApp
<img width="479" height="481" alt="image" src="https://github.com/user-attachments/assets/5a9b49f6-155e-4778-9c72-76db7ea18521" />




## 🖼 Tampilan StoredProcedureApp : 
- Output Form GUI StoredProcedureApp
<img width="479" height="481" alt="image" src="https://github.com/user-attachments/assets/679af682-c666-4d61-bca1-2cc987565863" />


---

Laporan PDF : 

[Stored Procedure.pdf](https://github.com/user-attachments/files/23012889/Stored.Procedure.pdf)

[Transaction.pdf](https://github.com/user-attachments/files/23012891/Transaction.pdf)


## 👨‍💻 Author
*Ryo Satriagung Hidayat*  
NIM: 09010624015  
Program Studi: Sistem Informasi  
Universitas Islam Negeri Sunan Ampel Surabaya – 2025
