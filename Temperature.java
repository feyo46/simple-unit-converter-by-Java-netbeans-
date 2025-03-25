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

class TemperatureConverter extends Converter {
    public TemperatureConverter() {
        super(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
    }

    @Override
    public double convert(double value, String fromUnit, String toUnit) {
        double valueInCelsius;

        switch (fromUnit.toLowerCase()) {
            case "celsius":
                valueInCelsius = value;
                break;
            case "fahrenheit":
                valueInCelsius = (value - 32) * 5 / 9;
                break;
            case "kelvin":
                valueInCelsius = value - 273.15;
                break;
            default:
                throw new IllegalArgumentException("Invalid 'from' unit");
        }

        switch (toUnit.toLowerCase()) {
            case "celsius":
                return valueInCelsius;
            case "fahrenheit":
                return (valueInCelsius * 9 / 5) + 32;
            case "kelvin":
                return valueInCelsius + 273.15;
            default:
                throw new IllegalArgumentException("Invalid 'to' unit");
        }
    }
}

public class Temperature extends JFrame {
    private JTextField valueField;
    private JComboBox<String> fromUnitCombo;
    private JComboBox<String> toUnitCombo;
    private JTextField resultField;
    private TemperatureConverter temperatureConverter;

    public Temperature() {
        this.temperatureConverter = new TemperatureConverter();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Temperature Unit Converter");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 5, 5));

        valueField = new JTextField(10);
        fromUnitCombo = new JComboBox<>(temperatureConverter.getUnits());
        toUnitCombo = new JComboBox<>(temperatureConverter.getUnits());
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

            double result = temperatureConverter.convert(value, fromUnit, toUnit);
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
        new Temperature();
    }
}
