package view;

import business.UserController;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Array;
import java.util.ArrayList;

public class UserView extends JFrame {
    private JPanel comtainer;
    private JTabbedPane tab_menu;
    private JPanel pnl_user;
    private JTable tbl_user;
    private JScrollPane scrl_user;
    private JButton btn_user_New;
    private UserController userController;
    private DefaultTableModel mdl_user;
    private JPopupMenu user_popup ;

    public UserView() {
        this.add(comtainer);
        this.setTitle("Kullanıcı Yönetimi");
        this.setSize(500, 500);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth()) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight()) / 2;
        this.setLocation(x, y);
        this.setVisible(true);

        this.userController = new UserController();
        this.mdl_user = new DefaultTableModel();
        this.user_popup = new JPopupMenu();
        Object[] columnUser = {"ID", "AD", "Tip", "Cinsiyet", "Mail", "Şifre"};
        this.mdl_user.setColumnIdentifiers(columnUser);

        ArrayList<User> users = this.userController.findAll();
        for (User user : users) {
            Object[] row = {
                    user.getId(),
                    user.getName(),
                    user.getType(),
                    user.getGender(),
                    user.getMail(),
                    user.getPassword()
        };
            this.mdl_user.addRow(row);
        }
        this.tbl_user.setModel(this.mdl_user);
        this.tbl_user.setEnabled(false);
        this.tbl_user.getTableHeader().setReorderingAllowed(false);

        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selectedRow,selectedRow);
            }
        });

        this.user_popup.add("Güncelle").addActionListener(e -> {
            int selectedID = Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(),0).toString());
            User selectedUser = this.userController.getByID(selectedID);
            System.out.println(selectedUser);
            EditView editView = new EditView(selectedUser);
        });
        this.user_popup.add("Sil").addActionListener(e -> {
            int selectedID = Integer.parseInt(tbl_user.getValueAt(tbl_user.getSelectedRow(),0).toString());
            System.out.println(selectedID);

        });

        this.tbl_user.setComponentPopupMenu(user_popup);
        btn_user_New.addActionListener(e -> {
            EditView editView = new EditView(new User());
        });
    }
}