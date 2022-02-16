package oop.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import oop.*;

import java.util.ArrayList;


public class App extends Application {

    GridPane grid = new GridPane();
    int widthOfField = 60;
    int heightOfField = 60;
    int width = 13, height = 13;
    RectangularMap map;
    Vector2d lowerLeft, upperRight;
    CreatureLeft kowalski;
    CreatureRight nowak;
    Thread bombThread;
    Scene scene, scene1, scene2;
    Label ending1 = new Label("Player 1");
    Label ending2 = new Label("Player 2");
    Label ending = new Label(" won the game");
    HBox end = new HBox(ending1, ending);
    Stage window;


    int lives1 = 3, lives2 = 3;

    // tworzy obramowanie
    private void makeGrid(int X, int Y) {
        for (int y = 0; y < Y; y++) {
            this.grid.getRowConstraints().add(new RowConstraints(heightOfField));
        }
        for (int x = 0; x < X; x++){
            this.grid.getColumnConstraints().add(new ColumnConstraints(widthOfField));
        }
    }

    private void gBR(int x, int y){
        Vector2d vector = new Vector2d(x,y);
        GuiElementBox guiElementBox;
        if (!map.isOccupied(vector)) guiElementBox = new GuiElementBox(null);
        else guiElementBox = new GuiElementBox((IMapElement) map.objectAt(new Vector2d(x,y)).get(0));
        VBox vBox = guiElementBox.gGR();
        this.grid.add(vBox,x,y,1,1);
    }


    private void printMap(){
        grid.setGridLinesVisible(false);
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);

        int x = upperRight.x;
        int y = upperRight.y;

        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y ; j++) {
                gBR(i,j);
            }
        }
    }
    int nOfLightRock;
    private void startGame(){
        map = new RectangularMap(width,height, nOfLightRock);
        kowalski = new CreatureLeft(map);
        nowak = new CreatureRight(map);
        ArrayList<IMapElement> a = new ArrayList<>();
        a.add(kowalski);
        ArrayList<IMapElement> b = new ArrayList<>();
        b.add(nowak);
        map.mapElements.put(new Vector2d(1,1), a);
        map.mapElements.put(new Vector2d(width-2,height-2), b);
        lowerLeft = map.getLowerLeft();
        upperRight = map.getUpperRight();
    }


    public void printMap2(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                App.this.printMap();
            }
        });
    }

    public void newGame(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                startGame();
            }
        });
    }


    public void changeLiveScore(AbstractCreature creature){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (creature instanceof CreatureLeft){
                    lives1--;
                    if (lives1==0){
                        end.getChildren().remove(0);
                        end.getChildren().add(0,ending2);
                        end.setAlignment(Pos.CENTER);
                        scene2 = new Scene(end, 400, 400);
                        window.setScene(scene2);
                    }
                    firstPlayerLives.getChildren().remove(1);
                    firstPlayerLives.getChildren().add(new Label(Integer.toString(lives1)));
                } else {
                    lives2--;
                    if (lives2==0){
                        end.setAlignment(Pos.CENTER);
                        scene2 = new Scene(end, 400, 400);
                        window.setScene(scene2);
                    }
                    secondPlayerLives.getChildren().remove(1);
                    secondPlayerLives.getChildren().add(new Label(Integer.toString(lives2)));
                }
            }
        });
    }


    HBox firstPlayerLives;
    HBox secondPlayerLives;
    Label player1Lives;
    Label player2Lives;


    private void entryScene(Stage window){
        window.setTitle("Settings");
        Label line0 = new Label("Map properties");

        Label line1 = new Label("Number of light rocks:");
        TextField n = new TextField("50");
        HBox first =new HBox(40);
        first.setAlignment(Pos.TOP_CENTER);
        first.getChildren().addAll(line1, n);

        Label line2 = new Label("Bomb delay (s):");
        TextField n2 = new TextField("1");
        HBox second =new HBox(40);
        second.setAlignment(Pos.TOP_CENTER);
        second.getChildren().addAll(line2, n2);


        Button button = new Button("Start game");
        button.setOnAction(e -> {
            window.setTitle("Game");
            int nolr = Integer.parseInt(n.getText());
            if (nolr<0 || nolr >86){
                throw new ArithmeticException("Number of light rocks must be between 0 and 86");
            }
            nOfLightRock =nolr;
            int t = Integer.parseInt(n2.getText())*1000;
            if (t<0){
                throw new ArithmeticException("Bomb delay must be greater than or equal to zero");
            }
            Bomb.setDelay(t);
            window.setScene(scene);
            startGame();
            makeGrid(width,height);
            printMap();
            grid.setGridLinesVisible(true);
            grid.setAlignment(Pos.CENTER);
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(first,second, button);
        vBox.setAlignment(Pos.CENTER);
        scene1 = new Scene(vBox, 400, 400);
        window.setScene(scene1);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        Label player1Name = new Label("Player 1");
        player1Lives = new Label("Lives: ");
        firstPlayerLives = new HBox(player1Lives, new Label(Integer.toString(lives1)));
        VBox player1 = new VBox(player1Name ,firstPlayerLives);

        Label player2Name = new Label("Player 2");
        player2Lives = new Label("Lives: ");
        secondPlayerLives = new HBox(player2Lives, new Label(Integer.toString(lives2)));
        VBox player2= new VBox(player2Name ,secondPlayerLives);

        HBox hBox = new HBox(player1, player2);
        hBox.setSpacing(700);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(hBox,grid);
        scene = new Scene(vBox, 900, 900);

//        primaryStage.setScene(scene);
        entryScene(primaryStage);


        scene.setOnKeyReleased(e -> {
            Vector2d vector = kowalski.getPosition();
            if (e.getCode() == KeyCode.W) {
                kowalski.move(MapDirection.NORTH);
                printMap();
            }
            else if (e.getCode() == KeyCode.S) {
                kowalski.move(MapDirection.SOUTH);
                printMap();
            }
            else if (e.getCode() == KeyCode.A) {
                kowalski.move(MapDirection.WEST);
                printMap();
            }
            else if (e.getCode() == KeyCode.D) {
                kowalski.move(MapDirection.EAST);
                printMap();
            }
            else if (e.getCode() == KeyCode.Z) {
                if (kowalski.isCanPlantTheBomb()){
                    kowalski.setCanPlantTheBomb(false);
                    Vector2d position = kowalski.getPosition();
                    PlantTheBomb plantTheBomb1 = new PlantTheBomb(this, position, map, kowalski, nowak);
                    bombThread = new Thread(plantTheBomb1);
                    bombThread.start();
                }
            }
            else if (e.getCode() == KeyCode.M) {
                if (nowak.isCanPlantTheBomb()){
                    nowak.setCanPlantTheBomb(false);
                    Vector2d position = nowak.getPosition();
                    PlantTheBomb plantTheBomb = new PlantTheBomb(this, position, map, nowak, kowalski);
                    bombThread = new Thread(plantTheBomb);
                    bombThread.start();
                }
            }

            else if (e.getCode() == KeyCode.UP) {
                nowak.move(MapDirection.NORTH);
                printMap();
            }
            else if (e.getCode() == KeyCode.DOWN) {
                nowak.move(MapDirection.SOUTH);
                printMap();
            }
            else if (e.getCode() == KeyCode.LEFT) {
                nowak.move(MapDirection.WEST);
                printMap();
            }
            else if (e.getCode() == KeyCode.RIGHT) {
                nowak.move(MapDirection.EAST);
                printMap();
            }
            else if (e.getCode() == KeyCode.G) {
                startGame();
                printMap();
            }
        });

        primaryStage.show();
    }
}
