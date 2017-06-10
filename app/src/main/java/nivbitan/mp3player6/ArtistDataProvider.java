package nivbitan.mp3player6;

/**
 * Created by ניב on 13/03/2016.
 */
public class ArtistDataProvider
{
    private int artist_poster1_resource;
    private int artist_poster2_resource;
    private String artist_name;

    public ArtistDataProvider(int artist_poster1_resource,String artist_name,int  artist_poster2_resource)
    {
        this.setArtist_poster1_resource(artist_poster1_resource);
        this.setArtist_poster2_resource(artist_poster2_resource);
        this.setArtist_name(artist_name);
    }

    public int getArtist_poster1_resource() {
        return artist_poster1_resource;
    }

    public void setArtist_poster1_resource(int artist_poster1_resource) {
        this.artist_poster1_resource = artist_poster1_resource;
    }

    public int getArtist_poster2_resource() {
        return artist_poster2_resource;
    }

    public void setArtist_poster2_resource(int artist_poster2_resource) {
        this.artist_poster2_resource = artist_poster2_resource;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }
}
