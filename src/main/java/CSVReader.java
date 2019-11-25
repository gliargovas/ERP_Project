import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

    public class CSVReader {

        //Method predicts sales of the month/week/etc according to the previous sales ("exponential smoothing" method)
        public static double predictSales(){
            String csvFile = "(//Insert File Location path)"; //csv_file location, file with sales and predictions
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";
            try {
                br = new BufferedReader(new FileReader(csvFile));
                final double a = 0.5;// a is a constant. It can change, according yo the type of product we sell
                int lineCounter = 0;//counts lines of csv_file
                double ft=0; //The sales prediction
                while ((line = br.readLine()) != null) {
                    // use comma as separator
                    String[] salePrediction = line.split(cvsSplitBy);
                    lineCounter++;//finds final month
                    System.out.println(line);
                    if (lineCounter>1) {
                        System.out.println(lineCounter);
                        double At_1 = Double.parseDouble(salePrediction[1]);
                        double Ft_1 = Double.parseDouble(salePrediction[2]);
                        ft = a * At_1 + (1 - a) * Ft_1;
                        //System.out.println(ft);
                    }
                }
                //System.out.println("Prediction for month " + lineCounter + " is: "+ ft);
                return ft;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return 0;
        }


    }