package gutmann.mettour;

public class METObjectData
{
    private int objectID;
    private String primaryImage;
    private String title;
    private String culture;
    private String artistDisplayName;

    public int getObjectID()
    {
        return objectID;
    }

    public String getPrimaryImage()
    {
        return primaryImage;
    }

    public String getTitle()
    {
        return title;
    }

    public String getCulture()
    {
        return culture;
    }

    public String getArtistDisplayName()
    {
        return artistDisplayName;
    }

    public String getObjectDate()
    {
        return objectDate;
    }

    private String objectDate;
}
