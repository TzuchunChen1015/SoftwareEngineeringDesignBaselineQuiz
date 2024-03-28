import java.util.*;

public class Patient {
  private String name;
  private int period;

  public ArrayList<Sensor> sensors;

  public Patient(String name, int period) {
    this.name = name;
    this.period = period;

    sensors = new ArrayList<>();
  }

  public String getName() {
    return this.name;
  }
  public int getPeriod() {
    return this.period;
  }
}
