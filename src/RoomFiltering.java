
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RoomFiltering extends JFrame {

    private JComboBox<String> roomTypeBox;
    private DefaultListModel<String> roomListModel;
    private JList<String> roomList;

    // Shared room data
    private static ArrayList<String[]> rooms = new ArrayList<>();

    // Store bookings made by the user
    private static ArrayList<String> myBookings = new ArrayList<>();

    private ArrayList<String[]> displayedRooms = new ArrayList<>();

    public RoomFiltering() {

        setTitle("Smart Campus Booking - Find Rooms");
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sample room data
        if (rooms.isEmpty()) {
            rooms.add(new String[]{"Study Room 1", "Study Room", "Available"});
            rooms.add(new String[]{"Study Room 2", "Study Room", "Booked"});
            rooms.add(new String[]{"Meeting Room 1", "Meeting Room", "Available"});
            rooms.add(new String[]{"Meeting Room 2", "Meeting Room", "Available"});
            rooms.add(new String[]{"Computer Lab 1", "Computer Lab", "Booked"});
        }

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Room filter
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel filterLabel = new JLabel("Filter by room type:");

        String[] roomTypes = {
            "All Rooms",
            "Study Room",
            "Meeting Room",
            "Computer Lab"
        };

        roomTypeBox = new JComboBox<>(roomTypes);

        filterPanel.add(filterLabel);
        filterPanel.add(roomTypeBox);

        // Room list
        roomListModel = new DefaultListModel<>();
        roomList = new JList<>(roomListModel);
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(roomList);

        // Buttons
        JButton filterButton = new JButton("Filter Rooms");
        JButton bookButton = new JButton("Book Room");
        JButton viewButton = new JButton("View Bookings");
        JButton cancelButton = new JButton("Cancel Booking");

        filterButton.addActionListener(e -> filterRooms());
        bookButton.addActionListener(e -> bookRoom());
        viewButton.addActionListener(e -> viewBookings());
        cancelButton.addActionListener(e -> cancelBooking());

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        buttonPanel.add(filterButton);
        buttonPanel.add(bookButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(cancelButton);

        panel.add(filterPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        filterRooms();
    }

    // Filter rooms by type
    private void filterRooms() {

        roomListModel.clear();
        displayedRooms.clear();

        String selectedType = (String) roomTypeBox.getSelectedItem();

        for (String[] room : rooms) {

            if (selectedType.equals("All Rooms")
                    || room[1].equals(selectedType)) {

                displayedRooms.add(room);

                roomListModel.addElement(
                    room[0] + " | " + room[1] + " | " + room[2]
                );
            }
        }
    }

    // Book selected room
    private void bookRoom() {

        int selectedIndex = roomList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room first.");
            return;
        }

        String[] selectedRoom = displayedRooms.get(selectedIndex);

        if (selectedRoom[2].equals("Booked")) {
            JOptionPane.showMessageDialog(this, "This room is already booked.");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
            this,
            "Do you want to book " + selectedRoom[0] + "?",
            "Confirm Booking",
            JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {

            selectedRoom[2] = "Booked";

            // Save booking
            myBookings.add(selectedRoom[0]);

            // Show booking confirmation
            new BookingConfirmation(selectedRoom[0]).setVisible(true);

            filterRooms();
        }
    }

    // View existing bookings
    private void viewBookings() {

        if (myBookings.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "You have no bookings."
            );
            return;
        }

        StringBuilder bookingList = new StringBuilder("Your Bookings:\n\n");

        for (String booking : myBookings) {
            bookingList.append("- ").append(booking).append("\n");
        }

        JOptionPane.showMessageDialog(
            this,
            bookingList.toString(),
            "My Bookings",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Cancel an existing booking
    private void cancelBooking() {

        if (myBookings.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "You have no bookings to cancel."
            );
            return;
        }

        String selectedBooking = (String) JOptionPane.showInputDialog(
            this,
            "Select a booking to cancel:",
            "Cancel Booking",
            JOptionPane.QUESTION_MESSAGE,
            null,
            myBookings.toArray(new String[0]),
            myBookings.get(0)
        );

        if (selectedBooking == null) {
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to cancel " + selectedBooking + "?",
            "Confirm Cancellation",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            myBookings.remove(selectedBooking);

            // Update room availability
            for (String[] room : rooms) {
                if (room[0].equals(selectedBooking)) {
                    room[2] = "Available";
                    break;
                }
            }

            JOptionPane.showMessageDialog(
                this,
                "Booking cancelled successfully."
            );

            filterRooms();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new RoomFiltering().setVisible(true);
        });
    }
}
