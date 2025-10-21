import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // URL koneksi ke PostgreSQL
    private static final String URL = "jdbc:postgresql://localhost:5432/transaksi_db";
    
    // Username PostgreSQL (biasanya 'postgres')
    private static final String USER = "postgres";
    
    // Password PostgreSQL kamu — ganti sesuai password di laptopmu
    private static final String PASSWORD = "masryoo21";  // contoh: "admin" atau "12345"

    // Method untuk mendapatkan koneksi
    public static Connection getConnection() throws SQLException {
        try {
            // Load driver PostgreSQL (opsional di Java 8+, tapi tetap aman buat jaga-jaga)
            Class.forName("org.postgresql.Driver");
            // Return koneksi aktif
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException
        ("Driver PostgreSQL tidak ditemukan! Pastikan library JDBC sudah ditambahkan.", e);
        } } }
