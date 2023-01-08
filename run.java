import java.awt.Color;
import java.awt.Window;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import miniProject.*;
import java.awt.event.*;

public class run {
    run() {
        Signup a = new Signup();
        a.setobj(a);
        a.setVisible(true);
    }

    public static void main(String args[]) {
        new run();
    }
}

class Signup extends JFrame implements ActionListener {

    Object CurrentObj;
    Object LoginObj;

    public void setobj(Object obj) {
        this.CurrentObj = obj;// setting the current obj
    }

    public Object getobj() {
        return this.CurrentObj; // getting current obj
    }

    public void setloginObj(Object obj) {
        this.LoginObj = obj; // setting Login frame obj
    }

    public Object getloginObj() {
        return this.LoginObj;// Getting Login Page Obj
    }

    Signup() {
        FrameInitailzes();
    }

    protected String getData() {
        return this.uEmial;
    }

    // dbc conn = new dbc();
    private String uName, uEmial, uCity = null;
    char[] uPassword, uComPassword;
    private int uAge = 0;
    private JTextField tfName = new JTextField();
    private JTextField tfEmail = new JTextField();
    private JTextField tfCity = new JTextField();
    private JPasswordField tfPassword = new JPasswordField();
    private JPasswordField tfComPassword = new JPasswordField();
    private JTextField tfAge = new JTextField();
    private int yAlignForLabel = 40, yAlignForTextFeild = 40, xAlignForLabel = 30, xAlignForTextFeild = 160;
    private JLabel lName = new JLabel("Name");
    private JLabel lEmail = new JLabel("Email");
    private JLabel lCity = new JLabel("City");
    private JLabel lAge = new JLabel("Age");
    private JLabel lPassword = new JLabel("Password");
    private JLabel lComPassword = new JLabel("Comfirm");
    private JButton Submit = new JButton("Enroll");
    private JPanel p1 = new JPanel();
    private JLabel star_for_Name = new JLabel("*");
    private JLabel star_for_Email = new JLabel("*");
    private JLabel star_for_Pass = new JLabel("*");
    private JLabel star_for_Age = new JLabel("*");
    private JLabel errorTextName = new JLabel();
    private JLabel errorTextEmail = new JLabel();
    private JLabel errorTextPass = new JLabel();
    private JLabel errorTextComPass = new JLabel();
    private JLabel errorTextAge = new JLabel();
    private JLabel alreadyAcc = new JLabel("Already Have Account ?");

    void FrameInitailzes() {
        setTitle("Sign Up");
        // setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(500, 80, 500, 700);
        setResizable(false);
        setsizeComp();
        addComp();
        spinner();
    }

    private void spinner() {
        p1.setBounds(190, 400, 150, 120);
        JLabel imageicon = new JLabel();
        imageicon.setIcon(new ImageIcon("src\\img4.gif"));
        p1.add(imageicon);
        add(p1);
        spinnerShow(false);
    }

    private void spinnerShow(boolean val) {
        this.p1.setVisible(val);
    }

    void addComp() {
        add(lName);
        add(lEmail);
        add(lPassword);
        add(lComPassword);
        add(lAge);
        add(lCity);
        add(tfName);
        add(tfEmail);
        add(tfPassword);
        add(tfComPassword);
        add(tfAge);
        add(tfCity);
        add(Submit);
        add(star_for_Name);
        add(star_for_Email);
        add(star_for_Pass);
        add(star_for_Age);
        add(errorTextName);
        add(errorTextEmail);
        add(errorTextPass);
        add(errorTextComPass);
        add(errorTextAge);
        add(alreadyAcc);
    }

    // name Initailze ends
    void setsizeComp() {
        styleForLabel();
        styleForTextFeild();
        styleForButton();
    }

    void styleForButton() {
        Submit.setFont(new java.awt.Font("Trebuchet MS", 0, 24));
        Submit.setBounds(350, 580, 100, 40);
        Submit.setBackground(Color.WHITE);
        Submit.addActionListener(this);

    }

    void styleForTextFeild() {
        tfName.setBounds(xAlignForTextFeild, (yAlignForTextFeild), 250, 30);
        tfEmail.setBounds(xAlignForTextFeild, (yAlignForTextFeild + 60), 250, 30);
        tfPassword.setBounds(xAlignForTextFeild, (yAlignForTextFeild + 60 * 2), 250, 30);
        tfComPassword.setBounds(xAlignForTextFeild, (yAlignForTextFeild + 60 * 3), 250, 30);
        tfAge.setBounds(xAlignForTextFeild, (yAlignForTextFeild + 60 * 4), 250, 30);
        tfCity.setBounds(xAlignForTextFeild, (yAlignForTextFeild + 60 * 5), 250, 30);
    }

    void styleForLabel() {

        lName.setFont(new java.awt.Font("Trebuchet MS", 0, 24));
        lName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lName.setBounds(xAlignForLabel, yAlignForLabel + 0, 65, 30);
        // for_label_Name
        star_for_Name.setForeground(Color.RED);
        star_for_Name.setFont(new java.awt.Font("Trebuchet MS", 0, 25));
        star_for_Name.setBounds(xAlignForLabel + 70, yAlignForLabel + 0, 10, 30);
        // for star Name
        errorTextName.setForeground(Color.RED);
        errorTextName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorTextName.setFont(new java.awt.Font("Trebuchet MS", 1, 10));
        errorTextName.setBounds(xAlignForTextFeild, yAlignForTextFeild + 20, 250, 30);
        // Error text for Name

        lEmail.setFont(new java.awt.Font("Trebuchet MS", 0, 24));
        lEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lEmail.setBounds(xAlignForLabel, (yAlignForLabel + 60), 65, 30);
        // for_label_Email
        star_for_Email.setForeground(Color.RED);
        star_for_Email.setFont(new java.awt.Font("Trebuchet MS", 0, 25));
        star_for_Email.setBounds(xAlignForLabel + 70, yAlignForLabel + 60, 10, 30);
        // for star
        errorTextEmail.setForeground(Color.RED);
        errorTextEmail.setFont(new java.awt.Font("Trebuchet MS", 1, 10));
        errorTextEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorTextEmail.setBounds(xAlignForTextFeild, yAlignForTextFeild + 60 + 20, 250, 30);
        // Error text for Name

        lPassword.setFont(new java.awt.Font("Trebuchet MS", 0, 24));
        lPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lPassword.setBounds(xAlignForLabel, (yAlignForLabel + 60 * 2), 100, 30);
        // for_label_Password
        star_for_Pass.setForeground(Color.RED);
        star_for_Pass.setFont(new java.awt.Font("Trebuchet MS", 0, 25));
        star_for_Pass.setBounds(xAlignForLabel + 105, yAlignForLabel + 60 * 2, 10, 30);
        // for star
        errorTextPass.setForeground(Color.RED);
        errorTextPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorTextPass.setFont(new java.awt.Font("Trebuchet MS", 1, 10));
        errorTextPass.setBounds(xAlignForTextFeild, yAlignForTextFeild + 60 * 2 + 20, 250, 30);
        // Error text for Name

        lComPassword.setFont(new java.awt.Font("Trebuchet MS", 0, 24));
        lComPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lComPassword.setBounds(xAlignForLabel, (yAlignForLabel + 60 * 3), 99, 30); //
        // for_label_Com_Password
        errorTextComPass.setForeground(Color.RED);
        errorTextComPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorTextComPass.setFont(new java.awt.Font("Trebuchet MS", 1, 10));
        errorTextComPass.setBounds(xAlignForTextFeild, yAlignForTextFeild + 60 * 3 + 20, 250, 30);
        // Error text for Name

        lAge.setFont(new java.awt.Font("Trebuchet MS", 0, 24));
        lAge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lAge.setBounds(xAlignForLabel, (yAlignForLabel + 60 * 4), 45, 30);
        // //for_label_Age
        star_for_Age.setForeground(Color.RED);
        star_for_Age.setFont(new java.awt.Font("Trebuchet MS", 0, 25));
        star_for_Age.setBounds(xAlignForLabel + 45, yAlignForLabel + 60 * 4, 10, 30);
        // star for Age
        errorTextAge.setForeground(Color.RED);
        errorTextAge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorTextAge.setFont(new java.awt.Font("Trebuchet MS", 1, 10));
        errorTextAge.setBounds(xAlignForTextFeild, yAlignForTextFeild + 60 * 4 + 20, 250, 30);
        // Error text for Name

        lCity.setFont(new java.awt.Font("Trebuchet MS", 0, 24));
        lCity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lCity.setBounds(xAlignForLabel, (yAlignForLabel + 60 * 5), 45, 30);
        // //for_label_City

        alreadyAcc.setFont(new java.awt.Font("Trebuchet MS", 0, 24));
        alreadyAcc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alreadyAcc.setBounds(10, 520, 480, 40);
        alreadyAcc.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                jumpToLogin();
            }
        });
    }

    public void jumpToLogin() {
        spinnerShow(false);
        setVisible(false);
        if (getloginObj() == null) {
            LoginPage obj = new LoginPage(getobj());
            obj.setVisible(true);
            setloginObj(obj);
        } else {
            ((Window) LoginObj).setVisible(true);
        }
    }

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile(
                "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
                Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    private void InsertData() {
        dbc conn = new dbc();
        if (conn.Insert_User_Sign(this.uName, this.uEmial, this.uCity, String.valueOf(this.uPassword),
                this.uAge) == 1001) {
            tfEmail.setText("");
            setErrorTf(tfEmail, errorTextEmail, "Email Already Taken");
            spinnerShow(false);
        } else {
            jumpToLogin();
        }
    }

    private boolean CheckInput() {
        int count = 0;
        remErrorTf(tfName, errorTextName);
        if (this.uName.length() == 0) {
            setErrorTf(tfName, errorTextName, "Empty Name!!");
            count++;
        }
        remErrorTf(tfEmail, errorTextEmail);
        if (this.uEmial.length() == 0) {
            setErrorTf(tfEmail, errorTextEmail, "Inavlid Email");
            count++;
        } else {
            if (isEmailValid(this.uEmial)) {
                // System.out.println("Yes");
            } else {
                setErrorTf(tfEmail, errorTextEmail, "Inavlid Email");
                count++;
            }
        }
        if (this.uPassword.length > 6) {
            if (Arrays.equals(uPassword, uComPassword)) {
                remErrorTf(tfComPassword, errorTextComPass);
                remErrorTf(tfPassword, errorTextPass);
            } else {
                setErrorTf(tfPassword, errorTextPass, "Doesn't Match wiht Password");
                setErrorTf(tfComPassword, errorTextComPass, "Doesn't Match wiht Password");
                count++;
            }
        } else {
            // setErrorTf(tfComPassword);
            setErrorTf(tfPassword, errorTextPass, "More than 6 Character");
            count++;
        }
        remErrorTf(tfAge, errorTextAge);
        if (tfAge.getText().length() >= 0) {
            try {
                this.uAge = Integer.parseInt(tfAge.getText());
                if (uAge < 18) {
                    setErrorTf(tfAge, errorTextAge, "More Than 18");
                    count++;
                }
            } catch (NumberFormatException e) {
                System.out.println("type cath ahppen");
                setErrorTf(tfAge, errorTextAge, "Enter Numeric value");
                // TODO: handle exception
                count++;
            }
        }
        if (count > 0)
            return false;
        else
            return true;
    }

    private void setErrorTf(JTextField tf, JLabel label, String msg) {
        label.setText(msg);
        tf.setBorder(BorderFactory.createLineBorder(Color.red, 1, false));
    }

    private void remErrorTf(JTextField tf, JLabel label) {
        tf.setBorder(null);
        label.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        spinnerShow(true);
        Object source = e.getSource();
        if (source == Submit) {
            this.uName = tfName.getText().trim();
            this.uEmial = tfEmail.getText().trim();
            this.uPassword = tfPassword.getPassword();
            this.uComPassword = tfComPassword.getPassword();
            this.uCity = tfCity.getText().trim();
            if (CheckInput()) {
                InsertData();
            } else {
                spinnerShow(false);
            }
        }
    }

    // insert data ends

}
