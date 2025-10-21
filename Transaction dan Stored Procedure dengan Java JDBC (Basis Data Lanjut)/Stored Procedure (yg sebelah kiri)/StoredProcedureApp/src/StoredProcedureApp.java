import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StoredProcedureApp extends JFrame {
    private JTextField txtAkunId, txtJumlah, txtPengirimId, txtPenerimaId;
    private JButton btnTambahSaldo, btnKurangiSaldo, btnTransferSaldo;
    private JTextArea txtResult;

    public StoredProcedureApp() {
        setTitle("Aplikasi Manajemen Transaksi");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblAkunId = new JLabel("ID Akun:");
        lblAkunId.setBounds(20, 20, 100, 25);
        panel.add(lblAkunId);

        txtAkunId = new JTextField();
        txtAkunId.setBounds(150, 20, 200, 25);
        panel.add(txtAkunId);

        JLabel lblJumlah = new JLabel("Jumlah:");
        lblJumlah.setBounds(20, 60, 100, 25);
        panel.add(lblJumlah);

        txtJumlah = new JTextField();
        txtJumlah.setBounds(150, 60, 200, 25);
        panel.add(txtJumlah);

        btnTambahSaldo = new JButton("Tambah Saldo");
        btnTambahSaldo.setBounds(50, 100, 150, 30);
        panel.add(btnTambahSaldo);

        btnKurangiSaldo = new JButton("Kurangi Saldo");
        btnKurangiSaldo.setBounds(210, 100, 150, 30);
        panel.add(btnKurangiSaldo);

        JLabel lblPengirimId = new JLabel("ID Pengirim:");
        lblPengirimId.setBounds(20, 150, 100, 25);
        panel.add(lblPengirimId);

        txtPengirimId = new JTextField();
        txtPengirimId.setBounds(150, 150, 200, 25);
        panel.add(txtPengirimId);

        JLabel lblPenerimaId = new JLabel("ID Penerima:");
        lblPenerimaId.setBounds(20, 190, 100, 25);
        panel.add(lblPenerimaId);

        txtPenerimaId = new JTextField();
        txtPenerimaId.setBounds(150, 190, 200, 25);
        panel.add(txtPenerimaId);

        btnTransferSaldo = new JButton("Transfer Saldo");
        btnTransferSaldo.setBounds(130, 230, 150, 30);
        panel.add(btnTransferSaldo);

        txtResult = new JTextArea();
        txtResult.setBounds(20, 270, 350, 70);
        txtResult.setEditable(false);
        panel.add(txtResult);

        btnTambahSaldo.addActionListener(e -> callProcedure("tambah_saldo"));
        btnKurangiSaldo.addActionListener(e -> callProcedure("kurangi_saldo"));
        btnTransferSaldo.addActionListener(e -> callProcedure("transfer_saldo"));

        add(panel);
    }

    private void callProcedure(String proc) {
        try (Connection conn = DBConnection.getConnection()) {
            CallableStatement stmt;
            switch (proc) {
                case "tambah_saldo":
                    stmt = conn.prepareCall("CALL tambah_saldo(?, ?)");
                    stmt.setInt(1, Integer.parseInt(txtAkunId.getText()));
                    stmt.setDouble(2, Double.parseDouble(txtJumlah.getText()));
                    break;
                case "kurangi_saldo":
                    stmt = conn.prepareCall("CALL kurangi_saldo(?, ?)");
                    stmt.setInt(1, Integer.parseInt(txtAkunId.getText()));
                    stmt.setDouble(2, Double.parseDouble(txtJumlah.getText()));
                    break;
                default:
                    stmt = conn.prepareCall("CALL transfer_saldo(?, ?, ?)");
                    stmt.setInt(1, Integer.parseInt(txtPengirimId.getText()));
                    stmt.setInt(2, Integer.parseInt(txtPenerimaId.getText()));
                    stmt.setDouble(3, Double.parseDouble(txtJumlah.getText()));
            }
            stmt.execute();
            txtResult.setText("Prosedur " + proc + " berhasil dijalankan.");
        } catch (SQLException ex) {
            txtResult.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StoredProcedureApp().setVisible(true));
    }
}
