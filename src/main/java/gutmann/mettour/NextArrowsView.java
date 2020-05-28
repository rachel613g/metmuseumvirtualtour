package gutmann.mettour;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

public class NextArrowsView extends JComponent
{
    @Override
    public void paintComponents(Graphics g)
    {
        super.paintComponents(g);
        BasicArrowButton nextButton = new BasicArrowButton(BasicArrowButton.NEXT);
        BasicArrowButton prevButton = new BasicArrowButton(BasicArrowButton.PREVIOUS);
    }
}
