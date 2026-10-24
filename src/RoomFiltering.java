import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RoomFiltering extends JFrame {

    private JComboBox<String> roomTypeBox;
    private DefaultListModel<String> roomListModel;
    private JList<String> roomList;

    private ArrayList<String[]> rooms = new ArrayList<>();

    public RoomFiltering() {

        setTitle("Smart Campus Booking - Find Rooms");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sample room data
        rooms.add(new String[]{"Study Room 1", "Study Room", "Available"});
        rooms.add(new String[]{"Study Room 2", "Study Room", "Booked"});
        rooms.add(new String[]{"Meeting Room 1", "Meeting Room", "Available"});
        rooms.add(new String[]{"Meeting Room 2", "Meeting Room", "Available"});
        rooms.add(new String[]{"Computer Lab 1", "Computer Lab", "Booked"});

        // Main panel
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Room type filter
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

        JScrollPane scrollPane = new JScrollPane(roomList);

        // Filter button
        JButton filterButton = new JButton("Filter Rooms");

        filterButton.addActionListener(e -> filterRooms());

        // Layout
        panel.add(filterPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(filterButton, BorderLayout.SOUTH);

        add(panel);

        // Display all rooms initially
        filterRooms();
    }

    private void filterRooms() {

        roomListModel.clear();

        String selectedType = (String) roomTypeBox.getSelectedItem();

        for (String[] room : rooms) {

            if (selectedType.equals("All Rooms")
                    || room[1].equals(selectedType)) {

                roomListModel.addElement(
                    room[0] + " | " + room[1] + " | " + room[2]
                );
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RoomFiltering().setVisible(true);
        });
    }
}