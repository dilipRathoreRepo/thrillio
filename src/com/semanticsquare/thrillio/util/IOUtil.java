package com.semanticsquare.thrillio.util;

import java.io.*;

public class IOUtil {
    public static void read(String[] data, String filename){
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))){
            String line;
            int count = 0;
            while((line = br.readLine()) != null){
                data[count] = line;
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(InputStream in) {
        StringBuilder text = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"))){
            String line;
            while((line = br.readLine()) != null){
                text.append(line).append("\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public static void write(String webpage, long id) {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/diliprathore/IdeaProjects/thrillio/pages/" + String.valueOf(id)), "UTF-8"));
            bw.write(webpage);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
