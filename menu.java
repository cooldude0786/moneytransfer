
import miniProject.*;
import javax.swing.*;

public class menu extends JFrame {
    private JLabel uName, uId;

    menu() {
        setTitle("Home Page");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(500, 80, 500, 700);
        setResizable(false);
        compInitailize();
        styleCom();
        addCom();
    }

    private void compInitailize() {
        uName = new JLabel("khizar");
        uId = new JLabel("sd");
    }

    private void styleCom() {
        uName.setBounds(10, 10, 100, 50);
        uId.setBounds(400, 10, 70, 50);
    }

    private void addCom() {
        add(uName);
        add(uId);
    }

    public static void main(String[] args) {
        new menu();
    }
}
