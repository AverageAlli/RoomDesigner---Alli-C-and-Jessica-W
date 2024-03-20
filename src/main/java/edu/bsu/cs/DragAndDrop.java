package edu.bsu.cs;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class DragAndDrop {
    public BorderPane borderPane = new BorderPane();

    public Pane createPane(int x, int y, Node[] content){
        Pane pane = new Pane(content);
        pane.setPrefSize(x, y);
        return pane;
    }

    public Parent createDraggableApp(Node[] nodeArray) {

        for(int i = 0; i<nodeArray.length; i++){
            if(nodeArray[i] != null) {
                nodeArray[i].setTranslateX(i * 75);
                nodeArray[i].setTranslateY(50);
            }
        }

        var root = createPane(800, 600, nodeArray);

        root.getChildren().forEach(this::makeDraggable);

        return root;
    }

    private double startPointX;
    private double startPointY;

    private void makeDraggable(Node node){
        node.setOnMousePressed(obj -> {
            startPointX = obj.getSceneX() - node.getTranslateX();
            startPointY = obj.getSceneY() - node.getTranslateY();

            node.setCursor(Cursor.CLOSED_HAND);
        });

        node.setOnMouseDragged(obj -> {
            node.setTranslateX(obj.getSceneX() - startPointX);
            node.setTranslateY(obj.getSceneY() - startPointY);
        });

    }


}
