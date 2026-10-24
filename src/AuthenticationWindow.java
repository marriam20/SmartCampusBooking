import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuthenticationWindow extends JFrame implements ActionListener {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;

    public AuthenticationWindow() {
        setTitle("Campus Login");
        setSize(300, 180); //  window size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new GridLayout(3, 2, 8, 8));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        //username
        add(new JLabel("Username:"));
        userField = new JTextField();
        add(userField);

        // Row 2: Password
        add(new JLabel("Password:"));
        passField = new JPasswordField();
        add(passField);

        // Login 
        add(new JLabel("")); 
        loginBtn = new JButton("Login");
        loginBtn.addActionListener(this);
        add(loginBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userField.getText().trim();
        String pass = new String(passField.getPassword());

        if (user.equals("student") && pass.equals("password123")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            dispose();
            SwingUtilities.invokeLater(() -> new MainDashboard().setVisible(true));
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials! (Try: student / password123)");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AuthenticationWindow().setVisible(true));
    }
}