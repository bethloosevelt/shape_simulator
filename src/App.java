
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

public class App extends Application {

    private Stage configWindow;
    private Group root;
    private TextField numberOfSides;
    private TextField radius;
    private TextField screenHeightPixels;
    private TextField screenWidthPixels;
    private TextField screenDiagonalInches;
    private ComboBox planetChoice;
    private Physics physicsEngine;

    @Override
    public void start(Stage primaryStage) {
        createMainWindow(primaryStage);
        populateMainUI();
        createConfigWindow();
        this.configWindow.hide();
        primaryStage.show();
        Physics physicsEngine = new Physics();
        this.physicsEngine = physicsEngine;
        physicsEngine.start();
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

    private void populateMainUI() {
        Button configButton = new Button();
        configButton.setText("configure");
        configButton.setOnAction(event -> configWindow.show());
        this.root.getChildren().add(configButton);

        Button clearButton = new Button();
        clearButton.setText("clear");
        clearButton.setOnAction(event -> ShapeCanvas.getInstance().shapes.removeAll(ShapeCanvas.getInstance().shapes));
        clearButton.setLayoutX(80);
        this.root.getChildren().add(clearButton);


        Button shapeTypeButton = new Button();
        shapeTypeButton.setText("shape toggle");
        shapeTypeButton.setOnAction(event -> ShapeCanvas.getInstance().shapetype =
                                                !ShapeCanvas.getInstance().shapetype);
        shapeTypeButton.setLayoutX(130);
        this.root.getChildren().add(shapeTypeButton);
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
        Scene scene = new Scene(grid, 400, 275);
        this.configWindow.setScene(scene);
        return grid;
    }

    private void populateConfigUI(GridPane grid) {

        Label numberOfSides = new Label("Vertices");
        grid.add(numberOfSides, 0, 1);
        TextField inputNumberOfSides = new TextField();
        inputNumberOfSides.setText(GlobalConfig.NUMBER_SIDES_DEFAULT_TEXT);
        grid.add(inputNumberOfSides, 1, 1);
        this.numberOfSides = inputNumberOfSides;

        Label radius = new Label("Radius");
        grid.add(radius, 0, 2);
        TextField inputRadius = new TextField();
        inputRadius.setText(GlobalConfig.RADIUS_DEFAULT_TEXT);
        grid.add(inputRadius, 1, 2);
        this.radius = inputRadius;

        Label screenHeightInPx = new Label("Screen Height (px)");
        grid.add(screenHeightInPx, 0, 3);
        TextField inputScreenHeightPx = new TextField();
        inputScreenHeightPx.setText(GlobalConfig.SCREEN_HEIGHT_DEFAULT_TEXT);
        grid.add(inputScreenHeightPx, 1, 3);
        this.screenHeightPixels = inputScreenHeightPx;

        Label screenWidthInPx = new Label("Screen width (px)");
        grid.add(screenWidthInPx, 0, 4);
        TextField inputScreenWidthPx = new TextField();
        inputScreenWidthPx.setText(GlobalConfig.SCREEN_WIDTH_DEFAULT_TEXT);
        grid.add(inputScreenWidthPx, 1, 4);
        this.screenWidthPixels = inputScreenWidthPx;

        Label diagonalInches = new Label("Screen Size Diagonal (in)");
        grid.add(diagonalInches, 0, 5);
        TextField inputDiagonalInches = new TextField();
        inputDiagonalInches.setText(GlobalConfig.SCREEN_DIAGONAL_DEFAULT_TEXT);
        grid.add(inputDiagonalInches, 1, 5);
        this.screenDiagonalInches = inputDiagonalInches;

        Label planetGravity = new Label("Planet Gravity");
        grid.add(planetGravity, 0, 6);
        ComboBox planetChoice = new ComboBox(GlobalConfig.PLANETS);
        planetChoice.setValue("Earth");
        grid.add(planetChoice, 1, 6);
        this.planetChoice = planetChoice;

        Button btn = new Button();
        btn.setText("apply");
        btn.setOnAction(applyConfiguration());
        grid.add(btn, 0, 7);
    }

    void changeResolution() {
        ShapeCanvas.getInstance().diagonalScreeninInches = Double.parseDouble(screenDiagonalInches.getCharacters().toString());
        ShapeCanvas.getInstance().widthScreenInPixels = Integer.parseInt(screenWidthPixels.getCharacters().toString());
        ShapeCanvas.getInstance().heighScreenInPixels = Integer.parseInt(screenHeightPixels.getCharacters().toString());
    }

    private EventHandler<ActionEvent> applyConfiguration() {

        return (event) -> {
            changeResolution();
            physicsEngine.updateGravity(planetChoice.getValue().toString());
            CircleConfig.getInstance().updateRadius(Integer.parseInt(radius.getCharacters().toString()));
            CircleConfig.getInstance().updateTessellationResolution(Integer.parseInt(numberOfSides.getCharacters().toString()));
            configWindow.hide();
        };
    }
}
