package gop1;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Model combinedModel = new Model(); // Sử dụng class đã gộp
                PasswordView passwordView = new PasswordView();
                new Controller(combinedModel, passwordView);
                passwordView.setVisible(true);
            }
        });
    }
}