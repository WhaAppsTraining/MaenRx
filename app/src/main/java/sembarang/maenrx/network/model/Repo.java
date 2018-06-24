package sembarang.maenrx.network.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author hendrawd on 12/15/16
 */

// TODO 17 buat model Repo
public class Repo {
    @SerializedName("id")
    public long id;
    @SerializedName("name")
    public String name;
    @SerializedName("full_name")
    public String fullName;
}