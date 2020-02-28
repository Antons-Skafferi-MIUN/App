package se.miun.dt170.antonsskafferi.data;

import android.util.Log;
import android.widget.SimpleAdapter;
import androidx.recyclerview.widget.ListAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import se.miun.dt170.antonsskafferi.MainActivity;

//Temp
/*
 ///TEMP XML REALATED STUFF
    TextView textview;
    private HandleXML obj;
    //ListView lv = (ListView) findViewById(R.id.user_list);

    public void Content() {
        //ListAdapter adapter = new SimpleAdapter(MainActivity.this, obj.getFoodlist(), R.layout.list_row,new String[]{"name","designation","location"}, new int[]{R.id.name, R.id.designation, R.id.location});
        //lv.setAdapter(adapter);
    }


  textview = findViewById(R.id.textView66);
          textview.setText("XML hääär");
          obj = new HandleXML("https://entresundsvall.se/arrangemang/feed/");
          obj.fetchXML();
*/

//https://www.tutlane.com/tutorial/android/android-xml-parsing-using-xmlpullparser

public class HandleXML {

    private String urlString = null;
    private String title = "Titel";
    private String cost = "90-;";

    private XmlPullParserFactory xmlFactoryObject;
    private ArrayList<HashMap<String, String>>foodlist = new ArrayList<>();
    private HashMap<String, String>food = new HashMap<>();


    public HandleXML(String url)
    {
        this.urlString = url;
    }


    //Getter and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public ArrayList<HashMap<String, String>> getFoodlist() {
        return foodlist;
    }

    public void setFoodlist(ArrayList<HashMap<String, String>> foodlist) {
        this.foodlist = foodlist;
    }

    public HashMap<String, String> getFood() {
        return food;
    }

    public void setFood(HashMap<String, String> food) {
        this.food = food;
    }



    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        Log.d("TAG", "parseXMLAndStoreIt köööööörs nuuuu: ");
        //LISTVIEW FINDVIEWBYID DEFINITION här...

        int event;
        String tag = "";
        String text = "";

        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT)
            {
                tag = myParser.getName();
                switch (event)
                {
                    case XmlPullParser.START_TAG:
                        if (tag.equals("main"))
                            setFood(new HashMap<>());
                            break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        //Log.d("END TAG", "parseXMLAndStoreIt: ");
                        switch (tag) {
                            case "title":
                                food.put("title", text);
                                Log.d("TITLE", text);
                                break;
                            case "link":
                                food.put("link", text);
                                Log.d("LINK", text);
                                break;
                            case "item":
                                if (food != null)
                                    foodlist.add(food);
                                break;
                        }
                        break;

                    }
                    event = myParser.next();

                }
            Log.d("SLSLSLSLSL", String.valueOf(getFoodlist().size()));

            //MainActivity main = new MainActivity();
            //main.Content();


            } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void fetchXML() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlString);
                    HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                    connect.setReadTimeout(10000);
                    connect.setConnectTimeout(15000);
                    connect.setRequestMethod("GET");
                    connect.setDoInput(true);
                    connect.setDoOutput(true);
                    connect.connect();

                    InputStream stream = connect.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = xmlFactoryObject.newPullParser();
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(stream, null);
                    parseXMLAndStoreIt(parser);
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


}
