
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jdk.nashorn.internal.objects.Global;

import java.util.Timer;

public class App extends Application {

    private Stage configWindow;
    private Group root;
    private TextField numberOfSides;
    private TextField radius;

    @Override
    public void start(Stage primaryStage) {
        createMainWindow(primaryStage);
        createInterface();
        createConfigWindow();
        this.configWindow.hide();
        primaryStage.show();
        Timer tasks = new Timer();
        tasks.schedule(new Physics(), 0, (int)GlobalConfig.REFRESH_RATE);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void createMainWindow(Stage primaryStage) {
        primaryStage.setTitle("Shape Simulator");
        Group root = new Group();
        Canvas canvas = ShapeCanvas.getInstance();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        this.root = root;
    }

    private void createConfigWindow() {
        Stage configWindow = new Stage();
        this.configWindow = configWindow;
        configWindow.setTitle("Configuration");
        GridPane uiGrid = setupConfigUIGrid();
        populateConfigUI(uiGrid);
    }
    private GridPane setupConfigUIGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(grid, 300, 275);
        this.configWindow.setScene(scene);
        return grid;
    }

    private void populateConfigUI(GridPane grid) {

        Label numberOfSides = new Label("Vertices");
        grid.add(numberOfSides, 0, 1);

        TextField inputNumberOfSides = new TextField();
        grid.add(inputNumberOfSides, 1, 1);
        this.numberOfSides = inputNumberOfSides;

        Label radius = new Label("Radius");
        grid.add(radius, 0, 2);

        TextField inputRadius = new TextField();
        grid.add(inputRadius, 1, 2);
        this.radius = inputRadius;

        Button btn = new Button();
        btn.setText("apply");
        btn.setOnAction(applyConfiguration());
        grid.add(btn, 1, 3);
    }

    private void createInterface() {
        Button btn = new Button();
        btn.setText("configure");
        btn.setOnAction(event -> configWindow.show());
        this.root.getChildren().add(btn);
    }

    private EventHandler<ActionEvent> applyConfiguration() {

        return (event) -> {
            ShapeCanvas.getInstance().shapes.removeAll(ShapeCanvas.getInstance().shapes);
            CircleConfig.getInstance().updateRadius(Integer.parseInt(radius.getCharacters().toString()));
            CircleConfig.getInstance().updateTessellationResolution(Integer.parseInt(numberOfSides.getCharacters().toString()));
            configWindow.hide();
        };
    }
}
