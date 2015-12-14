package com.example.keng.rockscissorpaper;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Keng on 2015-12-12.
 */
public class Request {

    public static Socket socket;
    public static String ip;
    public static ArrayList<User> users = new ArrayList<User>();
    private static ArrayList<String> temp = new ArrayList<String>();
    private static String sendMsg;


    static class asyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
            socket = new Socket(ip, 9090);
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(sendMsg);
            Thread.sleep(500);
            InputStreamReader reader = new InputStreamReader(socket.getInputStream());
            BufferedReader input = new BufferedReader(reader);
            parseInput(input);
            socket.close();
            } catch(IOException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onPostExecute(Void aVoid) {
        }
    }

    public static void send(String msg) throws IOException, InterruptedException {
            sendMsg = msg;
            new asyncTask().execute();

    }





    private static void parseInput(BufferedReader input) {
        try {
            String i = input.readLine();
            switch (i) {
                case "names":
                    getNames(input);

                    break;
                case "update":
                    getNames(input);

                    break;
                case "result":
                    addWeapon(input);
                    calculateScore();
                    break;
                case "ok":
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void getNames(BufferedReader input) throws IOException {

        String line = null;
        while ((line = input.readLine()) != null) {
            temp.add(line);
            if (line.length() > 1 && getIndex(line) == -1) {
                users.add(new User(line));
            }
        }
        checkIfGone();
        temp.clear();
    }

    private static void addWeapon(BufferedReader input) throws IOException {
        String line = null;
        while ((line = input.readLine()) != null) {
            if (line.length() > 1) {
                int index = getIndex(line);
                users.get(index).addWeapon(input.readLine());
            }
        }
    }

    private static int getIndex(String line) {
        int index = -1;

        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).getName().equals(line)) {
                index = i;
            }
        }
        return index;
    }
    private static void calculateScore() {

        for (int i = 0; i < users.size(); i++) {
            switch (users.get(i).getWeapon()) {
                case "rock":
                    for(User j : users) {
                        if (j.getWeapon().equals("scissor")) {
                            users.get(i).increaseScore();
                        }
                    }
                    break;
                case "paper":
                    for(User j : users) {
                        if (j.getWeapon().equals("rock")) {
                            users.get(i).increaseScore();
                        }
                    }
                    break;
                case "scissor":
                    for(User j : users) {
                        if (j.getWeapon().equals("paper")) {
                            users.get(i).increaseScore();
                        }
                    }
                    break;
            }
        }
    }

    private static void checkIfGone() {

        for(int i = 0; i < users.size(); i++) {
            boolean equal = false;
            for (int j = 0; j < temp.size(); j++) {
                if (temp.get(j).equals(users.get(i).getName())) {
                    equal = true;
                }
                if (j == temp.size()-1 && equal == false) {
                    users.remove(i);
                }
            }
        }
    }

}
