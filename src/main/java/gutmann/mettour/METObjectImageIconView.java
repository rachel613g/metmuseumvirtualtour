package gutmann.mettour;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class METObjectImageIconView extends ImageIcon
{
    private ImageIcon imageIcon;
    private URL url;

    public void setImage(String imageURLString, int imageWidth, int imageHeight) throws MalformedURLException, NoImageException
    {
        if(imageURLString.equals("")| imageURLString.equals(null))
        {
            throw new NoImageException();
        }

        url = new URL(imageURLString);
        imageIcon = new ImageIcon(url);
        Image image = imageIcon.getImage();
        image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        imageIcon.setImage(image);
    }

    public ImageIcon getImageIcon()
    {
        return imageIcon;
    }

}
