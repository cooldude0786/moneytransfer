import miniProject.*;
import javax.swing.*;
import java.awt.Color;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;

public class menu extends JFrame implements ActionListener {
    private JLabel luName, uName, uId, lBalanceAmount, BalanceAmount;
    private JButton Balance, History, SendMoney, Logout;
    private DefaultListModel<String> userEnterName = new DefaultListModel<>();
    private JList<String> userEnterJList = new JList<>(userEnterName);
    private int id = 1;
    JScrollPane scrollpane = new JScrollPane(userEnterJList);

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
        Balance = new JButton("Balance");
        History = new JButton("History");
        SendMoney = new JButton("Send Money");
        Logout = new JButton("Logout");
        luName = new JLabel("Name:");
        uName = new JLabel("Khizar Shaikh");
        uId = new JLabel("khizarshiakh2922@gmail.com");
        lBalanceAmount = new JLabel("Balance :");
        BalanceAmount = new JLabel("0");
    }

    private void styleCom() {
        luName.setBounds(10, 50, 100, 50);
        luName.setFont(new java.awt.Font("Trebuchet MS", 0, 25));

        uName.setBounds(125, 50, 200, 50);
        uName.setFont(new java.awt.Font("Trebuchet MS", 0, 25));

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
        scrollpane.setBounds(80, 253, 320, 180);
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
        Balance.addActionListener(this);
        History.addActionListener(this);
        SendMoney.addActionListener(this);
        Logout.addActionListener(this);
        addto(scrollpane);
        int temp = new dbc().showBal(Integer.toBinaryString(id));
        BalanceAmount.setText(Integer.toString(temp));
    }

    private void addto(Object a) {
        add((Component) a, 0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == Balance) {
            JTextField amount = new JTextField();
            int optionCheck = JOptionPane.showConfirmDialog(null, amount, "Enter Amount",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (optionCheck != 0 || amount.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Empty Error!");
                return;
            }
            JPasswordField pf = new JPasswordField();
            int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (okCxl == 0) {
                // System.out.println(0);
                if (new dbc().matchId(id, String.valueOf(pf.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "Update Balance");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed!");
                }
            }
            System.out.println(pf.getPassword());
        }
    }

    public static void main(String[] args) {
        new menu();
    }
}
