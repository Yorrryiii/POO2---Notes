package imagesCollection;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageCreator {
    private int numPrimitives;
    private Color[] colors;
    private int width;
    private int height;

    public ImageCreator(int numPrimitives, Color[] colors, int width, int height) {
        this.numPrimitives = numPrimitives;
        this.colors = colors;
        this.width = width;
        this.height = height;
    }

    public void createImage(String imagePath) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        Random random = new Random();
        for (int i = 0; i < numPrimitives; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int primitiveType = random.nextInt(2);

            if (primitiveType == 0) {
                int size = random.nextInt(100) + 1;
                Color color = colors[random.nextInt(colors.length)];
                Rectangle2D rectangle = new Rectangle2D.Double(x, y, size, size);
                graphics.setColor(color);
                graphics.fill(rectangle);
            } else {
                int radius = random.nextInt(50) + 1;
                Color color = colors[random.nextInt(colors.length)];
                Ellipse2D ellipse = new Ellipse2D.Double(x, y, radius, radius);
                graphics.setColor(color);
                graphics.fill(ellipse);
            }
        }

        ImageIO.write(image, "png", new File(imagePath));
    }
}

