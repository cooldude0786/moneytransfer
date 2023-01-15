import miniProject.*;
import javax.swing.*;
import java.awt.Color;
import java.time.LocalDate;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

public class menu extends JFrame implements ActionListener {
    private JLabel luName, uName, uId, lBalanceAmount, BalanceAmount, date, ldate;
    private JButton Balance, History, SendMoney, Logout;
    private DefaultListModel<String> userEnterName = new DefaultListModel<>();
    private JList<String> userEnterJList = new JList<>(userEnterName);
    private int id = 0, uAge;
    private int fAmount;
    public String uEmial, ccCode;
    LocalDate dateObj = LocalDate.now();
    JScrollPane scrollpane = new JScrollPane(userEnterJList);

    public void setccCode() {
        id = new dbc().getId(uEmial);
        String temp = "";
        for (char c : uEmial.toCharArray()) {
            char dot = '.';
            if (c == dot || c == '@')
                break;
            else
                temp += String.valueOf(c);
        }
        ccCode = temp + temp.length();
        System.out.println(ccCode);
    }

    menu(String ccode, String Email) {
        uEmial = Email;
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
        Balance = new JButton("Balance");
        History = new JButton("History");
        SendMoney = new JButton("Send Money");
        Logout = new JButton("Logout");
        luName = new JLabel("Name:");
        uName = new JLabel("Khizar Shaikh");
        uId = new JLabel(uEmial);
        lBalanceAmount = new JLabel("Balance :");
        BalanceAmount = new JLabel("0");
        date = new JLabel("Date:");
        ldate = new JLabel(dateObj.toString());
    }

    private void styleCom() {
        luName.setBounds(10, 50, 100, 50);
        luName.setFont(new java.awt.Font("Trebuchet MS", 0, 25));

        uName.setBounds(85, 50, 200, 50);
        uName.setFont(new java.awt.Font("Trebuchet MS", 0, 25));

        date.setBounds(300, 50, 70, 50);
        date.setFont(new java.awt.Font("Trebuchet MS", 0, 25));

        ldate.setBounds(360, 51, 100, 50);
        ldate.setFont(new java.awt.Font("Trebuchet MS", 0, 20));
        uId.setBounds(300, 1, 200, 50);

        lBalanceAmount.setBounds(10, 110, 110, 50);
        lBalanceAmount.setFont(new java.awt.Font("Trebuchet MS", 0, 25));

        BalanceAmount.setBounds(120, 110, 250, 50);
        BalanceAmount.setFont(new java.awt.Font("Trebuchet MS", 0, 25));

        SendMoney.setBounds(310, 550, 150, 50);
        History.setBounds(310, 600, 150, 50);
        Balance.setBounds(20, 550, 150, 50);
        Logout.setBounds(20, 600, 150, 50);

        userEnterJList.setLayoutOrientation(JList.VERTICAL);

        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setBounds(45, 253, 390, 180);
        scrollpane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        scrollpane.getVerticalScrollBar().setBackground(Color.black);
        scrollpane.getVerticalScrollBar().setForeground(Color.blue);
        scrollpane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
    }

    private void addCom() {
        add(luName);
        add(uName);
        add(uId);
        add(Balance);
        add(History);
        add(SendMoney);
        add(Logout);
        add(BalanceAmount);
        add(lBalanceAmount);
        add(date);
        add(ldate);
        Balance.addActionListener(this);
        History.addActionListener(this);
        SendMoney.addActionListener(this);
        Logout.addActionListener(this);
        addto(scrollpane);
        setBalance();
    }

    private void setBalance() {
        setccCode();
        System.out.println("Ccode is here =>" + ccCode);
        int temp = new dbc().showBal(ccCode);
        BalanceAmount.setText(Integer.toString(temp));
    }

    private void addto(Object a) {
        add((Component) a, 0);
    }

    boolean checkAmount(String Amount) {
        try {
            fAmount = Integer.parseUnsignedInt(Amount);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == Balance) {
            JTextField amount = new JTextField();
            int optionCheck = JOptionPane.showConfirmDialog(null, amount, "Enter Amount",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            amount.requestFocus();
            if (optionCheck != 0 || amount.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Empty Error!");
                return;
            }
            if (checkAmount(amount.getText())) {
                JOptionPane.showMessageDialog(null, "Enter Integer Values!");
                return;
            }
            fAmount = Integer.parseInt(amount.getText());
            JPasswordField pf = new JPasswordField();
            int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (okCxl == 0) {
                // System.out.println(0);
                if (new dbc().matchId(uEmial, String.valueOf(pf.getPassword()), fAmount, ccCode, id)) {
                    JOptionPane.showMessageDialog(null, "Update Balance");
                    setBalance();
                    getAllDetail();
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Password!");
                }
            }
        }
        if (source == History) {
            getAllDetail();
        }
    }

    private void getAllDetail() {
        ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
        userEnterName.clear();
        temp = new dbc().getTransacDetail(ccCode);
        String temp1 = "ID   Remark     From                     Debit      Credit     Balance \n ";
        userEnterName.addElement(temp1);
        temp1 = "";
        System.out.println(temp.size());
        for (ArrayList<String> a : temp) {
            temp1 += a.get(0) + "     " + Crop(a.get(1).toLowerCase()) + "     "
                    + Crop(a.get(2).toLowerCase()) + "     ";
            temp1 += "     " + (a.get(4)) + "       " + (a.get(5)) + "       "
                    + (a.get(6));
            userEnterName.addElement(temp1);
            temp1 = "";
        }
    }

    private String Crop(String a) {
        String rturn = "";
        if (a.length() < 12) {
            return a;
        }
        for (int i = 0; i < 12; i++) {
            if (i == 0)
                rturn += a.toUpperCase().toCharArray()[i];
            else
                rturn += a.toCharArray()[i];
        }
        if (a.length() < 12) {
            return rturn;
        } else {
            rturn += "....";
            return rturn;
        }
    }

    public static void main(String[] args) {
        new menu("khizarshaikh292216", "khizarshaikh2922@gmail.com");
    }
}
