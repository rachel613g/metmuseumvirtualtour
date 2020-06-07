package gutmann.mettour;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class METObjectImageView
{
    ImageIcon imageIcon;

    public void setImage(String imageURLString) throws MalformedURLException, NoImageException
    {
        if(imageURLString == "" | imageURLString == null)
        {
            throw new NoImageException();
        }

        URL url = new URL(imageURLString);
        imageIcon = new ImageIcon(url);
    }

    public ImageIcon getImageIcon()
    {

        return imageIcon;
    }
}
