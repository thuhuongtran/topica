package vn.topica.itlab4.exercises.trung_trainer.network_io.io;

import java.util.ArrayList;
import java.util.List;

public class MachineFactory {
    private final String INPUT_FILE_NAME = "input1.txt";
    private final String OUTPUT_FILE_NAME = "output1.txt";
    private MachineUtils machineUtils = MachineUtils.getInstance();
    private FileUtil fileUtil = FileUtil.getInstance();
    private List<Machine> increasedWarrantyMachines;
    private final static MachineFactory instance = new MachineFactory();

    private MachineFactory() {

    }

    public static MachineFactory getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        MachineFactory mf = MachineFactory.getInstance();
        mf.writeIncreaseWarranty();
        mf.writeFormatOwner();
        mf.writeTopicaMachines();
        mf.writePopularWords();
    }

    /**
     * Convert from list of machine object to string list
     *
     * @param machines
     * @return string-list
     */
    private List<String> toStringList(List<Machine> machines, boolean hasHashTag) {
        List<String> list = new ArrayList<>();
        if (hasHashTag) {
            list.add("###");
        }
        for (Machine machine : machines) {
            list.add(machine.toString());
        }
        return list;
    }

    private void sortByIncreaseWarranty() {
        List<Machine> machines = machineUtils.getMachines(
                fileUtil.readLines(INPUT_FILE_NAME)
        );
        this.increasedWarrantyMachines = machineUtils.sortIncreaseWarrantyYear(machines);
    }

    /**
     * write machines sorted by increasing warranty year to text file
     */
    public void writeIncreaseWarranty() {
        sortByIncreaseWarranty();
        fileUtil.writeLines(OUTPUT_FILE_NAME,
                toStringList(increasedWarrantyMachines, false));
    }

    /**
     * write machines which its owner is formatted to standard form to text file
     */
    public void writeFormatOwner() {
        fileUtil.writeLines(OUTPUT_FILE_NAME,
                toStringList(machineUtils
                                .getFormatOwnerMachines(increasedWarrantyMachines),
                        true));
    }

    /**
     * Write machines which its code contains TOPICA
     * & inputDate from 31/10/2018 to 31/10/2019
     * & sorted by inputDate descending
     * & sorted by increasing warrantyYear
     * to text file
     */
    public void writeTopicaMachines() {
        fileUtil.writeLines(OUTPUT_FILE_NAME,
                toStringList(machineUtils
                                .getTopicaMachines(increasedWarrantyMachines),
                        true));
    }

    /**
     * Write most popular words in text file
     */
    public void writePopularWords() {
        fileUtil.writeLines(OUTPUT_FILE_NAME,
                machineUtils.getPopularWords(increasedWarrantyMachines));
    }
}
