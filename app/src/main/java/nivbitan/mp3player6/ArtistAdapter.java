package nivbitan.mp3player6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ניב on 13/03/2016.
 */
public class ArtistAdapter extends ArrayAdapter
{
    List list = new ArrayList();

    public ArtistAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class DataHandler
    {
        ImageView Poster1;
        ImageView Poster2;
        TextView name;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View row;
        row = convertView;
        DataHandler handler;

//      check if row  exist
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout,parent,false);
            handler = new DataHandler();
            handler.Poster1 = (ImageView) row.findViewById(R.id.artist_poster1);
            handler.name = (TextView) row.findViewById(R.id.artist_name);
            handler.Poster2 = (ImageView) row.findViewById(R.id.artist_poster2);
            row.setTag(handler);
        }
        else
        {
            handler = (DataHandler) row.getTag();
        }

        ArtistDataProvider dataProvider;
        dataProvider = (ArtistDataProvider) this.getItem(position);
        handler.Poster1.setImageResource(dataProvider.getArtist_poster1_resource());
        handler.Poster2.setImageResource(dataProvider.getArtist_poster2_resource());
        handler.name.setText(dataProvider.getArtist_name());

        return row;
    }
}
