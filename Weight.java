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

// WeightConverter class extending Converter
class WeightConverter extends Converter {

    public WeightConverter() {
        super(new String[]{"grams", "milligrams", "metric tons", "kilograms", "pounds"});
    }

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        double valueInGrams;
        switch (fromUnit.toLowerCase()) {
            case "grams":
                valueInGrams = value;
                break;
            case "milligrams":
                valueInGrams = value * 0.001;
                break;
            case "metric tons":
                valueInGrams = value * 1_000_000;
                break;
            case "kilograms":
                valueInGrams = value * 1_000;
                break;
            case "pounds":
                valueInGrams = value * 453.592;
                break;
            default:
                throw new IllegalArgumentException("Invalid 'from' unit");
        }

        switch (toUnit.toLowerCase()) {
            case "grams":
                return valueInGrams;
            case "milligrams":
                return valueInGrams / 0.001;
            case "metric tons":
                return valueInGrams / 1_000_000;
            case "kilograms":
                return valueInGrams / 1_000;
            case "pounds":
                return valueInGrams / 453.592;
            default:
                throw new IllegalArgumentException("Invalid 'to' unit");
        }
    }
}

// Main Weight class extending JFrame
public class Weight extends JFrame {

    private JTextField valueField;
    private JComboBox<String> fromUnitCombo;
    private JComboBox<String> toUnitCombo;
    private JTextField resultField;
    private WeightConverter weightConverter;

    public Weight() {
        this.weightConverter = new WeightConverter();
        initializeUI();
    }

    private void initializeUI() {
        // Set up the JFrame
        setTitle("Weight Unit Converter");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        // Initialize components
        valueField = new JTextField(10);
        fromUnitCombo = new JComboBox<>(weightConverter.getUnits()); // Using getter
        toUnitCombo = new JComboBox<>(weightConverter.getUnits()); // Using getter
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

            double result = weightConverter.convert(value, fromUnit, toUnit);
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

    // Getter for weightConverter
    public WeightConverter getWeightConverter() {
        return weightConverter;
    }

    // Setter for weightConverter
    public void setWeightConverter(WeightConverter weightConverter) {
        this.weightConverter = weightConverter;
    }

    public static void main(String[] args) {
        new Weight();
    }
}
