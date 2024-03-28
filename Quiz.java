import java.util.*;
import java.io.*;

public class Quiz {
  public static void main(String[] args) {
    if(args.length != 1) {
      System.out.println("Input Error");
      return;
    }

    int monitorPeriod = 0;
    ArrayList<Patient> patients = new ArrayList<>();

    try {
      File dataFile = new File(args[0]);
      BufferedReader reader = new BufferedReader(new FileReader(dataFile));

      String line = reader.readLine();
      monitorPeriod = Integer.parseInt(line);

      while((line = reader.readLine()) != null) {
        String[] lineArgs = line.split(" ");
        if(lineArgs.length == 3) {
          if(lineArgs[0].equals("patient")) {
            int period = Integer.parseInt(lineArgs[2]);
            patients.add(new Patient(lineArgs[1], period));
          } else {
          // 
          }
        } else if(lineArgs.length == 5) {
          if(lineArgs[0].equals("PulseSensor") || lineArgs[0].equals("BloodPressureSensor") || lineArgs[0].equals("TemperatureSensor")) {
            if(patients.size() > 0) {
              int lowerBound = Integer.parseInt(lineArgs[3]);
              int upperBound = Integer.parseInt(lineArgs[4]);
              patients.get(patients.size() - 1).sensors.add(new Sensor(lineArgs[0], lineArgs[1], lineArgs[2], lowerBound, upperBound));
            } else {
            //
            }
          } else {
          //
          }
        } else {
        //
        }
      }
    } catch(FileNotFoundException e) {
      System.out.println("File doesn't exist.");
    } catch(IOException e) {
      System.out.println("Failed to read the file.");
    } catch(NumberFormatException e) {
      System.out.println("Failed to convert number from String to Double.");
    }

    for(int i = 0; i <= monitorPeriod; i++) {
      for(Patient patient : patients) {
        if(i % patient.getPeriod() == 0) {
          for(Sensor sensor : patient.sensors) {
            double data = sensor.getDataByIndex(i / patient.getPeriod());
            if(data == -1.0) {
              System.out.printf("[%d] %s fails\n", i, sensor.getName());
            } else if(!sensor.checkInBound(data)) {
              System.out.printf("[%d] %s is in danger! Cause: %s %.1f\n", i, patient.getName(), sensor.getName(), data);
            }
          }
        }
      }
    }

    for(Patient patient : patients) {
      System.out.printf("patient %s\n", patient.getName());
      for(Sensor sensor : patient.sensors) {
        System.out.printf("%s %s\n", sensor.getCategory(), sensor.getName());
        for(int i = 0; i * patient.getPeriod() <= monitorPeriod; i++) {
          System.out.printf("[%d] %.1f\n", i * patient.getPeriod(), sensor.getDataByIndex(i));
        }
      }
    }
  }
}
