package com.example.mymetro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;

public class FareAndRouteList extends AppCompatActivity {


    int count, change;
    ArrayAdapter<String> adapter;
    ListView listView;
    TextView s1, c1, f1, t1;
    Spinner spinner;

    String[] stationNameList = new String[]



//1 Violet line, 34 stations, 60 rs, 76 min //towards kashmere gate, 5 junction

            {"KASHMERE GATE(junction)", "LAL QILA", "JAMA MASJID", "DELHI GATE", "ITO", "MANDI HOUSE(junction)", "JANPATH", "CENTRAL SECRETARIAT(junction)", "KHAN MARKET", "JAWAHARLAL NEHRU STADIUM", "JANGPURA", "LAJPAT NAGAR(junction)", "MOOLCHAND", "KAILASH COLONY", "NEHRU PLACE", "KALKAJI MANDIR(junction)", "GOVIND PURI", "HARKESH NAGAROKHLA", "JASOLA APOLLO", "SARITA VIHAR", "MOHAN ESTATE", "TUGHLAKABAD", "BADARPUR", "SARAI", "NHPC CHOWK", "MEWALA MAHARAJPUR", "SECTOR 28", "BADKAL MOR", "OLD FARIDABAD", "NEELAM CHOWK AJRONDA", "BATA CHOWK", "ESCORTS MUJESAR", "SANT SURDAS(SIHI)", "RAJA NAHAR SINGH",


//2 Yellow Line, 35 stations, 60 rs, 82 min //towards samaypur badli(Platform 2), 8 junction

                    "HUDA CITY CENTRE", "IFFCO CHOWK", "M G ROAD", "SIKANDERPUR(junction)", "GURU DRONACHARYA", "ARJANGARH", "GHITORNI", "SULTANPUR", "CHHATTARPUR", "QUTAB MINAR", "SAKET", "MALVIYA NAGAR", "HAUZ KHAS(junction)", "GREEN PARK", "AIIMS", "INA(junction)", "JORBAGH", "LOK KALYAN MARG", "UDYOG BHAWAN", "PATEL CHOWK", "RAJIV CHOWK(junction)", "NEW DELHI(junction)", "CHAWRI BAZAR", "CHANDNI CHOWK","CIVIL LINES", "VIDHAN SABHA", "VISHWAVIDYALAYA", "G.T.B. NAGAR", "MODEL TOWN", "AZADPUR(junction)", "ADARSH NAGAR", "JAHANGIRPURI", "HAIDERPUR BADLI MOR", "ROHINI SECTOR 18", "SAMAYPUR BADLI",


//For Orange Line //Blue line until Rajiv chowk (Yellow line), 30 stations,60 rs,68 min , 1 change ,towards noida elec city(platform 1), if route through without airport line.

//3 Orange line or Airport line , 6 stations, 60 rs, 26 min, Towards New Delhi(Platform 3), if route through with airport line.

                    "DWARKA SECTOR 21", "IGI AIRPORT", "DELHI AERO CITY", "DHAULA KUAN(junction)", "SHIVAJI STADIUM",


//4 Blue line 1, 50 stations , 60 rs, 101 min, Towards Noida elec city (Platform 1) , if route through without airport line.,10 junction

                    "DWARKA SEC 8", "DWARKA SEC 9", "DWARKA SEC 10", "DWARKA SEC 11", "DWARKA SEC 12", "DWARKA SEC 13", "DWARKA SEC 14", "DWARKA", "DWARKA MOR", "NAWADA", "UTTAM NAGAR WEST", "UTTAM NAGAR EAST", "JANAK PURI WEST(junction)", "JANAK PURI EAST", "TILAK NAGAR", "SUBHASH NAGAR", "TAGORE GARDEN", "RAJOURI GARDEN(junction)", "RAMESH NAGAR", "MOTI NAGAR", "KIRTI NAGAR(junction)", "SHADIPUR", "PATEL NAGAR", "RAJENDRA PLACE", "KAROL BAGH", "JHANDEWALAN", "RK ASHRAM MARG", "BARAKHAMBA", "PRAGATI MAIDAN", "INDRAPRASTHA", "YAMUNA BANK(junction)", "AKSHARDHAM", "MAYUR VIHAR I(junction)", "MAYUR VIHAR EXT", "NEW ASHOK NAGAR", "NOIDA SEC 15", "NOIDA SEC 16", "NOIDA SEC 18", "BOTANICAL GARDEN(junction)", "GOLF COURSE", "NOIDA CITY CENTRE", "NOIDA SEC 34", "NOIDA SEC 52(junction)", "NOIDA SEC 61", "NOIDA SEC 59", "NOIDA SEC 62", "NOIDA ELECTRONIC CITY",

//5 Blue Line 2 ,8 stations , 30 rs , 15 min , Towards Dwarka(platform 1), 3 junction

                    "VAISHALI", "KAUSHAMBI", "ANAND VIHAR(junction)", "KARKAR DUMA(junction)", "PREET VIHAR", "NIRMAN VIHAR", "LAXMI NAGAR",

//6 Aqua Line , 21 stations

                    "NOIDA SECTOR 51","NOIDA SECTOR 50","NOIDA SECTOR 76","NOIDA SECTOR 101","NOIDA SECTOR 81","NSEZ","NOIDA SECTOR 83","NOIDA SECTOR 137","NOIDA SECTOR 142","NOIDA SECTOR 143","NOIDA SECTOR 144","NOIDA SECTOR 145","NOIDA SECTOR 146","NOIDA SECTOR 147","NOIDA SECTOR 148","KNOWLEDGE PARK 2","PARI CHOWK","ALPHA 1","DELTA 1","GREATER NOIDA(GNOIDA) OFFICE","DEPOT STATION",

//7 Green Line 1, 22 stations, 50 rs, 45 min , Towards Kirti Nagar(Platform 2)

                    "BRIGADIER HOSHIYAR(Bahadurgarh City Park)", "BAHADURGARH CITY", "PANDIT SHREE RAM SHARMA", "TIKRI BORDER", "TIKRI KALAN", "GHEVRA METRO STATION", "MUNDKA INDUSTRIAL AREA", "MUNDKA", "RAJDHANI PARK", "NANGLOI RLY. STATION", "NANGLOI", "SURAJMAL STADIUM", "UDYOG NAGAR", "PEERA GARHI", "PASCHIM VIHAR (WEST)", "PASCHIM VIHAR (EAST)", "MADI PUR", "SHIVAJI PARK", "PUNJABI BAGH", "ASHOK PARK MAIN(Junction)","SATGURU RAM SINGH MARG",



//8 Red line, 29 stations, 50 rs, 59 min, Towards shaheed sthal Platform 1, 4 junction

                    "RITHALA", "ROHINI WEST", "ROHINI EAST", "PITAM PURA", "KOHAT ENCLAVE", "NETAJI SUBHASH PLACE(junction)", "KESHAV PURAM", "KANHAIYA NAGAR", "INDER LOK(junction)", "SHASTRI NAGAR", "PRATAP NAGAR", "PUL BANGASH", "TIS HAZARI", "SHASTRI PARK", "SEELAMPUR", "WELCOME(junction)", "SHAHDARA", "MANSAROVAR PARK", "JHIL MIL", "DILSHAD GARDEN", "SHAHID NAGAR", "RAJ BAGH", "MAJOR MOHIT SHARMA", "SHYAM PARK", "MOHAN NAGAR", "ARTHALA", "HINDON", "SHAHEED STHAL",

//9 Pink Line //Towards Mayur Vihar Pocket 1(Platform 1), 3 junction

                    "MAJLIS PARK", "SHALIMAR BAGH", "SHAKURPUR", "PUNJABI BAGH WEST", "ESI BASAIDARPUR", "MAYAPURI", "NARAINA VIHAR", "DELHI CANTONMENT", "DURGABAI DESHMUKH SOUTH CAMPUS", "SIR VISHWESHWARAIAH MOTI BAGH", "BHIKAJI CAMA PLACE", "SAROJINI NAGAR", "SOUTH EXTENSION", "VINOBAPURI", "ASHRAM", "SARAI KALE KHAN NIZAMUDDIN", "MAYUR VIHAR POCKET I", "TRILOKPURI SANJAY LAKE", "EAST VINOD NAGAR-Mayur Vihar-II", "MANDAWALI-West Vinod Nagar", "IP EXTENSION", "KARKARDUMA COURT", "KRISHNA NAGAR", "EAST AZAD NAGAR", "JAFFRABAD", "MAUJPUR-BABARPUR", "GOKULPURI", "JOHRI ENCLAVE", "SHIV VIHAR",

//10 Megenta Line,25 stations,50 rs,59 min //Towards Botanical Garden(Platform 3),4 junction
                    "DABRI MOR-JANAKPURI SOUTH", "DASHRATH PURI", "PALAM", "SADAR BAZAAR CANTONMENT", "TERMINAL 1-IGI AIRPORT", "SHANKAR VIHAR", "VASANT VIHAR", "MUNIRKA", "R.K PURAM", "IIT DELHI", "PANCHSHEEL PARK", "CHIRAG DELHI", "GREATER KAILASH", "NEHRU ENCLAVE", "OKHLA NSIC", "SUKHDEV VIHAR", "JAMIA MILLIA ISLAMIA", "OKHLA VIHAR", "JASOLA VIHAR SHAHEEN BAGH", "KALINDI KUNJ", "OKHLA BIRD SANCTUARY"};



    int[] stationNodeList = new int[]{/*1*/0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,
            /*2*/34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,
            /*3*/69,70,71,72,73,
            /*4*/74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,
            /*5*/121,122,123,124,125,126,127,
            /*6*/128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,
            /*7*/149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,
            /*8*/170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,
            /*9*/198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,
            /*10*/227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247};
    int[] stationInterchange = new int[]{1,0,1,0,0,0,1,0,0,1,0,1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare_and_route_list);

        listView = (ListView) findViewById(R.id.listroute);
        spinner = (Spinner) findViewById(R.id.spinner);
        s1 = (TextView) findViewById(R.id.stations);
        c1 = (TextView) findViewById(R.id.change);
        f1 = (TextView) findViewById(R.id.fare);
        t1 = (TextView) findViewById(R.id.time);

        String currTime = getTime();
//        tv_titleBar.setText(currTime);
        Log.d("okok", "Current time: " + currTime);

        int numArrayList;
        final ArrayList <ArrayList <Integer> > allRouteArrayList;


        //getting data from intent
        numArrayList  = getIntent().getIntExtra("noOfArrayLists", 2);
        allRouteArrayList = new ArrayList<>(numArrayList);
        String[] routes = new String[numArrayList];

        for(int i = 0; i < numArrayList; i++){
            routes[i] = "Route " + (i+1);
            ArrayList<Integer> arrayList = getIntent().getIntegerArrayListExtra("pathArrayList"+i);
            allRouteArrayList.add(arrayList);
        }
        sortBubble(allRouteArrayList);


        // storing only 3 routes at maximum
        String[] routes3only;
        if(numArrayList > 2){
            routes3only = new String[2];

            for(int i = 0; i < 2; i++) {
                routes3only[i] = routes[i] + "";
                Log.d("okok", "here");
            }

            // set numArrayList = 3
            numArrayList = 2;
        }
        else {

            routes3only = new String[numArrayList];
            for(int i = 0; i < numArrayList; i++)
                routes3only[i] = routes[i] + "";
        }

        int timeEfficient = getTimeEfficientRouteIndex(allRouteArrayList, numArrayList);

        routes3only[timeEfficient] += " (Time Efficient)";
        Log.d("okok", "Time Eff: " + timeEfficient);


        //setting the spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, routes3only);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                int size = allRouteArrayList.get(position).size();

                String stationNameArray[] = nodeToStation(allRouteArrayList.get(position), size);
                int stationNodeArray[] = nodeArrayListToNodeArray(allRouteArrayList.get(position), size);

                int time = calculateTime(allRouteArrayList.get(position), size);

                adapter = new MyAdapter(getApplicationContext(), stationNameArray, stationNodeArray);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                s1.setText("" + stationNameArray.length);
                f1.setText("Rs. 0");
                t1.setText("" + time + " min");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    // creating a custom array adapter class
    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String stationName[];
        int stationNode[];


        MyAdapter (Context c, String name[], int node[]) {
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
            sNode.setText("Node number " + stationNode[position]);

            return row;
        }
    }

    public String[] nodeToStation(ArrayList<Integer> route, int length){
        String[] stationName = new String[length];
        for(int i = 0; i < length; i++){
            stationName[i] = stationNameList[route.get(i)];
        }
        return stationName;
    }


    public int[] nodeArrayListToNodeArray(ArrayList<Integer> route, int length){
        int[] stationNode = new int[length];
        for(int i=0; i<length; i++){
            stationNode[i] = route.get(i);
        }
        return stationNode;
    }

    public void sortBubble(ArrayList <ArrayList<Integer>> allPaths){
        int size  = allPaths.size();
        for(int i=0; i < size; i++){
            for(int j=0; j < size-1-i; j++){
                if(allPaths.get(j).size() > allPaths.get(j+1).size()){
                    Collections.swap(allPaths, j, j+1);
                }
            }
        }
    }


    public int calculateTime(ArrayList<Integer> route, int length) {

        // Time criteria: 2 min at all stations except at interchange and 3 min at interchange

        // Logic: 2 min x no. of stations + 1 min for each interchange
        int time = 2*length;

        for(int i=0; i<length; i++){

            // check if current station is an interchangeable station
            // if interchangeable stations at first and last, do not consider them as interchange
           if((stationInterchange[route.get(i)] == 1) && (i != 0) && (i != length-1)){

                // if interchangeable station, then check if the path has an interchange
                // for this, check if the lineColor of previous and next station is same or not. If not, it is an interchange
               // if(stationColorCode[route.get(i-1)] != stationColorCode[route.get(i+1)]){

                    // It is an interchange
                    // Increment time by 1 min.
                    time++;
                }
            }


        //return the time
        return time;
    }


    public int getTimeEfficientRouteIndex(ArrayList <ArrayList <Integer> > allroutes, int n) {

        int[] time = new int[n];
        for(int i = 0; i < n; i++){
            time[i] = calculateTime(allroutes.get(i), allroutes.get(i).size());
            Log.d("Time","Vlaue of time is "+ time);
        }
        int minInd = 0;
        for(int i = 1; i < n; i++)
            if(time[i] < time[minInd])
                minInd = i;

        return minInd;
    }


    public String getTime(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+5:30"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mm:ss");
        date.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String localTime = date.format(currentLocalTime);

        return localTime;
    }

}








