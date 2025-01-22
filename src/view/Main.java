package view;

import quanLyPhong.Model;
import sinhVienDangO.Controller;
import sinhVienDangO.PasswordView;

public class Main {
    public static void main(String[] args) {
        Model combinedModel = new Model();
        PasswordView passwordView = new PasswordView();
        new Controller(combinedModel, passwordView);
        passwordView.setVisible(true);
    }
}
