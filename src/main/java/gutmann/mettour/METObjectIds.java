package gutmann.mettour;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class METObjectIds
{
    int total;
    ArrayList<Integer> objectIDs;

    public int getObjectId(int index)
    {
        return objectIDs.get(index);
    }
}
