package com.example.travellab.mainJava;
import java.util.*;

public class main {

    //Access javaDB class through instantiation
    javaDB main = new javaDB();
    //Get the String array of the scraped destinations
    String [] destResult = main.dataScraper();

    public String[] randUniqDestGen(int size) {
        String[] newstr = new String[size];
        int[] a = new int[size];
        //Generate unique random numbers <19
        for (int i = 0; i < size; i++) {
            a[i] = (int)(Math.random()*19);//Generates numbers from [0,18]
            for (int j = 0; j < i; j++) {
                if (a[i] == a[j]) {
                    i--; //If a[i] is a duplicate of a[j], then run the outer loop on i again
                    break;
                }
            }
        }
        //Assign those numbers to get the destinations
        for(int i=0;i<newstr.length;i++){
            //18 destinations
            newstr[i] = destResult[a[i]];
        }
        return newstr;
    }

    //function for Default Dijkstra algorithm
    //takes in String array of randomized destination list
    public String [] dijkstraAlg(String []a) {
        String[] result = new String[9];
        int[] cont = new int[9];
        int count=0;
        //0, 1, 2
        for(int i = 0; i< a.length-1; i++){
            //1, 2, 3
            for(int j = i+1; j< a.length; j++){
                cont[count] = main.getDistance(Arrays.asList(destResult).indexOf(a[i]),
                        Arrays.asList(destResult).indexOf(a[j])); //Get the distance from Origin to Destination
                //Skip if origin equals to destination
                if((cont[count]!=0)){
                    result[count] = a[i] + "," + a[j] + "=" + cont[count];
                    count++;
                }
            }
        }

        //remove null value from the array
        List<String> list = new ArrayList<String>();
        for(int i=0; i<result.length; i++){
            if(result[i] != null && result[i].length() > 0) {
                list.add(result[i]);
            }
        }

        //find the first minimum distance
        //break the loop if found
        Arrays.sort(cont);
        int minDist=cont[0];
        for(int x: cont){
            if(x!=0){
                minDist += x;
                break;
            }
        }

        //to prevent, example =1 and =10, be considered the same
        if(minDist<10)
            minDist = Integer.parseInt(String.format("%02d", minDist));

        String call = "="+Integer.toString(minDist);
        String minPair = "";
        //find the String array containing the minimum distance value
        for (String x:result) {
            if(x==null){
                continue;
            }else if(x.contains(call)){
                minPair = x;
                break;
            }
        }

        //Get an array of minimum origin and destination pair
        String[] minResRoute = minPair.split("=")[0].split(",");

        //move the minimum pair up and place the rest below
        ArrayList<String> oriResult = new ArrayList<String>();
        oriResult.add(a[0]);
        oriResult.add(a[1]);
        oriResult.add(a[2]);
        oriResult.add(a[3]);

        ArrayList<String> sortedResult = new ArrayList<String>();
        sortedResult.add(minResRoute[0]);
        sortedResult.add(minResRoute[1]);

        oriResult.removeAll(sortedResult);
        ArrayList<String> combineResult= new ArrayList<String>();
        combineResult.addAll(sortedResult);
        combineResult.addAll(oriResult);
        //Assign combined arraylist to String array
        String [] sortedMinDest = new String[combineResult.size()];
        for (int i = 0; i < combineResult.size(); i++) {
            // Assign each value to String array
            sortedMinDest[i] = combineResult.get(i);
        }

        //sortedMinDest is the end result up till here
        //Possible combination route of 4 destinations
        String [] possibleModels  = new String[]{"0123", "0132" ,"1023", "1032"};
        int [] totalDistance = new int[4];
        int countTotal =0;

        //Dest 1,2,3,4,total dist
        //Set A 0123  Set B 0132  Set C 1023  Set D 1032
        String[][] mainresult = new String[4][5];
        for(int i=0; i<4; i++){
            for(int j=0; j<mainresult[i].length-1; j++){
                mainresult[i][j] = sortedMinDest[Integer.parseInt(possibleModels[i].substring(j,j+1))];
            }
            for(int k=0; k<3; k++){
                countTotal+=(main.getDistance(Arrays.asList(destResult).indexOf(mainresult[i][k]),
                        Arrays.asList(destResult).indexOf(mainresult[i][k+1])));
            }
            mainresult[i][mainresult[i].length-1]=Integer.toString(countTotal);
            totalDistance[i] = countTotal;
            countTotal=0;
        }

        Arrays.sort(totalDistance);
        //If there are similar route, pick the first set and break the loop
        //Find the total minimum path cost
        int indexEndResult = 0;
        for(int i=0; i<mainresult.length; i++){
            if(Integer.parseInt(mainresult[i][4])==totalDistance[0]){
                indexEndResult += i;
                break;
            }
        }

        //Format the output
        String [] endResult = new String[mainresult[indexEndResult].length];
        for(int i=0; i<endResult.length; i++){
            if(i!=endResult.length-1){
                endResult[i]=String.format("Destination %s is %s", Integer.toString(i+1), mainresult[indexEndResult][i]);
            }else{
                endResult[i]=String.format("Total Distance is %s", mainresult[indexEndResult][i]);
            }
        }

        return endResult;
    }

    public String[] mainCall(){
        return dijkstraAlg(randUniqDestGen(4));
    }
}
