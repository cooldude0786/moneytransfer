import java.awt.Color;
import java.awt.event.*;
import java.util.regex.Pattern;
import miniProject.dbc;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {
    private JLabel lEmail, lPassword, lErrorText;
    private JTextField tfEmail = new JTextField();
    private JPasswordField tfPassword = new JPasswordField("");
    private int padding = 70;
    private JButton login = new JButton("Login"), forgotP = new JButton("Forgot Password");

    LoginPage() {
        setTitle("Loginsfed Up");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(500, 80, 500, 700);
        setResizable(false);
        compInitailize();
        styleCom();
        addCom();
        // add(lEmail);
    }

    private void compInitailize() {
        lEmail = new JLabel("Email");
        lPassword = new JLabel("Password");
        lErrorText = new JLabel();
        login.addActionListener(this);
        forgotP.addActionListener(this);
    }

    private void styleCom() {
        lEmail.setBounds(10, 20 + padding, 460, 50);
        lEmail.setFont(new java.awt.Font("Trebuchet MS", 0, 25));

        lPassword.setBounds(10, 150 + padding, 460, 50);
        lPassword.setFont(new java.awt.Font("Trebuchet MS", 0, 25));

        tfEmail.setBounds(90, 70 + padding, 320, 40);
        tfEmail.setFont(new java.awt.Font("Trebuchet MS", 0, 20));

        tfPassword.setBounds(90, 200 + padding, 320, 40);
        tfPassword.setFont(new java.awt.Font("Trebuchet MS", 0, 15));

        forgotP.setBounds(150, 242 + padding, 200, 10);
        forgotP.setBorderPainted(false);
        forgotP.setBackground(Color.white);

        login.setBounds(150, 520, 200, 50);
        login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        login.setFont(new java.awt.Font("Trebuchet MS", 0, 25));
        login.setBackground(Color.WHITE);

        lEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lErrorText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tfPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tfEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    }

    private void addCom() {
        add(lEmail);
        add(lPassword);
        add(tfPassword);
        add(tfEmail);
        add(forgotP);
        add(login);
        add(lErrorText);
    }

    private void setErrorTf(JTextField tf, JLabel label, String msg, int x) {
        lErrorText.setBounds(0, x, 500, 15);
        label.setText(msg);
        tf.setBorder(BorderFactory.createLineBorder(Color.red, 1, false));
    }

    private void remErrorTf(JTextField tf, JLabel label) {
        tf.setBorder(null);
        label.setText("");
    }

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile(
                "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
                Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == login) {
            remErrorTf(tfEmail, lErrorText);
            if (tfEmail.getText().trim().length() <= 0) {
                setErrorTf(tfEmail, lErrorText, "Empty Email", tfEmail.getY() + 42);
                return;
            }
            if (!isEmailValid(tfEmail.getText().trim())) {
                setErrorTf(tfEmail, lErrorText, "In Valid Email", tfEmail.getY() + 42);
                return;
            }
            if (new dbc().checkEmail(tfEmail.getText().trim().toString()) != 1002) {
                setErrorTf(tfEmail, lErrorText, "Not here", tfEmail.getY() + 42);
                return;
            }
        }
        // System.out.println(tfEmail.getX() + " " + tfPassword.getAlignmentY());
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}