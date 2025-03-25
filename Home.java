import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {

    private JButton length, mass, volume, time, temperature, days, groupMember;

    public static void main(String[] args) {
     new Home(); 
    }

    public Home() {
        // Set up the JFrame
        setTitle("Converter project ");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Create a title label with decoration
        JLabel titleLabel = new JLabel("UNIT CONVERTER PROJECT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Larger font size
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue text color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10)); // Add padding around the title
        mainPanel.add(titleLabel, BorderLayout.NORTH); // Add title to the top

        // Create a grid panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // Add spacing between buttons

        // Initialize buttons
        length = new JButton("Length Converter");
        mass = new JButton("Mass Converter");
        volume = new JButton("Volume Converter");
        time = new JButton("Time Converter");
        temperature = new JButton("Temperature Converter");
        days = new JButton("Days Converter");

        // Add buttons to the button panel
        buttonPanel.add(length);
        buttonPanel.add(mass);
        buttonPanel.add(volume);
        buttonPanel.add(time);
        buttonPanel.add(temperature);
        buttonPanel.add(days);

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Create a panel for the "Group Members" button
        JPanel groupPanel = new JPanel();
        groupPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add margin from the top

        // Add the groupPanel to the main panel at the bottom
        mainPanel.add(groupPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);

        // Action Listeners to open respective converter windows and close Home window
        length.addActionListener(e -> {
            dispose(); // Close Home window
            new Length();
        });

        mass.addActionListener(e -> {
            dispose();
            new Weight();
        });

        volume.addActionListener(e -> {
            dispose();
            new VolumeConverter();
        });

        time.addActionListener(e -> {
            dispose();
            new Time();
        });

        temperature.addActionListener(e -> {
            dispose();
            new Temperature();
        });

        days.addActionListener(e -> {
            dispose();
            new Days();
        });


        setVisible(true);
    }
}
