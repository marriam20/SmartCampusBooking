
import javax.swing.*;
import java.awt.*;

public class BookingConfirmation extends JFrame {

    public BookingConfirmation(String roomName) {

        setTitle("Booking Confirmation");
        setSize(400, 280);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel titleLabel = new JLabel(
            "Booking Confirmed!",
            JLabel.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Booking information
        JPanel detailsPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        JLabel roomLabel = new JLabel(
            "Room: " + roomName
        );

        JLabel statusLabel = new JLabel(
            "Status: Confirmed"
        );

        JLabel messageLabel = new JLabel(
            "Your room has been booked successfully."
        );

        detailsPanel.add(roomLabel);
        detailsPanel.add(statusLabel);
        detailsPanel.add(messageLabel);

        // Close button
        JButton closeButton = new JButton("Close");

        closeButton.addActionListener(e -> dispose());

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(detailsPanel, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        add(panel);
    }
}
