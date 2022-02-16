package oop.gui;
import oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GuiElementBox {
    private IMapElement mapElement;
    ImageView imageView;

    public GuiElementBox (IMapElement mapElement){
        this.mapElement = mapElement;
    }


    public VBox gGR(){
        if (this.mapElement==null)
            return new VBox();

        if (this.mapElement instanceof IMapElement) {
            imageView = new ImageView(this.mapElement.getImage());
        }


        imageView.setFitWidth(50);
        imageView.setFitHeight(50);


        VBox vBox = new VBox(imageView);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }
}
