package org.example.kalorieberegner;

//Imports needed that allows using all the controls that is used.
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    //Variables.
    private int kilo;
    private int minutes;
    private double metValue = 0;
    private double result;

    //Connecting the FXML controls.
    @FXML
    private ChoiceBox<String> activity;

    @FXML
    private TextField timeInMinutes;

    @FXML
    private Spinner<Integer> kg;

    @FXML
    private Label showCalories;

    //Method to initialize the choicebox and the spinner.
    @FXML
    public void initialize() {

        //Input options for the activity control, where the set value is "Hvile".
        activity.getItems().addAll("Løb, roligt (9,5km/t)", "Løb, moderat (12km/t)", "Løb, hurtigt (13,5km/t)", "Landevejscykling", "Let styrketræning", "Hård styrketræning", "Hvile");
        activity.setValue("Hvile");

        //Sets up the value range and default value for the spinner.
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 300, 80);
        kg.setValueFactory(valueFactory);
    }

    //Method that calculates calories burned at a certain activity.
    @FXML
    protected void calculateCalories() {

        //Gets the values from the controls.
        kilo = kg.getValue();
        minutes = Integer.parseInt(timeInMinutes.getText());

        //The metValue is assigned to the specific activities.
        switch (activity.getValue()){
            case "Hvile":
                metValue = 1.3;
                break;
            case "Løb, roligt (9,5km/t)":
                metValue = 9.8;
                break;
            case "Løb, moderat (12km/t)":
                metValue = 11.5;
                break;
            case "Løb, hurtigt (13,5km/t)":
                metValue = 12.3;
                break;
            case "Landevejscykling":
                metValue = 6.8;
                break;
            case "Let styrketræning":
                metValue = 3.5;
                break;
            case "Hård styrketræning":
                metValue = 6;
                break;
        }

        //Calculates and shows the calories burned during the chosen activity, time and weight.
        //The calculation is based on:https://iform.dk/traening/test-hvad-forbraender-min-traening, and may not be the most accurate, because it does not cover pulse, bodycomposition etc.
        result = metValue * kilo * minutes/60;
        showCalories.setText(String.format("%.0f kcal", result));
    }
}