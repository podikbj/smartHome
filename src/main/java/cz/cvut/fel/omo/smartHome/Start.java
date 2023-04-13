package cz.cvut.fel.omo.smartHome;

import java.io.*;

public class Start {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.txt");
        int a = 0;
        int b = 0;
        try (
                BufferedReader br = new BufferedReader(new FileReader(inputFile));) {
            String line = br.readLine();
            String[] itemsLine = line.split(" ");
            a = Integer.valueOf(itemsLine[0]);
            b = Integer.valueOf(itemsLine[1]);


        } catch (
                IOException ex) {

            ex.printStackTrace();
        }

        File outputFile = new File("output.txt");
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            writer.println(a + b);
            writer.flush();
            writer.close();

        } catch (
                IOException ex) {
            ex.printStackTrace();
        }
    }
}
