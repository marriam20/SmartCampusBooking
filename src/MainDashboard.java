
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainDashboard extends JFrame implements ActionListener {

    private JButton profileButton;
    private JButton logoutButton;

    public MainDashboard() {
        setTitle("Smart Campus - Dashboard");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getRootPane().setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel titleLabel = new JLabel("Welcome!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Dashboard buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton findRoomsButton = new JButton("Find Rooms");
        profileButton = new JButton("View User Profile");
        logoutButton = new JButton("Log Out");

        // Open room filtering window
        findRoomsButton.addActionListener(e -> {
            new RoomFiltering().setVisible(true);
        });

        profileButton.addActionListener(this);
        logoutButton.addActionListener(this);

        // Add buttons to dashboard
        buttonPanel.add(findRoomsButton);
        buttonPanel.add(profileButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == profileButton) {
            JOptionPane.showMessageDialog(
                this,
                "User Story Demo: Authentication & Dashboard active.",
                "User Profile",
                JOptionPane.INFORMATION_MESSAGE
            );
        }

        else if (e.getSource() == logoutButton) {

            int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to log out?",
                "Logout Confirmation",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                this.dispose();

                // Return to login screen
                new AuthenticationWindow().setVisible(true);
            }
        }
    }
}
