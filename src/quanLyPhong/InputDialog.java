package quanLyPhong;

import javax.swing.*;
import java.awt.*;

public class InputDialog {
    public static void showElectricityInputDialog(String roomNumber, Room room) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Nhập chỉ số điện nước cho phòng " + roomNumber);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(20, 25));
        panel.add(label);
        panel.add(inputField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Nhập Chỉ Số Điện Nước", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String input = inputField.getText();
            if (!input.isEmpty()) {
                try {
                    int electricityIndex = Integer.parseInt(input);
                    room.setElectricityIndex(electricityIndex);
                    room.setPaymentAmount(electricityIndex ); // Ví dụ: Mỗi chỉ số là 5K
                    JOptionPane.showMessageDialog(null, "Dữ liệu đã được lưu.", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

}
