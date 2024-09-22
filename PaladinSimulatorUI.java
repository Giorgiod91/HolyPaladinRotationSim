import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaladinSimulatorUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        Paladin paladin = new Paladin(); // Your Paladin class

        Label damageLabel = new Label("Total Damage: 0");
        Button rotationButton = new Button("Start Basic Rotation");

        rotationButton.setOnAction(e -> {
            paladin.basicRotationForOneTarget();
            damageLabel.setText("Total Damage: " + paladin.OverAllDamage());
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(damageLabel, rotationButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Paladin Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
