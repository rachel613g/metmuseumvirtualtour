package gutmann.mettour;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class METObjectImageView
{
    ImageIcon imageIcon;

    public void setImage(String primaryImage) throws MalformedURLException
    {
        URL url = new URL(primaryImage);
        imageIcon = new ImageIcon(url);
    }

    public ImageIcon getImageIcon()
    {
        return imageIcon;
    }
}
