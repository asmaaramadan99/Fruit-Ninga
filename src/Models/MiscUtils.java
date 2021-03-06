package Models;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class MiscUtils {
    public static double rand(double min, double max) {
        double range = max - min;
        return (Math.random() * range) + min;
    }

    public static void fileNotFound() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("File not found!");
        alert.setContentText("Oops! It looks like a file has been deleted.");
        alert.showAndWait();
        System.exit(0);
    }

    public static BufferedImage rotateRight(BufferedImage bufferedImage, double angle) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, 0, bufferedImage.getHeight());
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(bufferedImage, null);
    }

    public static BufferedImage rotateLeft(BufferedImage bufferedImage, double angle) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(-angle, bufferedImage.getWidth(), bufferedImage.getHeight());
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        return op.filter(bufferedImage, null);
    }

    public static BufferedImage concat(BufferedImage img1, BufferedImage img2, int x) {

        //do some calculate first
        int offset = -3 * x;
        int wid = img1.getWidth() + img2.getWidth() + offset;
        int height = Math.max(img1.getHeight(), img2.getHeight()) + offset;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid, height, BufferedImage.TYPE_INT_ARGB); 
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
        //draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, null, 0, 0);
        g2.drawImage(img2, null, img1.getWidth() + offset, 0);
        g2.dispose();
        return newImage;
    }
    
    public static EventHandler<javafx.scene.input.MouseEvent> assignListener(GameObject gameObject){
    	EventHandler<javafx.scene.input.MouseEvent> eventHandler=new EventHandler<javafx.scene.input.MouseEvent>() { 
            @Override 
            public void handle(javafx.scene.input.MouseEvent e) { 
               gameObject.slice();   
            } 
         };   
		return eventHandler;
    	
    }
}
