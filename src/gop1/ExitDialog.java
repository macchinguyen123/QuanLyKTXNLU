package gop1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ExitDialog extends JDialog {
    private JButton btnThoat, btnHuy;

    public ExitDialog(JFrame parent) {
        super(parent, "Xác nhận thoát", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(217, 217, 217));
        setContentPane(mainPanel);

        JLabel messageLabel = new JLabel("Bạn có muốn thoát?");
        messageLabel.setFont(new Font("Inter", Font.BOLD, 25));
        messageLabel.setBounds(50, 40, 300, 30);
        mainPanel.add(messageLabel);

        btnThoat = new JButton("Thoát");
        btnThoat.setBounds(60, 120, 125, 40);
        btnThoat.setFont(new Font("Inter", Font.BOLD, 25));
        btnThoat.setForeground(Color.WHITE);
        btnThoat.setBackground(new Color(94, 212, 245));
        mainPanel.add(btnThoat);

        btnHuy = new JButton("Hủy");
        btnHuy.setBounds(215, 120, 125, 40);
        btnHuy.setFont(new Font("Inter", Font.BOLD, 25));
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setBackground(new Color(94, 212, 245));
        mainPanel.add(btnHuy);
    }

    public void setThoatButtonListener(ActionListener listener) {
        btnThoat.addActionListener(listener);
    }

    public void setHuyButtonListener(ActionListener listener) {
        btnHuy.addActionListener(listener);
    }
}
