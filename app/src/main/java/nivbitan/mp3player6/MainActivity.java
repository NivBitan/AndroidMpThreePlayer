package nivbitan.mp3player6;



import android.content.Intent;
import android.media.AudioManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;




/*     README FILE CONTENT:

        Niv Bitan
        200554210

        added apk file "app-release.apk" (also under Mp3Player6/app)

        instructions:

        - main activity : listview of artist names
        - LONG press on artist name opens context menu of artist songs
        - selecting song name opens the play screen
        - In play screen there are 3 buttons: play, pause & replay


        Features:

        - play screen contains two buttons: restart button and a changing play/pause button

        - added animated Transition From MainActivity to PlayScreen Activity and vise versa.

        - added horizontal scrolling to long song names

        - set PlayScreen Activity screenOrientation to potrait

        - custom styled seekbar (style xml's under drawable folder)

        - texview of total song time and time played (adjusted with seekbar)*/


public class MainActivity extends FragmentActivity {

    ListView listView;
    int[] artist_poster_resource = {R.drawable.guns1,R.drawable.alice1,R.drawable.redhot1,R.drawable.led_zeppelin1,
                                    R.drawable.nirvana1,R.drawable.disturbed1,R.drawable.acdc1,R.drawable.avengesevenfold1,
                                    R.drawable.blacksabbath1,R.drawable.thedoors1,R.drawable.linkinpark1,R.drawable.metallica1};

    String[] artist_names;
    ArtistAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        artist_names = getResources().getStringArray(R.array.artist_names);
        Arrays.sort(artist_names); //sort artist names
        Arrays.sort(artist_poster_resource); //sort artist posters
        adapter = new ArtistAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(adapter);
        int i = 0;


        //connect volume button of phone to app
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        for(String names: artist_names)
        {
            ArtistDataProvider dataProvider = new ArtistDataProvider(artist_poster_resource[i],names,artist_poster_resource[i]);
            adapter.add(dataProvider);
            i++;
        }

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "item was selected",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {

        if (v.getId() == R.id.listView)
        {
            String[] menuItems = null;
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(artist_names[info.position]);
            //Toast.makeText(getBaseContext(), artist_names[info.position],Toast.LENGTH_LONG).show();
            //menuItems = getResources().getStringArray(R.array.Red_Hot_Chili_Peppers_songs);
            if (artist_names[info.position].equals("Guns N Roses"))
                menuItems = getResources().getStringArray(R.array.guns_N_roses_songs);
            if (artist_names[info.position].equals("Alice In Chains"))
                menuItems = getResources().getStringArray(R.array.Alice_In_Chains_songs);
            if (artist_names[info.position].equals("Red Hot Chili Peppers"))
                menuItems = getResources().getStringArray(R.array.Red_Hot_Chili_Peppers_songs);
            if (artist_names[info.position].equals("Led Zeppelin") )
                menuItems = getResources().getStringArray(R.array.Led_Zepplin_songs);
            if (artist_names[info.position].equals("Nirvana"))
                menuItems = getResources().getStringArray(R.array.Nirvana_songs);
            if (artist_names[info.position].equals("Disturbed"))
                menuItems = getResources().getStringArray(R.array.Disturbed_songs);
            if (artist_names[info.position].equals("Linkin Park"))
                menuItems = getResources().getStringArray(R.array.Linkin_Park_songs);
            if (artist_names[info.position].equals("ACDC"))
                menuItems = getResources().getStringArray(R.array.ACDC_songs);
            if (artist_names[info.position].equals("The Doors"))
                menuItems = getResources().getStringArray(R.array.The_Doors_songs);
            if (artist_names[info.position].equals("Metallica"))
                menuItems = getResources().getStringArray(R.array.Metallica_songs);
            if (artist_names[info.position].equals("Black Sabbath"))
                menuItems = getResources().getStringArray(R.array.Black_Sabbath_songs);
            if (artist_names[info.position].equals("Avenge Sevenfold"))
                menuItems = getResources().getStringArray(R.array.Avenge_Sevenfold_songs);

            for (int i = 0; i < menuItems.length; i++)
            {
                menu.add(Menu.NONE,i,i,menuItems[i]);
            }
        }

        //getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();
        String[] menuItems = getResources().getStringArray(R.array.guns_N_roses_songs);

        String selectedArtistName = artist_names[info.position];
        if (selectedArtistName.equals("Guns N Roses"))
            menuItems = getResources().getStringArray(R.array.guns_N_roses_songs);
        if (selectedArtistName.equals("Alice In Chains"))
            menuItems = getResources().getStringArray(R.array.Alice_In_Chains_songs);
        if (selectedArtistName.equals("Red Hot Chili Peppers"))
            menuItems = getResources().getStringArray(R.array.Red_Hot_Chili_Peppers_songs);
        if (selectedArtistName.equals("Led Zeppelin"))
            menuItems = getResources().getStringArray(R.array.Led_Zepplin_songs);
        if (selectedArtistName.equals("Nirvana"))
            menuItems = getResources().getStringArray(R.array.Nirvana_songs);
        if (selectedArtistName.equals("Disturbed"))
            menuItems = getResources().getStringArray(R.array.Disturbed_songs);
        if (selectedArtistName.equals("Linkin Park"))
            menuItems = getResources().getStringArray(R.array.Linkin_Park_songs);
        if (selectedArtistName.equals("ACDC"))
            menuItems = getResources().getStringArray(R.array.ACDC_songs);
        if (selectedArtistName.equals("The Doors"))
            menuItems = getResources().getStringArray(R.array.The_Doors_songs);
        if (selectedArtistName.equals("Metallica"))
            menuItems = getResources().getStringArray(R.array.Metallica_songs);
        if (selectedArtistName.equals("Black Sabbath"))
            menuItems = getResources().getStringArray(R.array.Black_Sabbath_songs);
        if (selectedArtistName.equals("Avenge Sevenfold"))
            menuItems = getResources().getStringArray(R.array.Avenge_Sevenfold_songs);

        String selectedSongName = menuItems[menuItemIndex];
        String text = "Selected " + selectedSongName + " By " + selectedArtistName;
        Toast.makeText(getBaseContext(), text,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),PlayScreen.class);

        intent.putExtra("artistName",selectedArtistName);
        intent.putExtra("songName",selectedSongName);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        return true;
    }
}
