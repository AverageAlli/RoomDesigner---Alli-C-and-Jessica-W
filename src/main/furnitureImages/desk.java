package furnitureImages;

import edu.bsu.cs.Main;
import javafx.scene.image.Image;

import java.io.InputStream;

public class desk {
    public static void main(String[] args) {
        InputStream inputStream = Main.class.getResourceAsStream("/furnitureImages/Desk.png");
        Image image = new Image(inputStream);

    }
}
