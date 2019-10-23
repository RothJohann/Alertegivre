package strteam.alertegivre;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;
import pl.droidsonroids.gif.GifTextView;

public class MainActivity extends AppCompatActivity {
    private Button mbuttoncheck;
    private Button mbuttonpasdgivre;
    private Button mbuttonsignalgivre;
    private GifTextView mgifloader;
    private TextView minfosmeteo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbuttoncheck = (Button) findViewById(R.id.btncheckgivre);
        mbuttonpasdgivre = (Button) findViewById(R.id.btnpasdgivre);
        mbuttonsignalgivre = (Button) findViewById(R.id.btnsignalgivre);
        mgifloader = (GifTextView) findViewById(R.id.gifloader);
        minfosmeteo = (TextView) findViewById(R.id.txtbidonapresclic);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //mgifloader.setVisibility(View.INVISIBLE);

        /*mbuttonmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent Intentmap = new Intent(MainActivity.this, map_activity.class);
                startActivity(Intentmap);



            } //fin du onclick }
        }); //fin du setonclicklistener });
*/
        mbuttoncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url =new URL("http://alertegivre.hopto.org:8080/meteo/gratter.php");
                    try {
                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        InputStream stream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(stream));
                        StringBuffer buffer = new StringBuffer();

                        String line = "";
                        while ((line  = reader.readLine())!=null){
                            buffer.append(line);
                        }
                        minfosmeteo.setText(buffer.toString());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }finally{
                    if (connection != null)
                        connection.disconnect();
                    try {
                        if (reader !=null)
                            reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } //fin du onclick }
        }); //fin du setonclicklistener

        mbuttonsignalgivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url =new URL("http://alertegivre.hopto.org:8080/meteo/rapporter.php");
                    try {
                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        InputStream stream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(stream));
                        StringBuffer buffer = new StringBuffer();

                        String line = "";
                        while ((line  = reader.readLine())!=null){
                            buffer.append(line);
                        }

                        if (buffer.toString().compareTo("1")==0)
                        {
                            minfosmeteo.setText("Le rapport a bien été inséré dans la base de données(givre présent)");
                        }
                        else
                        {
                            minfosmeteo.setText("Erreur d'insertion du rapport dans la base de données");
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }finally{
                    if (connection != null)
                        connection.disconnect();
                    try {
                        if (reader !=null)
                            reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



            } //fin du onclick }
        });
        mbuttonpasdgivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url =new URL("http://alertegivre.hopto.org:8080/meteo/rapporterfalse.php");
                    try {
                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        InputStream stream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(stream));
                        StringBuffer buffer = new StringBuffer();

                        String line = "";
                        while ((line  = reader.readLine())!=null){
                            buffer.append(line);
                        }

                        if (buffer.toString().compareTo("1")==0)
                        {
                            minfosmeteo.setText("Le rapport a bien été inséré dans la base de données(pas de givre)");
                        }
                        else
                        {
                            minfosmeteo.setText("Erreur d'insertion du rapport dans la base de données");
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }finally{
                    if (connection != null)
                        connection.disconnect();
                    try {
                        if (reader !=null)
                            reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



            } //fin du onclick }
        });


    } //fin de ce qu'on fait au démarrage }
} //fin de l'activité }
