/**
 * @file: Proj4.java
 * @description: This program creates a series of hashmaps and records the running times for inserting, searching, and deleting within them
 * @author: Tucker Corwen
 * @date: December 5, 2024
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        //Create a Scanner class that reads the data csv
        Scanner csvScanner = new Scanner(new File(inputFileName));
        //Create ArrayList
        ArrayList<Candidate> candidates = new ArrayList<>();

        boolean isFirstLine = true;

        for (int i = 0; i <= numLines; i++) {

            //Read CSV line
            String line = csvScanner.nextLine().trim();

            if(!isFirstLine) {
                //Create empty string array
                String[] info = null;

                //If line is empty move to next line
                if (line.isEmpty()) continue;

                //Split line into sections that are bounded by commas
                info = line.split(",", -1);


                //Check csv data points, if missing create N/A or 0 entries
                float year = Float.parseFloat(info[0].isEmpty() ? "0" : info[0]);
                String name = info[1].isEmpty() ? "N/A" : info[1];
                String college = info[2].isEmpty() ? "N/A" : info[2];
                String position = info[3].isEmpty() ? "N/A" : info[3];

                float heightInches = 0;
                if (isNumeric(info[4])) {
                    Float.parseFloat(info[4].isEmpty() ? "0" : info[4]);
                }

                float weightLbs = Float.parseFloat(info[5].isEmpty() ? "0" : info[5]);
                float handSize = Float.parseFloat(info[6].isEmpty() ? "0" : info[6]);
                float armLength = Float.parseFloat(info[7].isEmpty() ? "0" : info[7]);
                float wonderlic = Float.parseFloat(info[8].isEmpty() ? "0" : info[8]);
                float fortyYard = Float.parseFloat(info[9].isEmpty() ? "0" : info[9]);
                float benchPress = Float.parseFloat(info[10].isEmpty() ? "0" : info[10]);
                float verticalLeap = Float.parseFloat(info[11].isEmpty() ? "0" : info[11]);
                float broadJump = Float.parseFloat(info[12].isEmpty() ? "0" : info[12]);
                float shuttle = Float.parseFloat(info[13].isEmpty() ? "0" : info[13]);
                float threeCone = Float.parseFloat(info[14].isEmpty() ? "0" : info[14]);
                float sixtyYardShuttle = Float.parseFloat(info[15].isEmpty() ? "0" : info[15]);

                //Create the object and set the values
                Candidate newCandidate = new Candidate(info);
                newCandidate.setYear(year);
                newCandidate.setName(name);
                newCandidate.setCollege(college);
                newCandidate.setPos(position);
                newCandidate.setHeight_in(heightInches);
                newCandidate.setWeight_lbs(weightLbs);
                newCandidate.setHand_size_in(handSize);
                newCandidate.setArm_length_in(armLength);
                newCandidate.setWonderlic(wonderlic);
                newCandidate.setForty_yard(fortyYard);
                newCandidate.setBench_press(benchPress);
                newCandidate.setVert_leap_in(verticalLeap);
                newCandidate.setBroad_jump_in(broadJump);
                newCandidate.setShuttle(shuttle);
                newCandidate.setThree_cone(threeCone);
                newCandidate.setSixty_yd_shuttle(sixtyYardShuttle);

                //Insert object to ArrayList
                candidates.add(newCandidate);
            }
            isFirstLine = false;
        }

        //CREATE DIFFERENT ARRAY TYPES
        //Create sorted Array
        Collections.sort(candidates);
        ArrayList<Candidate> candidates_sorted = new ArrayList<>(candidates);

        //Create shuffled Array
        Collections.shuffle(candidates);
        ArrayList<Candidate> candidates_shuffled = new ArrayList<>(candidates);

        //Create reversed Array
        Collections.sort(candidates, Collections.reverseOrder());
        ArrayList<Candidate> candidates_reversed = new ArrayList<>(candidates);

        //PRINT EVERYTHING
        FileWriter writer = new FileWriter("analysis.txt", true);
        PrintWriter printWriter = new PrintWriter(writer);

        //Create Hash Tables
        SeparateChainingHashTable sortedhashTable = new SeparateChainingHashTable();
        SeparateChainingHashTable shuffledhashTable = new SeparateChainingHashTable();
        SeparateChainingHashTable reversehashTable = new SeparateChainingHashTable();






        //INSERT / SEARCH / DELETE - SORTED

        //Insert
        long startTime = System.nanoTime();
        for(int i = 0; i < candidates_sorted.size(); i++) {
            sortedhashTable.insert(candidates_sorted.get(i));
        }
        long endTime = System.nanoTime();
        long sorted_insert_duration = endTime - startTime;

        //Search
        startTime = System.nanoTime();
        for(int i = 0; i < candidates_sorted.size(); i++) {
            sortedhashTable.contains(candidates_sorted.get(i));
        }
        endTime = System.nanoTime();
        long sorted_search_duration = endTime - startTime;

        //Delete
        startTime = System.nanoTime();
        for(int i = 0; i < candidates_sorted.size(); i++) {
            sortedhashTable.remove(candidates_sorted.get(i));
        }
        endTime = System.nanoTime();
        long sorted_delete_duration = endTime - startTime;


        //Print to analysis.txt
        printWriter.println("-------------------------------------------------------");
        printWriter.println("SORTED ARRAY results for " + numLines + " lines:");
        printWriter.println("Insert runtime (ns): " + sorted_insert_duration);
        printWriter.println("Search runtime (ns): " + sorted_search_duration);
        printWriter.println("Delete runtime (ns): " + sorted_delete_duration);
        printWriter.println("-------------------------------------------------------");

        //Print to screen
        System.out.println("-------------------------------------------------------");
        System.out.println("SORTED ARRAY results for " + numLines + " lines:");
        System.out.println("Insert runtime (ns): " + sorted_insert_duration);
        System.out.println("Search runtime (ns): " + sorted_search_duration);
        System.out.println("Delete runtime (ns): " + sorted_delete_duration);
        System.out.println("-------------------------------------------------------");

        //INSERT / SEARCH / DELETE - SHUFFLED

        //Insert
        startTime = System.nanoTime();
        for(int i = 0; i < candidates_sorted.size(); i++) {
            shuffledhashTable.insert(candidates_shuffled.get(i));
        }
        endTime = System.nanoTime();
        long shuffled_insert_duration = endTime - startTime;

        //Search
        startTime = System.nanoTime();
        for(int i = 0; i < candidates_sorted.size(); i++) {
            shuffledhashTable.contains(candidates_shuffled.get(i));
        }
        endTime = System.nanoTime();
        long shuffled_search_duration = endTime - startTime;

        //Delete
        startTime = System.nanoTime();
        for(int i = 0; i < candidates_sorted.size(); i++) {
            shuffledhashTable.remove(candidates_shuffled.get(i));
        }
        endTime = System.nanoTime();
        long shuffled_delete_duration = endTime - startTime;


        //Print to analysis.txt
        printWriter.println("-------------------------------------------------------");
        printWriter.println("SHUFFLED ARRAY results for " + numLines + " lines:");
        printWriter.println("Insert runtime (ns): " + shuffled_insert_duration);
        printWriter.println("Search runtime (ns): " + shuffled_search_duration);
        printWriter.println("Delete runtime (ns): " + shuffled_delete_duration);
        printWriter.println("-------------------------------------------------------");

        //Print to screen
        System.out.println("-------------------------------------------------------");
        System.out.println("SHUFFLED ARRAY results for " + numLines + " lines:");
        System.out.println("Insert runtime (ns): " + shuffled_insert_duration);
        System.out.println("Search runtime (ns): " + shuffled_search_duration);
        System.out.println("Delete runtime (ns): " + shuffled_delete_duration);
        System.out.println("-------------------------------------------------------");

        //INSERT / SEARCH / DELETE - REVERSED

        //Insert
        startTime = System.nanoTime();
        for(int i = 0; i < candidates_sorted.size(); i++) {
            reversehashTable.insert(candidates_reversed.get(i));
        }
        endTime = System.nanoTime();
        long reversed_insert_duration = endTime - startTime;

        //Search
        startTime = System.nanoTime();
        for(int i = 0; i < candidates_sorted.size(); i++) {
            reversehashTable.contains(candidates_reversed.get(i));
        }
        endTime = System.nanoTime();
        long reversed_search_duration = endTime - startTime;

        //Delete
        startTime = System.nanoTime();
        for(int i = 0; i < candidates_sorted.size(); i++) {
            reversehashTable.remove(candidates_reversed.get(i));
        }
        endTime = System.nanoTime();
        long reversed_delete_duration = endTime - startTime;


        //Print to analysis.txt
        printWriter.println("-------------------------------------------------------");
        printWriter.println("REVERSED ARRAY results for " + numLines + " lines:");
        printWriter.println("Insert runtime (ns): " + reversed_insert_duration);
        printWriter.println("Search runtime (ns): " + reversed_search_duration);
        printWriter.println("Delete runtime (ns): " + reversed_delete_duration);
        printWriter.println("-------------------------------------------------------");

        //Print to screen
        System.out.println("-------------------------------------------------------");
        System.out.println("REVERSED ARRAY results for " + numLines + " lines:");
        System.out.println("Insert runtime (ns): " + reversed_insert_duration);
        System.out.println("Search runtime (ns): " + reversed_search_duration);
        System.out.println("Delete runtime (ns): " + reversed_delete_duration);
        System.out.println("-------------------------------------------------------");

        //Close writer
        printWriter.close();

    }

    // Method to check if the string is numeric
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
