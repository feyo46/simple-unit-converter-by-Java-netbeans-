import javax.swing.*;
import java.awt.*;

class Home extends JFrame {
    public Home() {
        setTitle("Home Page");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
} 
// Interface for conversion
interface Convertible {
    double convert(double value, String fromUnit, String toUnit);
}

// Abstract class for UnitConverter
abstract class Converter implements Convertible {
    private String[] units;

    public Converter(String[] units) {
        this.units = units;
    }

    // Getter for units
    public String[] getUnits() {
        return units;
    }

    // Setter for units
    public void setUnits(String[] units) {
        this.units = units;
    }
}

// LengthConverter class extending Converter
class LengthConverter extends Converter {

    public LengthConverter() {
        super(new String[]{"meters", "centimeters", "millimeters", "kilometers", "miles", "yards", "feet"});
    }

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        double valueInMeters;
        switch (fromUnit.toLowerCase()) {
            case "meters":
                valueInMeters = value;
                break;
            case "centimeters":
                valueInMeters = value / 100;
                break;
            case "millimeters":
                valueInMeters = value / 1000;
                break;
            case "kilometers":
                valueInMeters = value * 1000;
                break;
            case "miles":
                valueInMeters = value * 1609.34;
                break;
            case "yards":
                valueInMeters = value * 0.9144;
                break;
            case "feet":
                valueInMeters = value * 0.3048;
                break;
            default:
                throw new IllegalArgumentException("Invalid 'from' unit");
        }

        switch (toUnit.toLowerCase()) {
            case "meters":
                return valueInMeters;
            case "centimeters":
                return valueInMeters * 100;
            case "millimeters":
                return valueInMeters * 1000;
            case "kilometers":
                return valueInMeters / 1000;
            case "miles":
                return valueInMeters / 1609.34;
            case "yards":
                return valueInMeters / 0.9144;
            case "feet":
                return valueInMeters / 0.3048;
            default:
                throw new IllegalArgumentException("Invalid 'to' unit");
        }
    }
}

// Main Length class extending JFrame
public class Length extends JFrame {

    private JTextField valueField;
    private JComboBox<String> fromUnitCombo;
    private JComboBox<String> toUnitCombo;
    private JTextField resultField;
    private LengthConverter lengthConverter;

    public Length() {
        this.lengthConverter = new LengthConverter();
        initializeUI();
    }

    private void initializeUI() {
        // Set up the JFrame
        setTitle("Length Unit Converter");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        // Initialize components
        valueField = new JTextField(10);
        fromUnitCombo = new JComboBox<>(lengthConverter.getUnits()); // Using getter
        toUnitCombo = new JComboBox<>(lengthConverter.getUnits()); // Using getter
        resultField = new JTextField(10);
        resultField.setEditable(false);

        JButton convertButton = new JButton("Convert");
        JButton clearButton = new JButton("Clear");
        JButton homePage = new JButton("Return to Home Page");

        // Add components to frame
        add(new JLabel("Enter value:"));
        add(valueField);
        add(new JLabel("From unit:"));
        add(fromUnitCombo);
        add(new JLabel("To unit:"));
        add(toUnitCombo);
        add(convertButton);
        add(clearButton);
        add(new JLabel("Result:"));
        add(resultField);
        add(homePage);

        // Action listeners
        convertButton.addActionListener(e -> convert());
        clearButton.addActionListener(e -> clearFields());
        homePage.addActionListener(e -> returnToHome());

        // Display the frame
        setVisible(true);
    }

    private void convert() {
        try {
            double value = Double.parseDouble(valueField.getText());
            String fromUnit = (String) fromUnitCombo.getSelectedItem();
            String toUnit = (String) toUnitCombo.getSelectedItem();

            double result = lengthConverter.convert(value, fromUnit, toUnit);
            resultField.setText(String.format("%.2f %s", result, toUnit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        valueField.setText("");
        resultField.setText("");
        fromUnitCombo.setSelectedIndex(0);
        toUnitCombo.setSelectedIndex(0);
    }

    private void returnToHome() {
        dispose();
        new Home();
    }

    // Getter for lengthConverter
    public LengthConverter getLengthConverter() {
        return lengthConverter;
    }

    // Setter for lengthConverter
    public void setLengthConverter(LengthConverter lengthConverter) {
        this.lengthConverter = lengthConverter;
    }

    public static void main(String[] args) {
        new Length();
    }
}

