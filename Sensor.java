import java.util.*;
import java.io.*;

public class Sensor {
  private String category;
  private String name;
  private String filename;
  private double lowerBound;
  private double upperBound;
  private ArrayList<Double> data;

  public Sensor(String category, String name, String filename, double lowerBound, double upperBound) {
    this.category = category;
    this.name = name;
    this.filename = filename;
    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
    this.data = new ArrayList<>();

    try {
      File dataFile = new File(filename);
      BufferedReader reader = new BufferedReader(new FileReader(dataFile));
      String line;
      while((line = reader.readLine()) != null) {
        data.add(Double.parseDouble(line));
      }
      reader.close();
    } catch(FileNotFoundException e) {
      System.out.println("File doesn't exist.");
    } catch(IOException e) {
      System.out.println("Failed to read the file.");
    } catch(NumberFormatException e) {
      System.out.println("Failed to convert number from String to Double.");
    }
  }
  
  public String getCategory() {
    return this.category;
  }
  public String getName() {
    return this.name;
  }
  public boolean checkInBound(double value) {
    if(lowerBound <= value && value <= upperBound) {
      return true;
    } else {
      return false;
    }
  }
  public double getDataByIndex(int idx) {
    if(idx < data.size()) {
      return this.data.get(idx);
    } else {
      return -1.0;
    }
  }
}
