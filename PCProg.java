package a2_2101040097;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import utils.NotPossibleException;
/**
 * A program that captures data about PC objects and displays
 * a report about them on the console.
 */
public class PCProg {
    private static final Object YES = "Y";
    private Set<PC> objs;

    /**
     * Initialise this to have an empty set of PCs
     */
    public PCProg() {
        objs = new Set<>();
    }

    /**
     * If <tt>objs</tt> is not empty, displays a text-based tabular
     * report on <tt>objs</tt> to the standard console.
     * Displays nothing if <tt>objs</tt> is empty.
     *
     * @return this report if <tt>objs</tt> is not empty or <tt>null</tt> otherwise.
     */
    public String displayReport() {
        if (!objs.isEmpty()) {
            Vector<PC> pcs = objs.getElements();
            PCReport reportObj = new PCReport();
            return reportObj.displayReport(pcs.toArray(new PC[0]));
        } else {
            return null;
        }
    }

    /**
     * Saves report to a file <tt>pcs.txt</tt> in the program's working directory.
     */
    public void saveReport(String report) {
        String fileName = "pcs.txt";
        try (PrintWriter pw = new PrintWriter(fileName)) {
            pw.println(report);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes an instance of <tt>PCProg</tt>.
     * Create objects from data entered by the user.
     * Display a report on the objects.
     * Prompt user to save report to file. If user answers "Y", save report.
     * Otherwise, end program.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PCProg prog = new PCProg();
        try {
            // create objects
            prog.createObjects();
            // display report
            String report = prog.displayReport();
            System.out.println(report);
            if (report != null) {
                // prompt user to save report
                System.out.println("Save report to file? [Y/N]");
                String toSave = sc.nextLine();
                if (toSave.equals("Y")) {
                    prog.saveReport(report);
                    System.out.println("report saved");
                }
            }
        } catch (NotPossibleException e) {
            System.err.printf("%s: %s%n", e, e.getMessage());
        }
        System.out.println("~END~");
    }

    /**
     * The run method
     *
     * @effects initialise an instance of PCProg create objects from data entered by
     * the user display a report on the objects prompt user to save report
     * to file if user answers "Y" save report else end
     */

    private void createObjects() {
        createObjects(new Scanner(System.in)); // Call the other createObjects
    }

    private void createObjects(Scanner sc) { // Receive Scanner object
        PCFactory pcFactory = PCFactory.getInstance();
        String option = "Y";

        while (option.equalsIgnoreCase("Y")) {
            try {
                System.out.println("Enter product's model:");
                String model = sc.nextLine();
                System.out.println("Enter product's year:");
                int year = Integer.parseInt(sc.nextLine());
                System.out.println("Enter product's manufacturer:");
                String manufacturer = sc.nextLine();

                Set<String> comps = new Set<>();
                String comp;
                System.out.println("Enter components (enter a blank line to finish):");
                while (true) {
                    comp = sc.nextLine();
                    if (comp.isEmpty()) {
                        break;
                    }
                    comps.insert(comp);
                }

                PC newPC = pcFactory.createPC(model, year, manufacturer, comps);
                objs.insert(newPC);

                System.out.println("Add another PC? [Y/N]");
                option = sc.nextLine();
            } catch (NumberFormatException | NotPossibleException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Set<PC> getObjects() {
        return objs;
    }
}



