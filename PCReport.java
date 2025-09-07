package a2_2101040097;

public class PCReport {
    public String displayReport(PC[] objs) {
        String title = "PCPROG REPORT";
        String dashes = "";
        for (int i = 0; i < 99; i++) {
            dashes += "-";
        }
        String title_padding = "%" + (99+title.length())/2 + "s";

        StringBuilder display = new StringBuilder(String.format(String.format("%s\n%s\n%s\n", dashes, title_padding, dashes),title));

        for (int i = 0; i < objs.length; i++) {
            String model = objs[i].getModel();
            int year = objs[i].getYear();
            String manufacturer = objs[i].getManufacturer();
            Set<String> comps = objs[i].getComps();

            display.append(String.format("%2d", i+1)).append(" ");
            display.append(String.format("%-20s", model)).append(" ");
            display.append(String.format("%7d", year)).append(" ");
            display.append(String.format("%-15s", manufacturer)).append(" ");

            display.append(comps.toString()).append("\n"); // Use the toString() method of Set to get a formatted string with brackets
        }
        display.append(dashes).append("\n");
        return display.toString();
    }
}