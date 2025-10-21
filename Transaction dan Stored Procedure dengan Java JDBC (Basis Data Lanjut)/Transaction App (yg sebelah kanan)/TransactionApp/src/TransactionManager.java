import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionManager {

    public static void transferSaldo(int pengirimId, int penerimaId, double jumlah) throws SQLException {
        Connection conn = null;
        PreparedStatement psUpdatePengirim = null;
        PreparedStatement psUpdatePenerima = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // mulai transaksi

            // cek saldo pengirim cukup atau enggak
            if (!cekSaldoCukup(conn, pengirimId, jumlah)) {
                throw new SQLException("Saldo pengirim tidak mencukupi.");
            }

            // update saldo pengirim
            String sqlUpdatePengirim = "UPDATE akun SET saldo = saldo - ? WHERE id = ?";
            psUpdatePengirim = conn.prepareStatement(sqlUpdatePengirim);
            psUpdatePengirim.setDouble(1, jumlah);
            psUpdatePengirim.setInt(2, pengirimId);
            psUpdatePengirim.executeUpdate();

            // update saldo penerima
            String sqlUpdatePenerima = "UPDATE akun SET saldo = saldo + ? WHERE id = ?";
            psUpdatePenerima = conn.prepareStatement(sqlUpdatePenerima);
            psUpdatePenerima.setDouble(1, jumlah);
            psUpdatePenerima.setInt(2, penerimaId);
            psUpdatePenerima.executeUpdate();

            conn.commit(); // kalau semua berhasil
        } catch (SQLException e) {
            if (conn != null) conn.rollback(); // rollback kalau gagal
            throw e;
        } finally {
            if (psUpdatePengirim != null) psUpdatePengirim.close();
            if (psUpdatePenerima != null) psUpdatePenerima.close();
            if (conn != null) conn.close();
        }
    }

    private static boolean cekSaldoCukup(Connection conn, int pengirimId,
        double jumlah) throws SQLException {
        String sql = "SELECT saldo FROM akun WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, pengirimId);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            double saldo = rs.getDouble("saldo");
            return saldo >= jumlah;
        }
        return false;
    }
}
