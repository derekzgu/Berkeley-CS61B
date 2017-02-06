import java.util.ArrayList;

public class DotCom {

    private ArrayList<String> locationCells;
    private String name;

    public void setLocation(ArrayList<String> loc) {
        locationCells = loc;
    }

    public void setName(String s) {
        this.name = s;
    }

    public String checkYourself(String userInput) {
        String result = "miss";
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            result = "hit";
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = "kill";
            }
        }
        return result;
    }

    public String dotComName() {
        return this.name;
    }

}


