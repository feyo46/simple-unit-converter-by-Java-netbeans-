import javax.swing.*;
import java.awt.*;

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

// Renamed TimeConverter to DaysConverter
class DaysConverter extends Converter {
    public DaysConverter() {
        super(new String[]{"Days", "Weeks", "Months", "Years", "Centuries"});
    }

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        double valueInDays = convertToDays(value, fromUnit);
        return convertFromDays(valueInDays, toUnit);
    }

    private double convertToDays(double value, String fromUnit) {
        switch (fromUnit.toLowerCase()) {
            case "days":
                return value;
            case "weeks":
                return value * 7;
            case "months":
                return value * 30.00;
            case "years":
                return value * 365.00;
            case "centuries":
                return value * 36525;
            default:
                throw new IllegalArgumentException("Invalid 'from' unit");
        }
    }

    private double convertFromDays(double valueInDays, String toUnit) {
        switch (toUnit.toLowerCase()) {
            case "days":
                return valueInDays;
            case "weeks":
                return valueInDays / 7;
            case "months":
                return valueInDays / 30.00;
            case "years":
                return valueInDays / 365.00;
            case "centuries":
                return valueInDays / 36525;
            default:
                throw new IllegalArgumentException("Invalid 'to' unit");
        }
    }
}

public class Days extends JFrame {
    private JTextField valueField;
    private JComboBox<String> fromUnitCombo;
    private JComboBox<String> toUnitCombo;
    private JTextField resultField;
    private DaysConverter daysConverter; // Updated to use DaysConverter

    public Days() {
        this.daysConverter = new DaysConverter();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Days Unit Converter");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        valueField = new JTextField(10);
        fromUnitCombo = new JComboBox<>(daysConverter.getUnits());
        toUnitCombo = new JComboBox<>(daysConverter.getUnits());
        resultField = new JTextField(10);
        resultField.setEditable(false);

        JButton convertButton = new JButton("Convert");
        JButton clearButton = new JButton("Clear");
        JButton homePageButton = new JButton("Return to Home Page");

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
        add(homePageButton);

        convertButton.addActionListener(e -> convert());
        clearButton.addActionListener(e -> clearFields());
        homePageButton.addActionListener(e -> returnToHome());

        setVisible(true);
    }

    private void convert() {
        try {
            double value = Double.parseDouble(valueField.getText());
            String fromUnit = (String) fromUnitCombo.getSelectedItem();
            String toUnit = (String) toUnitCombo.getSelectedItem();

            double result = daysConverter.convert(value, fromUnit, toUnit);
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
        new Home();  // Assuming Home is another frame
    }

    public static void main(String[] args) {
        new Days();
    }
}
