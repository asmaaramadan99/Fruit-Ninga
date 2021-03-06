package Models;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Orange extends Fruit {
	public Orange() {
		this.objectType = GameObjects.Orange;
		// Commented the part below just for testing --MO2--
		try {
			this.image = ImageIO.read(new File("src/resources/fruits/orange.png"));
			this.image_left = ImageIO.read(new File("src/resources/fruits/orange_left.png"));
			this.image_right = ImageIO.read(new File("src/resources/fruits/orange_right.png"));
			this.currentView = new ImageView(SwingFXUtils.toFXImage(this.image, null));
			this.currentView.setOnMousePressed(MiscUtils.assignListener(this));
		} catch (IOException e) {
			MiscUtils.fileNotFound();
		}
	}
	 
	
}
