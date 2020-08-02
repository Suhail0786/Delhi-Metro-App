package com.example.mymetro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class RouteActivity extends AppCompatActivity {

    AutoCompleteTextView actvSource;
    AutoCompleteTextView actvDes;
    ImageButton imageButton;
    TextView textView;
    ListView listView;
    String start, stop;
    int count;
    ArrayList<Station> list = new ArrayList<Station>(5);
    ArrayAdapter<String> adapterString;
    MyAdapter customAdaptor;
    ArrayAdapter<Station> adapterStation;
    Station[] stationArray = new Station[5];
    String[] routeNameArray;
    int[] routeNodeArray;
    int change = 0;
    Graph metroMap = new Graph(248);
    //String[] stations = getResources().getStringArray(R.array.station_array);

    String[] stationNameList = new String[]


//1 Violet line, 34 stations, 60 rs, 76 min //towards kashmere gate, 5 junction

            {"KASHMERE GATE(junction)", "LAL QILA", "JAMA MASJID", "DELHI GATE", "ITO", "MANDI HOUSE(junction)", "JANPATH", "CENTRAL SECRETARIAT(junction)", "KHAN MARKET", "JAWAHARLAL NEHRU STADIUM", "JANGPURA", "LAJPAT NAGAR(junction)", "MOOLCHAND", "KAILASH COLONY", "NEHRU PLACE", "KALKAJI MANDIR(junction)", "GOVIND PURI", "HARKESH NAGAROKHLA", "JASOLA APOLLO", "SARITA VIHAR", "MOHAN ESTATE", "TUGHLAKABAD", "BADARPUR", "SARAI", "NHPC CHOWK", "MEWALA MAHARAJPUR", "SECTOR 28", "BADKAL MOR", "OLD FARIDABAD", "NEELAM CHOWK AJRONDA", "BATA CHOWK", "ESCORTS MUJESAR", "SANT SURDAS(SIHI)", "RAJA NAHAR SINGH",


//2 Yellow Line, 35 stations, 60 rs, 82 min //towards samaypur badli(Platform 2), 8 junction

                    "HUDA CITY CENTRE", "IFFCO CHOWK", "M G ROAD", "SIKANDERPUR(junction)", "GURU DRONACHARYA", "ARJANGARH", "GHITORNI", "SULTANPUR", "CHHATTARPUR", "QUTAB MINAR", "SAKET", "MALVIYA NAGAR", "HAUZ KHAS(junction)", "GREEN PARK", "AIIMS", "INA(junction)", "JORBAGH", "LOK KALYAN MARG", "UDYOG BHAWAN", "PATEL CHOWK", "RAJIV CHOWK(junction)", "NEW DELHI(junction)", "CHAWRI BAZAR", "CHANDNI CHOWK", "CIVIL LINES", "VIDHAN SABHA", "VISHWAVIDYALAYA", "G.T.B. NAGAR", "MODEL TOWN", "AZADPUR(junction)", "ADARSH NAGAR", "JAHANGIRPURI", "HAIDERPUR BADLI MOR", "ROHINI SECTOR 18", "SAMAYPUR BADLI",


//For Orange Line //Blue line until Rajiv chowk (Yellow line), 30 stations,60 rs,68 min , 1 change ,towards noida elec city(platform 1), if route through without airport line.

//3 Orange line or Airport line , 6 stations, 60 rs, 26 min, Towards New Delhi(Platform 3), if route through with airport line.

                    "DWARKA SECTOR 21", "IGI AIRPORT", "DELHI AERO CITY", "DHAULA KUAN(junction)", "SHIVAJI STADIUM",


//4 Blue line 1, 50 stations , 60 rs, 101 min, Towards Noida elec city (Platform 1) , if route through without airport line.,10 junction

                    "DWARKA SEC 8", "DWARKA SEC 9", "DWARKA SEC 10", "DWARKA SEC 11", "DWARKA SEC 12", "DWARKA SEC 13", "DWARKA SEC 14", "DWARKA", "DWARKA MOR", "NAWADA", "UTTAM NAGAR WEST", "UTTAM NAGAR EAST", "JANAK PURI WEST(junction)", "JANAK PURI EAST", "TILAK NAGAR", "SUBHASH NAGAR", "TAGORE GARDEN", "RAJOURI GARDEN(junction)", "RAMESH NAGAR", "MOTI NAGAR", "KIRTI NAGAR(junction)", "SHADIPUR", "PATEL NAGAR", "RAJENDRA PLACE", "KAROL BAGH", "JHANDEWALAN", "RK ASHRAM MARG", "BARAKHAMBA", "PRAGATI MAIDAN", "INDRAPRASTHA", "YAMUNA BANK(junction)", "AKSHARDHAM", "MAYUR VIHAR I(junction)", "MAYUR VIHAR EXT", "NEW ASHOK NAGAR", "NOIDA SEC 15", "NOIDA SEC 16", "NOIDA SEC 18", "BOTANICAL GARDEN(junction)", "GOLF COURSE", "NOIDA CITY CENTRE", "NOIDA SEC 34", "NOIDA SEC 52(junction)", "NOIDA SEC 61", "NOIDA SEC 59", "NOIDA SEC 62", "NOIDA ELECTRONIC CITY",

//5 Blue Line 2 ,8 stations , 30 rs , 15 min , Towards Dwarka(platform 1), 3 junction

                    "VAISHALI", "KAUSHAMBI", "ANAND VIHAR(junction)", "KARKAR DUMA(junction)", "PREET VIHAR", "NIRMAN VIHAR", "LAXMI NAGAR",

//6 Aqua Line , 21 stations

                    "NOIDA SECTOR 51", "NOIDA SECTOR 50", "NOIDA SECTOR 76", "NOIDA SECTOR 101", "NOIDA SECTOR 81", "NSEZ", "NOIDA SECTOR 83", "NOIDA SECTOR 137", "NOIDA SECTOR 142", "NOIDA SECTOR 143", "NOIDA SECTOR 144", "NOIDA SECTOR 145", "NOIDA SECTOR 146", "NOIDA SECTOR 147", "NOIDA SECTOR 148", "KNOWLEDGE PARK 2", "PARI CHOWK", "ALPHA 1", "DELTA 1", "GREATER NOIDA(GNOIDA) OFFICE", "DEPOT STATION",

//7 Green Line 1, 22 stations, 50 rs, 45 min , Towards Kirti Nagar(Platform 2)

                    "BRIGADIER HOSHIYAR(Bahadurgarh City Park)", "BAHADURGARH CITY", "PANDIT SHREE RAM SHARMA", "TIKRI BORDER", "TIKRI KALAN", "GHEVRA METRO STATION", "MUNDKA INDUSTRIAL AREA", "MUNDKA", "RAJDHANI PARK", "NANGLOI RLY. STATION", "NANGLOI", "SURAJMAL STADIUM", "UDYOG NAGAR", "PEERA GARHI", "PASCHIM VIHAR (WEST)", "PASCHIM VIHAR (EAST)", "MADI PUR", "SHIVAJI PARK", "PUNJABI BAGH", "ASHOK PARK MAIN(Junction)", "SATGURU RAM SINGH MARG",


//8 Red line, 29 stations, 50 rs, 59 min, Towards shaheed sthal Platform 1, 4 junction

                    "RITHALA", "ROHINI WEST", "ROHINI EAST", "PITAM PURA", "KOHAT ENCLAVE", "NETAJI SUBHASH PLACE(junction)", "KESHAV PURAM", "KANHAIYA NAGAR", "INDER LOK(junction)", "SHASTRI NAGAR", "PRATAP NAGAR", "PUL BANGASH", "TIS HAZARI", "SHASTRI PARK", "SEELAMPUR", "WELCOME(junction)", "SHAHDARA", "MANSAROVAR PARK", "JHIL MIL", "DILSHAD GARDEN", "SHAHID NAGAR", "RAJ BAGH", "MAJOR MOHIT SHARMA", "SHYAM PARK", "MOHAN NAGAR", "ARTHALA", "HINDON", "SHAHEED STHAL",

//9 Pink Line //Towards Mayur Vihar Pocket 1(Platform 1), 3 junction

                    "MAJLIS PARK", "SHALIMAR BAGH", "SHAKURPUR", "PUNJABI BAGH WEST", "ESI BASAIDARPUR", "MAYAPURI", "NARAINA VIHAR", "DELHI CANTONMENT", "DURGABAI DESHMUKH SOUTH CAMPUS", "SIR VISHWESHWARAIAH MOTI BAGH", "BHIKAJI CAMA PLACE", "SAROJINI NAGAR", "SOUTH EXTENSION", "VINOBAPURI", "ASHRAM", "SARAI KALE KHAN NIZAMUDDIN", "MAYUR VIHAR POCKET I", "TRILOKPURI SANJAY LAKE", "EAST VINOD NAGAR-Mayur Vihar-II", "MANDAWALI-West Vinod Nagar", "IP EXTENSION", "KARKARDUMA COURT", "KRISHNA NAGAR", "EAST AZAD NAGAR", "JAFFRABAD", "MAUJPUR-BABARPUR", "GOKULPURI", "JOHRI ENCLAVE", "SHIV VIHAR",

//10 Megenta Line,25 stations,50 rs,59 min //Towards Botanical Garden(Platform 3),4 junction
                    "DABRI MOR-JANAKPURI SOUTH", "DASHRATH PURI", "PALAM", "SADAR BAZAAR CANTONMENT", "TERMINAL 1-IGI AIRPORT", "SHANKAR VIHAR", "VASANT VIHAR", "MUNIRKA", "R.K PURAM", "IIT DELHI", "PANCHSHEEL PARK", "CHIRAG DELHI", "GREATER KAILASH", "NEHRU ENCLAVE", "OKHLA NSIC", "SUKHDEV VIHAR", "JAMIA MILLIA ISLAMIA", "OKHLA VIHAR", "JASOLA VIHAR SHAHEEN BAGH", "KALINDI KUNJ", "OKHLA BIRD SANCTUARY"};


    int[] stationNodeList = new int[]{/*1*/0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33,
            /*2*/34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68,
            /*3*/69, 70, 71, 72, 73,
            /*4*/74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120,
            /*5*/121, 122, 123, 124, 125, 126, 127,
            /*6*/128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148,
            /*7*/149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169,
            /*8*/170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197,
            /*9*/198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226,
            /*10*/227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247};

    boolean sourceEntered = false;

    String[] b = {"ANAND VIHAR(junction)", "ASHOK PARK MAIN(junction)", "AZADPUR(junction)", "BOTANICAL GARDEN(junction)", "CENTRAL SECRETARIAT(junction)", "DHAULA KUAN(junction)", "DURGABAI DESHMUKH SOUTH CAMPUS(junction)", "DWARKA SEC 21(junction)", "HAUZ KHAS(junction)", "INA(junction)", "INDER LOK(junction)", "JANAKPURI WEST(junction)", "KALKAJI MANDIR(junction)", "KARKAR DUMA(junction)", "KASHMERE GATE(junction)", "KIRTI NAGAR(junction)", "LAJPAT NAGAR(junction)", "MANDI HOUSE(junction)", "MAYUR VIHAR I(junction)", "NETAJI SUBHASH PLACE(junction)", "NEW DELHI(junction)", "NOIDA SEC 51(junction)", "NOIDA SEC 52(junction)", "PHASE 2 (RAPID METRO)(junction)", "RAJIV CHOWK(junction)", "RAJOURI GARDEN(junction)", "SIKANDERPUR(junction)", "SIKANDERPUR(RAPID METRO)(junction)", "WELCOME(junction)", "YAMUNA BANK(junction)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        actvSource = (AutoCompleteTextView) findViewById(R.id.sourceText);
        actvDes = (AutoCompleteTextView) findViewById(R.id.desText);
        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listview);
        imageButton = (ImageButton) findViewById(R.id.showRoute);

        String[] stations = getResources().getStringArray(R.array.station_array);
        actvSource.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stations));
        actvDes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, stations));


        // populating the list view of all the stations (with logo) (does not work, has bugs)
//        populateListViewWithLogo();


        //Suggestions for user in listview based on the letter they typed to enter station for source
        actvSource.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //populateListView();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Triggered when text changed at et_to edittext
                Log.d("Listview", "Is this list view working");
                RouteActivity.this.adapterString.getFilter().filter(s);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("RouteActivity", "Item clicked at location" + position);
                        String elemt = adapterString.getItem(position);
                        actvSource.setText(elemt);
                        actvDes.requestFocus();
                        populateListView();

                    }
                });

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Suggestions for user in listview based on the letter they typed to enter station for destination
        actvSource.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("Listview", "Is this triggered");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Triggered when text changed at et_from edittext
                Log.d("Listview", "Is this list view working");
                RouteActivity.this.adapterString.getFilter().filter(s);
                listView.setVisibility(View.VISIBLE);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("RouteActivity", "Item clicked at location" + position);
                        String elemt = adapterString.getItem(position);
                        actvDes.setText(elemt);

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!sourceEntered) {
                    actvSource.setText(list.get(position).getStationName());
                    sourceEntered = true;
                } else {
                    actvDes.setText(list.get(position).getStationName());
                    sourceEntered = false;
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //This intent starts the cardview
//                Intent intent=new Intent(getApplicationContext(),cardview.class);
//                startActivity(intent);
                clearValue();
                String from = actvSource.getText().toString();
                String to = actvDes.getText().toString();

                if (from.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter source station", Toast.LENGTH_SHORT).show();
                    actvSource.requestFocus();
                } else if (to.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter destination station", Toast.LENGTH_SHORT).show();
                    actvDes.requestFocus();
                } else {
                    ListOfRoute(from, to);
                }
            }
        });



        /*imageButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                clearValue();
                String from = actvSource.getText().toString().trim();
                String to = actvDes.getText().toString().trim();
                if (start.length() != 0 && stop.length() != 0 && !start.equals(stop)) {
                    ListOfRoute(from,to);
                } else if (start.length() != 0 && stop.length() == 0) {
                    Toast.makeText(RouteActivity.this, "Please Insert Destination Station", Toast.LENGTH_SHORT).show();
                } else if (stop.length() != 0 && start.length() == 0) {
                    Toast.makeText(RouteActivity.this, "Please Insert Source Station", Toast.LENGTH_SHORT).show();
                } else if (start.length() == 0 && stop.length() == 0) {
                    Toast.makeText(RouteActivity.this, "Please Insert Source And Destination Station First", Toast.LENGTH_SHORT).show();
                } else if(start.length() != 0 && stop.length() != 0 && start.equals(stop)) {
                    Toast.makeText(RouteActivity.this, "Sorry! Source And Destination Station Are Same", Toast.LENGTH_SHORT).show();
                }

                actvSource.setText("");
                actvDes.setText("");
            }
        });*/


        Station[] stationArray = new Station[248];
        for (int i = 0; i < 247; i++) {
            stationArray[i] = new Station(stationNameList[i], stationNodeList[i]);
        }

        // addEdges to the graph: metroMap
        addEdgesToMetroMap();

        // adding stations to the stationArrayList
        fillStationArrayList(stationArray);

        // populating the list view of all the stations
        populateListView();

    }


    // functions

    public void fillStationArrayList(Station stationArray[]) {
        for (int i = 0; i < stationArray.length; i++) {
            list.add(stationArray[i]);
        }
    }

   public void fillStationArray() {
        for (int i = 0; i < stationArray.length; i++) {
            stationArray[i].addStationValues(stationNameList[i], stationNodeList[i]);
        }
    }

    public void populateListView() {
        adapterString = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stationNameList);
        listView.setAdapter(adapterString);
    }


    //Add edges ro metro map
    public void addEdgesToMetroMap() {


        /*----Violet Line----*/

        //Kashmere Gate (junction)
        metroMap.addEdge(0, 1); //To Lal Quila
        metroMap.addEdge(0, 182); //To Tis Hazari
        metroMap.addEdge(0, 183); //To shastri park
        metroMap.addEdge(0, 57); //To Civil Lines
        metroMap.addEdge(0, 58); //To Chandni Chowk

        //Lal Quila
        metroMap.addEdge(1, 0); //To Kashmere gate
        metroMap.addEdge(1, 2); //To Jama Masjid

        //Jama Masjid
        metroMap.addEdge(2, 1); //To Lal Quila
        metroMap.addEdge(2, 3); //To Delhi gate

        //Delhi Gate
        metroMap.addEdge(3, 2); //To Jama masjid
        metroMap.addEdge(3, 4); //ITO

        //ITO
        metroMap.addEdge(4, 3); //To Delhi Gate
        metroMap.addEdge(4, 5); //Mandi House

        //Mandi House(junction)
        metroMap.addEdge(5, 4); //ITO
        metroMap.addEdge(5, 6); //Janpath
        metroMap.addEdge(5, 101); //Barakhamba Road
        metroMap.addEdge(5, 102); //Pragati maidan

        //Janpath
        metroMap.addEdge(6, 5); //Mandi House
        metroMap.addEdge(6, 7); //Central Secretariat

        //Central Secretriat(junction)
        metroMap.addEdge(7, 6); //Janpath
        metroMap.addEdge(7, 8); //Khan Market
        metroMap.addEdge(7, 52); //Udyog Bhawan
        metroMap.addEdge(7, 53); //Patel Chowk

        //Khan Market
        metroMap.addEdge(8, 7); //Central sec.
        metroMap.addEdge(8, 9); //JLN stadium

        //JLN stadium
        metroMap.addEdge(9, 8); //Khan Market
        metroMap.addEdge(9, 10); //Jangpoora

        //Jangpoora
        metroMap.addEdge(10, 9); //JLN stadium
        metroMap.addEdge(10, 11); //Lajpat Nagar

        //Lajpat Nagar(junction)
        metroMap.addEdge(11, 10); //Jangpoora
        metroMap.addEdge(11, 12); //Moolchand
        metroMap.addEdge(11, 210); //south extension
        metroMap.addEdge(11, 211); //vinobapuri

        //Moolchand
        metroMap.addEdge(12, 11); //Lajpat Nagar
        metroMap.addEdge(12, 13); //Kailash colony

        //Kailash colony
        metroMap.addEdge(13, 12); //Moolchand
        metroMap.addEdge(13, 14); //Nehru Place

        //Nehru Place
        metroMap.addEdge(14, 13); //Kailash colony
        metroMap.addEdge(14, 15); //Kalkaji Mandir

        //Kalkaji Mandir(junction)
        metroMap.addEdge(15, 14); //Nehru Place
        metroMap.addEdge(15, 16); //Govind Puri
        metroMap.addEdge(15, 240); //Nehru Enclave
        metroMap.addEdge(15, 241); //Okhla NSIC

        //Govind Puri
        metroMap.addEdge(16, 15); //Kalkaji Mandir
        metroMap.addEdge(16, 17); //Harkesh Nagar Okhla

        //Harkesh Nagar Okhla
        metroMap.addEdge(17, 16); //Govind Puri
        metroMap.addEdge(17, 18); //Jasola Apollo

        //Jasola Apollo
        metroMap.addEdge(18, 17); //Harkesh Nagar Okhla
        metroMap.addEdge(18, 19); //Sarita Vihar

        //Sarita Vihar
        metroMap.addEdge(19, 18);
        metroMap.addEdge(19, 20); //Mohan Estate

        //Mohan Estate
        metroMap.addEdge(20, 19);
        metroMap.addEdge(20, 21); //Tughlakabad

        //Tughlakabad
        metroMap.addEdge(21, 20);
        metroMap.addEdge(21, 22); //Badarpur Border

        //Badarpur Border
        metroMap.addEdge(22, 21);
        metroMap.addEdge(22, 23); //Sarai

        //Sarai
        metroMap.addEdge(23, 22);
        metroMap.addEdge(23, 24); //NHPC

        //NHPC
        metroMap.addEdge(24, 23);
        metroMap.addEdge(24, 25); //Mewala Maharajpur

        //Mewla
        metroMap.addEdge(25, 24);
        metroMap.addEdge(25, 26); //Sector 28

        //Sector 28
        metroMap.addEdge(26, 25);
        metroMap.addEdge(26, 27); //Badkal Mor

        //Badkal Mor
        metroMap.addEdge(27, 26);
        metroMap.addEdge(27, 28); //Old Faridabad

        //Old Faridabad
        metroMap.addEdge(28, 27);
        metroMap.addEdge(28, 29); //Neelam Chowk

        //Neelam chowk
        metroMap.addEdge(29, 28);
        metroMap.addEdge(29, 30); //Bata chowk

        //Bata chowk
        metroMap.addEdge(30, 29);
        metroMap.addEdge(30, 31); //Escorts Mujesar

        //Escorts Mujesar
        metroMap.addEdge(31, 30);
        metroMap.addEdge(31, 32); //Sant surdas

        //Sant surdas
        metroMap.addEdge(32, 31);
        metroMap.addEdge(32, 33); //Raja Nahar Singh


        /*-----Yellow Line-----*/

        //Huda City Center
        metroMap.addEdge(34, 35); //Iffco chowk

        //Iffco chowk
        metroMap.addEdge(35, 34);
        metroMap.addEdge(35, 36); //MG road

        //MG Road
        metroMap.addEdge(36, 35);
        metroMap.addEdge(36, 37); //Sikanderpur

        //Sikanderpur(junction)
        metroMap.addEdge(37, 36);
        metroMap.addEdge(37, 38); //Guru Dronacharya
        //Phase 1 for Light Blue Line
        //Phase 2 for Light Blue Line

        //Guru Dronacharya
        metroMap.addEdge(38, 37);
        metroMap.addEdge(38, 39); //Arjangarh

        //Arjangarh
        metroMap.addEdge(39, 38);
        metroMap.addEdge(39, 40); //Ghitorni

        //Ghitorni
        metroMap.addEdge(40, 39);
        metroMap.addEdge(40, 41); //Sultanpur

        //Sultanpur
        metroMap.addEdge(41, 40);
        metroMap.addEdge(41, 42); //Chhattarpur

        //Chhattarpur
        metroMap.addEdge(42, 41);
        metroMap.addEdge(42, 43); //Qutab Minar

        //Qutab Minar
        metroMap.addEdge(43, 42);
        metroMap.addEdge(43, 44); //Saket

        //Saket
        metroMap.addEdge(44, 43);
        metroMap.addEdge(44, 45); //Malviya Nagar

        //Malviya nagar
        metroMap.addEdge(45, 44);
        metroMap.addEdge(45, 46); //Hauz Khaas

        //Hauz Khaas(junction)
        metroMap.addEdge(46, 45);
        metroMap.addEdge(46, 47); //Green Park
        metroMap.addEdge(46, 236); //IIT
        metroMap.addEdge(46, 237); //Panscheel Park

        //Green Park
        metroMap.addEdge(47, 46);
        metroMap.addEdge(47, 48); //AIIms

        //AIIMS
        metroMap.addEdge(48, 47);
        metroMap.addEdge(48, 49); //INA

        //INA(junction)
        metroMap.addEdge(49, 48);
        metroMap.addEdge(49, 50); //Jor Bagh
        metroMap.addEdge(49, 209); //Sarojini Nagar
        metroMap.addEdge(49, 210); //South Extension

        //Jor Bagh
        metroMap.addEdge(50, 49);
        metroMap.addEdge(50, 51); //Lok Kalyan Marg

        //Lok Kalyan Marg
        metroMap.addEdge(51, 50);
        metroMap.addEdge(51, 52); //Udyog Bhawan

        //Udyog Bhawan
        metroMap.addEdge(52, 51);
        metroMap.addEdge(52, 53); //Patel Chowk

        //Patel Chowk
        metroMap.addEdge(53, 52);
        metroMap.addEdge(53, 54); //Rajiv Chowk
        metroMap.addEdge(53, 7); //Central sec

        //Rajiv Chowk(junction)
        metroMap.addEdge(54, 53);
        metroMap.addEdge(54, 55); //New Delhi
        metroMap.addEdge(54, 100); //Rk Ashram Marg
        metroMap.addEdge(54, 101); //Barakhamba

        //New Delhi
        metroMap.addEdge(55, 54);
        metroMap.addEdge(55, 56); //Chawri Bazar
        metroMap.addEdge(55, 73); //Shivaji Stadium

        //Chawri Bazar
        metroMap.addEdge(56, 55);
        metroMap.addEdge(56, 57); //Chandni Chowk

        //Chandni Chowk
        metroMap.addEdge(57, 56);
        metroMap.addEdge(57, 58); //Civil lines
        metroMap.addEdge(56, 0); //Kashmere Gate

        //Civil lines
        metroMap.addEdge(58, 57);
        metroMap.addEdge(58, 59); //Vidhan Sabha
        metroMap.addEdge(58, 0); //Kashmere gate

        //Vidhan Sabha
        metroMap.addEdge(59, 58);
        metroMap.addEdge(59, 60); //Vishwavidyalaya

        //Vishwavidyalaya
        metroMap.addEdge(60, 59);
        metroMap.addEdge(60, 61); //Guru Tegh Bahardur

        //Guru Tegh Bahadur
        metroMap.addEdge(61, 60);
        metroMap.addEdge(61, 62); //Model Town

        //Model Town
        metroMap.addEdge(62, 61);
        metroMap.addEdge(62, 63); //Azadpur

        //Azadpur(junction)
        metroMap.addEdge(63, 62);
        metroMap.addEdge(63, 64); //Adarsh Nagar
        metroMap.addEdge(63, 198); //Majlis Park
        metroMap.addEdge(63, 199); //Shalimar Bagh

        //Adarsh Nagar
        metroMap.addEdge(64, 63);
        metroMap.addEdge(64, 65); //Jahangirpuri

        //Jahangirpuri
        metroMap.addEdge(65, 64);
        metroMap.addEdge(65, 66); //Haiderpur Badli Mor

        //Haiderpur
        metroMap.addEdge(66, 65);
        metroMap.addEdge(66, 67); //Rohini Sec 18

        //Rohini Sec 18
        metroMap.addEdge(67, 66);
        metroMap.addEdge(67, 68); //Samaypur Badli


        /*-----Orange line------*/

        //Dwarka Sec 21(junction)
        metroMap.addEdge(69, 70); //Airport
        metroMap.addEdge(69, 74); //Dwarka Sec 8

        //Airport
        metroMap.addEdge(70, 69);
        metroMap.addEdge(70, 71); //Delhi Aerocity

        //Delhi Aerocity
        metroMap.addEdge(71, 70);
        metroMap.addEdge(71, 72); //Dhaula Kuan

        //Dhaula Kuan
        metroMap.addEdge(72, 71);
        metroMap.addEdge(72, 73); //Shivaji Stadium

        //Shivaji Stadium
        metroMap.addEdge(73, 72);
        metroMap.addEdge(73, 55); //New Delhi


        /*----Blue Line 1----*/

        //Dwarka Sec 8
        metroMap.addEdge(74, 69); //Dwarka Sec 21
        metroMap.addEdge(74, 75); //Dwarka Sec 9

        //Dwarka Sec 9
        metroMap.addEdge(75, 74);
        metroMap.addEdge(75, 76); //Dwarka Sec 10

        //Dwarka Sec 10
        metroMap.addEdge(76, 75);
        metroMap.addEdge(76, 77); //Dwarka Sec 11

        //Dwarka Sec 11
        metroMap.addEdge(77, 76);
        metroMap.addEdge(77, 78); //Dwarka Sec 12

        //Dwarka Sec 12
        metroMap.addEdge(78, 77);
        metroMap.addEdge(78, 79); //Dwarka Sec 13

        //Dwarka Sec 13
        metroMap.addEdge(79, 78);
        metroMap.addEdge(79, 80); //Dwarka Sec 14

        //Dwarka Sec 14
        metroMap.addEdge(80, 79);
        metroMap.addEdge(80, 81); //Dwarka

        //Dwarka
        metroMap.addEdge(81, 80);
        metroMap.addEdge(81, 82); //Dwarka Mor

        //Dwarka Mor
        metroMap.addEdge(82, 81);
        metroMap.addEdge(82, 83); //Nawada

        //Nawada
        metroMap.addEdge(83, 82);
        metroMap.addEdge(83, 84); //Uttam Nagar West

        //Uttam Nagar West
        metroMap.addEdge(84, 83);
        metroMap.addEdge(84, 85); //Uttam Nagar East

        //Uttam Nagar East
        metroMap.addEdge(85, 84);
        metroMap.addEdge(85, 86); //Janakpuri West

        //Janakpuri West
        metroMap.addEdge(86, 85);
        metroMap.addEdge(86, 87); //Janakpuri East
        metroMap.addEdge(86, 227); //Dabri Mor Janakpuri south

        //Janakpuri East
        metroMap.addEdge(87, 86);
        metroMap.addEdge(87, 88); //Tilak Nagar

        //Tilak Nagar
        metroMap.addEdge(88, 87);
        metroMap.addEdge(88, 89); //Subhash Nagar

        //Subhash Nagar
        metroMap.addEdge(89, 88);
        metroMap.addEdge(89, 90); //Tagore Garden

        //Tagore Garden
        metroMap.addEdge(90, 89);
        metroMap.addEdge(90, 91); //Rajouri Garden

        //Rajouri Garden(jnction)
        metroMap.addEdge(91, 90);
        metroMap.addEdge(91, 92); //Ramesh Nagar
        metroMap.addEdge(91, 202); //ESI
        metroMap.addEdge(91, 203); //Mayapuri

        //Ramesh Nagar
        metroMap.addEdge(92, 91);
        metroMap.addEdge(92, 93); //Moti Nagar

        //Moti nagar
        metroMap.addEdge(93, 92);
        metroMap.addEdge(93, 94); //Kirti nagar

        //Kirti Nagar
        metroMap.addEdge(94, 93);
        metroMap.addEdge(94, 95); //Shadipur
        metroMap.addEdge(94, 169); //Satguru Ram Singh Marg

        //Shadipur
        metroMap.addEdge(95, 94);
        metroMap.addEdge(95, 96); //Patel Nagar

        //Patel nagar
        metroMap.addEdge(96, 95);
        metroMap.addEdge(96, 97); //Rajendra Place

        //Rajendra Place
        metroMap.addEdge(97, 96);
        metroMap.addEdge(97, 98); //Karol Bagh

        //Karol Bagh
        metroMap.addEdge(98, 97);
        metroMap.addEdge(98, 99); //Jhandewalan

        //Jhandewalan
        metroMap.addEdge(99, 98);
        metroMap.addEdge(99, 100); //RK AShram Marg

        //Rk Ashram
        metroMap.addEdge(100, 99);
        metroMap.addEdge(100, 54); //Rajiv Chowk

        //Barakhamba
        metroMap.addEdge(101, 54); //Rajiv Chowk
        metroMap.addEdge(101, 5); //Mandi House

        //Pragati Maidan
        metroMap.addEdge(102, 5); //Mandi House
        metroMap.addEdge(102, 103); //Indraprasth

        //Indraprasth
        metroMap.addEdge(103, 102);
        metroMap.addEdge(103, 104); //Yamuna Bank

        //Yamuna Bank(junction)
        metroMap.addEdge(104, 103);
        metroMap.addEdge(104, 105); //Akshardham
        metroMap.addEdge(104, 127); //Laxmi Nagar

        //AksharDham
        metroMap.addEdge(105, 104);
        metroMap.addEdge(105, 106); //Mayur Vihar Phase 1

        //Mayur Vihar Phase 1(junction)
        metroMap.addEdge(106, 105);
        metroMap.addEdge(106, 107); //Mayur Vihar Ext
        metroMap.addEdge(106, 213); //Sarai
        metroMap.addEdge(106, 214); //Mayur Vihar Pocket 1

        //Mayur Vihar Ext
        metroMap.addEdge(107, 106);
        metroMap.addEdge(107, 108); //New Ashok Nagar

        //New Ashok Nagar
        metroMap.addEdge(108, 107);
        metroMap.addEdge(108, 109); //Noida Sce 15

        //Noida Sec 15
        metroMap.addEdge(109, 108);
        metroMap.addEdge(109, 110); //Noida Sec 16

        //Noida Sec 16
        metroMap.addEdge(110, 109);
        metroMap.addEdge(110, 111); //Noida Sec 18

        //Noida Sec 18
        metroMap.addEdge(111, 110);
        metroMap.addEdge(111, 112); //Botanical Garden

        //Botanical Garden(junction)
        metroMap.addEdge(112, 111);
        metroMap.addEdge(112, 113); //Golf Course
        metroMap.addEdge(112, 247); //Okhla Bird Sanctuary

        //Golf Course
        metroMap.addEdge(113, 112);
        metroMap.addEdge(113, 114); //Noida City Center

        //Noida City Center
        metroMap.addEdge(114, 113);
        metroMap.addEdge(114, 115); //Noida Sec 34

        //Noida Sec 34
        metroMap.addEdge(115, 114);
        metroMap.addEdge(115, 116); //Noida Sec 52

        //Noida Sec 52
        metroMap.addEdge(116, 115);
        metroMap.addEdge(116, 117); //Noida Sec 61
        metroMap.addEdge(116, 128); //Noida Sec 51

        //Noida Sec 61
        metroMap.addEdge(117, 116);
        metroMap.addEdge(117, 118); //Noida Sec 59

        //Noida Sec 59
        metroMap.addEdge(118, 117);
        metroMap.addEdge(118, 119); //Noida Sec 62

        //Noida Sec 62
        metroMap.addEdge(119, 118);
        metroMap.addEdge(119, 120); //Noida Elec.

        //Noida Elec.
        metroMap.addEdge(120, 119);


        /*----Blue line 2----*/

        //Vaishali
        metroMap.addEdge(121, 122); //Kaushambi

        //Kaushambi
        metroMap.addEdge(122, 121);
        metroMap.addEdge(122, 123); //Anand Vihar

        //Anand Vihar(junction)
        metroMap.addEdge(123, 122);
        metroMap.addEdge(123, 124); //Karkarduma
        metroMap.addEdge(123, 218); //IP Extension

        //Karkarduma(junction)
        metroMap.addEdge(124, 123);
        metroMap.addEdge(124, 125); //Preet Vihar
        metroMap.addEdge(124, 219); //Karkarduma court

        //Preet vihar
        metroMap.addEdge(125, 124);
        metroMap.addEdge(125, 126); //Nirman Vihar

        //Nirman Vihar
        metroMap.addEdge(126, 125);
        metroMap.addEdge(126, 127); //Laxmi Nagar

        //Laxmi Nagar
        metroMap.addEdge(127, 126);
        metroMap.addEdge(127, 104); //Yamuna Bank


        /*----Aqua Line----*/

        //Noida Sec 51
        metroMap.addEdge(128, 116);
        metroMap.addEdge(128, 129); //Sec 50

        //Sec 50
        metroMap.addEdge(129, 128);
        metroMap.addEdge(129, 130); //Sec 76

        //Sec 76
        metroMap.addEdge(130, 129);
        metroMap.addEdge(130, 131); //Sec 101

        //Sec 101
        metroMap.addEdge(131, 130);
        metroMap.addEdge(131, 132); //Sec 81

        //Sec 81
        metroMap.addEdge(132, 131);
        metroMap.addEdge(132, 133); //NSEZ

        //NSEZ
        metroMap.addEdge(133, 132);
        metroMap.addEdge(133, 134); //Sec 83

        //Sec 83
        metroMap.addEdge(134, 133);
        metroMap.addEdge(134, 135); //Sec 137

        //Sec 137
        metroMap.addEdge(135, 134);
        metroMap.addEdge(135, 136); //Sec 142

        //Sec 142
        metroMap.addEdge(136, 135);
        metroMap.addEdge(136, 137); //Sec 143

        //Sec 143
        metroMap.addEdge(137, 136);
        metroMap.addEdge(137, 138); //Sec 144

        //Sec 144
        metroMap.addEdge(138, 137);
        metroMap.addEdge(138, 139); //Sec 145

        //Sec 145
        metroMap.addEdge(139, 138);
        metroMap.addEdge(139, 140); //Sec 146

        //Sec 146
        metroMap.addEdge(140, 139);
        metroMap.addEdge(140, 141); //Sec 147

        //Sec 147
        metroMap.addEdge(141, 140);
        metroMap.addEdge(141, 142); //Sec 148

        //Sec 148
        metroMap.addEdge(142, 141);
        metroMap.addEdge(142, 143); //Knowledge Park 2

        //Knowledge Park 2
        metroMap.addEdge(143, 142);
        metroMap.addEdge(143, 144); //Pari Chowk

        //Pari Chowk
        metroMap.addEdge(144, 143);
        metroMap.addEdge(144, 145); //Alpha 1

        //Alpha 1
        metroMap.addEdge(145, 144);
        metroMap.addEdge(145, 146); //Delta 1

        //Delta 1
        metroMap.addEdge(146, 145);
        metroMap.addEdge(146, 147); //GNID Office

        //GNID Office
        metroMap.addEdge(147, 146);
        metroMap.addEdge(147, 148); //Depot

        //Depot
        metroMap.addEdge(148, 147);


        /*---Green Line----*/

        //Brig Hoshiar Singh
        metroMap.addEdge(149, 150);

        //Bahadurgarh
        metroMap.addEdge(150, 149);
        metroMap.addEdge(150, 151); //Pandit Shree Ram

        //Pandit Shree Ram
        metroMap.addEdge(151, 150);
        metroMap.addEdge(151, 152); //Tikri Border

        //Tikri Border
        metroMap.addEdge(152, 151);
        metroMap.addEdge(152, 153); //Tikri Kalan

        //Tikri Kalan
        metroMap.addEdge(153, 152);
        metroMap.addEdge(153, 154); //Ghevra Metro

        //Ghevra Metro
        metroMap.addEdge(154, 153);
        metroMap.addEdge(154, 155); //Mundka Industrial Area

        //Mundka Indus.
        metroMap.addEdge(155, 154);
        metroMap.addEdge(155, 156); //Mundka

        //Mundka
        metroMap.addEdge(156, 155);
        metroMap.addEdge(156, 157); //Rajdhani Park

        //Rajdhani Park
        metroMap.addEdge(157, 156);
        metroMap.addEdge(157, 158); //Nangloi rly station

        //Nangloi rly
        metroMap.addEdge(158, 157);
        metroMap.addEdge(158, 159); //Nangloi

        //Nangloi
        metroMap.addEdge(159, 158);
        metroMap.addEdge(159, 160); //Maharja Surajmal

        //Maharaja Surajmal
        metroMap.addEdge(160, 159);
        metroMap.addEdge(160, 161); //Udyog nagar

        //Udyog Nagar
        metroMap.addEdge(161, 160);
        metroMap.addEdge(161, 162); //Peera Garhi

        //Peera Garhi
        metroMap.addEdge(162, 161);
        metroMap.addEdge(162, 163); //Pashchim Vihar West

        //Pashchim Vihar West
        metroMap.addEdge(163, 162);
        metroMap.addEdge(163, 164); //Pashchim Vihar East

        //Pashchim Vihar East
        metroMap.addEdge(164, 163);
        metroMap.addEdge(164, 165); //Madipur

        //Madipur
        metroMap.addEdge(165, 164);
        metroMap.addEdge(165, 166); //Shivaji Park

        //Shivaji Park
        metroMap.addEdge(166, 165);
        metroMap.addEdge(166, 167); //Punjabi Baagh

        //Punjabi Baagh
        metroMap.addEdge(167, 166);
        metroMap.addEdge(167, 168); //Ashok Park Main

        //Ashok Park Main
        metroMap.addEdge(168, 167);
        metroMap.addEdge(168, 169); //Satguru Ram Singh
        metroMap.addEdge(168, 178); //Inderlok

        //Satguru Ram Singh
        metroMap.addEdge(169, 168);
        metroMap.addEdge(169, 94); //Kirti Nagar


        /*----Red Line----*/

        //Rithala
        metroMap.addEdge(170, 171); //Rohini West

        //Rohini West
        metroMap.addEdge(171,170);
        metroMap.addEdge(171,172); //Rohini East

        //Rohini East
        metroMap.addEdge(172,171);
        metroMap.addEdge(172,173); //Pitampura

        //Pitampura
        metroMap.addEdge(173,172);
        metroMap.addEdge(173,174); //Kohat Enclave

        //Kohat Enclave
        metroMap.addEdge(174,173);
        metroMap.addEdge(174,175); //Netaji Subhash Place

        //Netaji Subhash Place
        metroMap.addEdge(175,174);
        metroMap.addEdge(175,176); //Keshav Puram
        metroMap.addEdge(175,199); //Shalimar Bagh
        metroMap.addEdge(175,200); //Shakurpur

        //Keshav Puram
        metroMap.addEdge(176,175);
        metroMap.addEdge(176,177); //Kanhaiya Nagar

        //Kanhaiya nagar
        metroMap.addEdge(177,176);
        metroMap.addEdge(177,178); //Inderlok

        //Inderlok
        metroMap.addEdge(178,177);
        metroMap.addEdge(178,179); //Shastri Nagar
        metroMap.addEdge(178,168); //Ashok Park Main

        //Shastri Nagar
        metroMap.addEdge(179,178);
        metroMap.addEdge(179,180); //Pratap Nagar

        //Pratap Nagar
        metroMap.addEdge(180,179);
        metroMap.addEdge(180,181); //Pul Bangash

        //Pul Bangash
        metroMap.addEdge(181,180);
        metroMap.addEdge(181,182); //Tis Hazari

        //Tis Hazari
        metroMap.addEdge(182,181);
        metroMap.addEdge(182,0); //Kashmere Gate

        //Shastri Park
        metroMap.addEdge(183,0); //Kashmere Gate
        metroMap.addEdge(183,184); //Seelampur

        //Seelampur
        metroMap.addEdge(184,183);
        metroMap.addEdge(184,185); //Welcome

        //Welcome(junction)
        metroMap.addEdge(185,184);
        metroMap.addEdge(185,186); //Shahdara
        metroMap.addEdge(185,221); //East Azad Nagar
        metroMap.addEdge(185,222); //Jaffrabad

        //Shahdara
        metroMap.addEdge(186,185);
        metroMap.addEdge(186,187); //Mansarovar Park

        //Mansarovar Park
        metroMap.addEdge(187,186);
        metroMap.addEdge(187,188); //Jhilmil

        //Jhilmil
        metroMap.addEdge(188,187);
        metroMap.addEdge(188,189); //Dilshad Garden

        //Dilshad Garden
        metroMap.addEdge(189,188);
        metroMap.addEdge(189,190); //Shaheed Nagar

        //Shaheed Nagar
        metroMap.addEdge(190,189);
        metroMap.addEdge(190,191); //Raj Bagh

        //Raj Bagh
        metroMap.addEdge(191,190);
        metroMap.addEdge(191,192); //Major Mohit Sharma

        //Major Mohit Sharma
        metroMap.addEdge(192,191);
        metroMap.addEdge(192,193); //Shyam Park

        //Shyam Park
        metroMap.addEdge(193,192);
        metroMap.addEdge(193,194); //Mohan Nagar

        //Mohan Nagar
        metroMap.addEdge(194,193);
        metroMap.addEdge(194,195); //Arthala

        //Arthala
        metroMap.addEdge(195,194);
        metroMap.addEdge(195,196); //Hindon River

        //Hindon River
        metroMap.addEdge(196,195);
        metroMap.addEdge(196,197); //Shaheed Sthal


        /*----Pink Line----*/

        //Majlis Park
        metroMap.addEdge(198,63); //Azadpur

        //Shalimar Bagh
        metroMap.addEdge(199,63);
        metroMap.addEdge(199,175); //Netaji Subhash Place

        //Shakurpur
        metroMap.addEdge(200,175); //Netaji Subhash Place
        metroMap.addEdge(200,201); //Pashchim Bagh West

        //Pashchim Bagh West
        metroMap.addEdge(201,200);
        metroMap.addEdge(201,202); //ESI

        //ESI
        metroMap.addEdge(202,201);
        metroMap.addEdge(202,91); //Rajouri Garden

        //Mayapuri
        metroMap.addEdge(203,91);
        metroMap.addEdge(203,204); //Naraina Vihar

        //Naraina Vihar
        metroMap.addEdge(204,203);
        metroMap.addEdge(204,205); //Delhi Cantt

        //Delhi Cantt
        metroMap.addEdge(205,204);
        metroMap.addEdge(205,206); //Durgabai Deshmukh

        //Duragabai Deshmukh
        metroMap.addEdge(206,205);
        metroMap.addEdge(206,207); //Sir M Vishwaraiah

        //Sir M Vishwaraih
        metroMap.addEdge(207,206);
        metroMap.addEdge(207,208); //Bhikaji Kama Place

        //Bhkaji Kama Place
        metroMap.addEdge(208,207);
        metroMap.addEdge(208,209); //Sarojini Nagar

        //Sarojini Nagar
        metroMap.addEdge(209,208);
        metroMap.addEdge(209,49); //INA

        //South Extension
        metroMap.addEdge(210,49);
        metroMap.addEdge(210,11); //Lajpat Nagar

        //Vinoba Puri
        metroMap.addEdge(211,11); //Lajpat Nagar
        metroMap.addEdge(211,212); //Ashram

        //Ashram
        metroMap.addEdge(212,211);
        metroMap.addEdge(212,213); //Sarai

        //Sarai
        metroMap.addEdge(213,212);
        metroMap.addEdge(213,106); //Mayur Vihar Phase 1

        //Mayur Vihar Pocket 1
        metroMap.addEdge(214,106);

        //TrilokPuri
        metroMap.addEdge(215,216); //East Vinod Nagar

        //East Vinod Nagar
        metroMap.addEdge(216,215);
        metroMap.addEdge(216,217); //Mandawali

        //Mandawali
        metroMap.addEdge(217,216);
        metroMap.addEdge(217,218); //IP Extension

        //IP Extension
        metroMap.addEdge(218,217);
        metroMap.addEdge(218,123); //Anand Vihar

        //Karkarduma Court
        metroMap.addEdge(219,124);
        metroMap.addEdge(219,220); //Krishna Nagar

        //Krishna Nagar
        metroMap.addEdge(220,219);
        metroMap.addEdge(220,221); //East AzadNagar

        //East Azadnagar
        metroMap.addEdge(221,220);
        metroMap.addEdge(221,185); //Welcome

        //Jafrabad
        metroMap.addEdge(222,185);
        metroMap.addEdge(222,223); //Maujpur

        //Maujpur
        metroMap.addEdge(223,222);
        metroMap.addEdge(223,224); //Gokulpuri

        //gokulpuri
        metroMap.addEdge(224,223);
        metroMap.addEdge(224,225); //Johri Enclave

        //Johri Enclave
        metroMap.addEdge(225,224);
        metroMap.addEdge(225,226); //Shiv Vihar


        /*----Megenta Line----*/

        //Dabri Mor
        metroMap.addEdge(227,86); //Janakpuri West
        metroMap.addEdge(227,228); //Dashrath Puri

        //Dashrath Puri
        metroMap.addEdge(228,227);
        metroMap.addEdge(228,229); //Palam

        //Palam
        metroMap.addEdge(229,228);
        metroMap.addEdge(229,230); //Sadar Bazar Cantonment

        //Sadar Bazar
        metroMap.addEdge(230,229);
        metroMap.addEdge(230,231); //Terminal IGI Airport

        //Terminal IGI Airport
        metroMap.addEdge(231,230);
        metroMap.addEdge(231,232); //Shankar Vihar

        //Shankar Vihar
        metroMap.addEdge(232,231);
        metroMap.addEdge(232,233); //Vasant Vihar

        //Vasant Vihar
        metroMap.addEdge(233,232);
        metroMap.addEdge(233,234); //Munirka

        //Munirka
        metroMap.addEdge(234,233);
        metroMap.addEdge(234,235); //RK Puram

        //RK Puram
        metroMap.addEdge(235,234);
        metroMap.addEdge(235,236); //IIT

        //IIT
        metroMap.addEdge(236,235);
        metroMap.addEdge(236,46); //Hauz Khaas

        //Panscheel Park
        metroMap.addEdge(237,46);
        metroMap.addEdge(237,238); //Chirag Delhi

        //Chirag Delhi
        metroMap.addEdge(238,237);
        metroMap.addEdge(238,239); //Greater Kailash

        //Greater Kailash
        metroMap.addEdge(239,238);
        metroMap.addEdge(239,240); //Nehru Enclave

        //Nehru Enclave
        metroMap.addEdge(240,239);
        metroMap.addEdge(240,15); //Kalkaji Mandir

        //Okhla NSiC
        metroMap.addEdge(241,15);
        metroMap.addEdge(241,242); //Sukhdev Vihar

        //Sukhdev Vihar
        metroMap.addEdge(242,241);
        metroMap.addEdge(242,243); //Jamia Millia

        //Jamia Millia
        metroMap.addEdge(243,242);
        metroMap.addEdge(243,244); //Okhla Vihar

        //Okhla Vihar
        metroMap.addEdge(244,243);
        metroMap.addEdge(244,245); //Jasola Vihar

        //Jasola Vihar
        metroMap.addEdge(245,244);
        metroMap.addEdge(245,246); //Kalindi Kunj

        //Kalindi Kunj
        metroMap.addEdge(246,245);
        metroMap.addEdge(246,247); //Okhla Bird Sanctuary

        //Okhla Bird Sanctuary
        metroMap.addEdge(247,246);
        metroMap.addEdge(247,112); //Botanical Garden


    }

    public void ListOfRoute(String source, String destination) {
        int src, dest;
        src = search(source);
        dest = search(destination);

        // use something like: metroMap.get(position).getStationGraphNode;
        metroMap.printAllPaths(src, dest);
        ArrayList<ArrayList<Integer>> allRouteArrayList = new ArrayList<ArrayList<Integer>>();

        allRouteArrayList = metroMap.getAllPaths();

        Log.d("okok", "All paths:" + allRouteArrayList);
        // passing arrayList to intent
        Intent intent = new Intent(getApplicationContext(), FareAndRouteList.class);

        // passing the number of paths
        intent.putExtra("noOfArrayLists", allRouteArrayList.size());

        // passing individual paths
        for (int i = 0; i < allRouteArrayList.size(); i++) {
            intent.putExtra("pathArrayList" + i, allRouteArrayList.get(i));
        }
        Log.d("route", "starting intent");
        startActivity(intent);
    }


    public int search(String s) {
        int index = 0;
        for (int i = 0; i < stationNameList.length; i++)
            if (s.equals(stationNameList[i]))
                index = i;
        Log.d("route", "inside search, index: " + index);
        return index;
    }

    public void clearValue() {
        list.clear();
        count = 0;
        change = 0;
    }

   public String[] nodeToStation(ArrayList<Integer> route, int length) {
        String[] stationName = new String[length];
        for (int i = 0; i < length; i++) {
            stationName[i] = stationNameList[route.get(i)];
        }
        return stationName;
    }

    public int[] nodeArrayListToNodeArray(ArrayList<Integer> route, int length) {
        int[] stationNode = new int[length];
        for (int i = 0; i < length; i++) {
            stationNode[i] = route.get(i);
        }
        return stationNode;
    }


    public class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String stationName[];
        int stationNode[];

        MyAdapter(Context c, String name[], int node[]) {
            super(c, R.layout.station_row_final, R.id.tv_stationName, name);
            this.context = c;
            this.stationName = name;
            this.stationNode = node;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.station_row_final, parent, false);
            TextView sName = row.findViewById(R.id.tv_stationName);
            TextView sNode = row.findViewById(R.id.tv_stationNode);

            // now set our resources on views
            sName.setText(stationName[position]);
            sNode.setText("Node Number" + stationNode[position]);

            return row;
        }



   /* public void ListOfRoute()
    {
        String s1 = actvSource.getText().toString().trim();
        String d1 = actvDes.getText().toString().trim();
        Intent intent =new Intent(this,FareAndRouteList.class);
        intent.putExtra("ARRAYLIST", list);
        intent.putExtra("COUNT", count);
        intent.putExtra("CHANGE", change);
        startActivity(intent);

    }*/

    }
}






