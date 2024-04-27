import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/*
 * Resources:
 * https://stackoverflow.com/questions/6810581/how-to-center-the-text-in-a-jlabel
 */

public class ATM extends JFrame {
    public static void main(String[] args) {
        // Setting up the frame
        JFrame frame = new JFrame("ATM");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);

        // The panels (pages)
        JPanel langPanel = new JPanel(new GridLayout(2, 2));
        JPanel passwordPanel = new JPanel(new GridLayout(3, 1));
        JPanel mainPanel = new JPanel(new GridLayout(2, 2));
        JPanel withdrawPanel = new JPanel(new GridLayout(2, 2));
        JPanel passwordChangePanel = new JPanel(new GridLayout(3, 1));
        JPanel cardToCardPanel = new JPanel(new GridLayout(5, 1));
        JPanel balancePanel = new JPanel(new GridLayout(1, 1));
        JPanel resultPanel = new JPanel(new GridLayout(1, 1));

        frame.add(langPanel);

        // Language panel content
        langPanel.add(new JLabel("Choose language", SwingConstants.CENTER));
        langPanel.add(new JLabel("زبان خود را انتخاب کنید", SwingConstants.CENTER));
        langPanel.add(new JButton("English"));

        JButton langBtnFa = new JButton("فارسی");
        langBtnFa.addActionListener(e -> goToNextPanel(frame, langPanel, passwordPanel));
        langPanel.add(langBtnFa);

        // Password panel content
        passwordPanel.add(new JLabel("رمز خود را وارد کنید", SwingConstants.CENTER));
        passwordPanel.add(new JTextField());

        JButton passwordSubmitBtn = new JButton("ثبت");
        passwordSubmitBtn.addActionListener(e -> goToNextPanel(frame, passwordPanel, mainPanel));
        passwordPanel.add(passwordSubmitBtn);

        // Main panel content
        JButton withdrawBtn = new JButton("برداشت وجه");
        withdrawBtn.addActionListener(e -> goToNextPanel(frame, mainPanel, withdrawPanel));
        mainPanel.add(withdrawBtn);

        JButton passwordChangeBtn = new JButton("تغییر رمز");
        passwordChangeBtn.addActionListener(e -> goToNextPanel(frame, mainPanel, passwordChangePanel));
        mainPanel.add(passwordChangeBtn);

        JButton cardToCardBtn = new JButton("کارت به کارت");
        cardToCardBtn.addActionListener(e -> goToNextPanel(frame, mainPanel, cardToCardPanel));
        mainPanel.add(cardToCardBtn);

        JButton balanceBtn = new JButton("اعلام موجودی");
        balanceBtn.addActionListener(e -> goToNextPanel(frame, mainPanel, balancePanel));
        mainPanel.add(balanceBtn);

        // Withdraw panel content
        JButton tempBtn;
        for (int i = 0; i < 4; i++) {
            tempBtn = new JButton(String.valueOf((i + 1) * 500000));
            tempBtn.addActionListener(e -> goToNextPanel(frame, withdrawPanel, resultPanel));
            withdrawPanel.add(tempBtn);
        }

        // Password change panel content
        passwordChangePanel.add(new JLabel("رمز جدید را وارد کنید:", SwingConstants.CENTER));
        passwordChangePanel.add(new JTextField());

        JButton passwordChangeSubmitBtn = new JButton("ثبت");
        passwordChangeSubmitBtn.addActionListener(e -> goToNextPanel(frame, passwordChangePanel, resultPanel));
        passwordChangePanel.add(passwordChangeSubmitBtn);

        // Card to card panel content
        cardToCardPanel.add(new JLabel("مبلغ مورد نظر را وارد کنید:", SwingConstants.CENTER));
        cardToCardPanel.add(new JTextField());
        cardToCardPanel.add(new JLabel("شماره کارت مقصد را وارد کنید:", SwingConstants.CENTER));
        cardToCardPanel.add(new JTextField());

        JButton cardToCardSubmitBtn = new JButton("ثبت");
        cardToCardSubmitBtn.addActionListener(e -> goToNextPanel(frame, cardToCardPanel, resultPanel));
        cardToCardPanel.add(cardToCardSubmitBtn);

        // Balance panel content
        balancePanel.add(new JLabel("موجودی حساب شما: " + ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE) + " ریال", SwingConstants.CENTER));

        // Result panel content
        resultPanel.add(new JLabel("عملیات با موفقیت انجام شد!", SwingConstants.CENTER));

        frame.revalidate();
        frame.repaint();
    }

    private static void goToNextPanel(Frame frame, JPanel oldPanel, JPanel newPanel) {
        frame.remove(oldPanel);
        frame.add(newPanel);
        frame.revalidate();
        frame.repaint();
    }
}
