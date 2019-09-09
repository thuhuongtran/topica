package vn.topica.itlab4.exercises.trung_trainer.network_io.io;

import java.util.*;

public class MachineUtils {
    private final static MachineUtils instance = new MachineUtils();

    private MachineUtils() {

    }

    public static MachineUtils getInstance() {
        return instance;
    }

    /**
     * Sort list of machine by increasing warranty-year
     *
     * @param machines un-sort list of machine
     * @return machines
     */
    protected List<Machine> sortIncreaseWarrantyYear(List<Machine> machines) {
        machines.sort(new Comparator<Machine>() {
            @Override
            public int compare(Machine o1, Machine o2) {
                return o1.getWarrantyYear() - o2.getWarrantyYear();
            }
        });
        return machines;
    }

    /**
     * From list of string (machine detail) to list of machine
     *
     * @param strs
     * @return machines
     */
    protected List<Machine> getMachines(List<String> strs) {
        List<Machine> machines = new ArrayList<>();
        for (String str : strs) {
            machines.add(getMachine(str));
        }
        return machines;
    }

    /**
     * machineStr is format of code,name,owner,inputDate,warrantyYear
     *
     * @param machineStr
     * @return machine object
     */
    private Machine getMachine(String machineStr) {
        String[] strs = machineStr.split(",");
        Machine machine = new Machine(strs[0].trim(), // code
                strs[1].trim(), // name
                strs[2].trim(), // owner
                strs[3].trim(), // inputDate
                Integer.parseInt(strs[4].trim())); // warrantyYear
        return machine;
    }

    /**
     * Format owner name in standard form.
     *
     * @return owner-string-name
     */
    private String formatOwnerName(String owner) {
        owner = owner.toLowerCase();
        owner = owner.replaceAll("\\s+", " ");
        String firstCharacter = owner.substring(0, 1).toUpperCase();
        owner = firstCharacter.concat(owner.substring(1));
        return owner;
    }

    protected List<Machine> getFormatOwnerMachines(List<Machine> machines) {
        for (Machine machine : machines) {
            machine.setOwner(formatOwnerName(machine.getOwner()));
        }
        return machines;
    }

    /**
     * Get machines which its code contains TOPICA
     * and inputDate from 31/10/2018 to 31/10/2019
     * sort by inputDate descending
     * sort by increasing warrantyYear
     *
     * @param machines
     * @return topicaMachines
     */
    protected List<Machine> getTopicaMachines(List<Machine> machines) {
        List<Machine> topicaMachines = new ArrayList<>();
        for (Machine machine : machines) {
            if (machine.getCode().contains("TOPICA")
                    && machine.getInputDate().after(machine.stringToDate("31/10/2018"))
                    && machine.getInputDate().before(machine.stringToDate("31/10/2019"))) {

                topicaMachines.add(machine);
            }
        }
        sortIncreaseWarrantyYear(topicaMachines);
        sortByInputDateDecending(topicaMachines);
        return topicaMachines;
    }

    /**
     * Sort machines by input-date descending
     *
     * @param machines
     */
    private void sortByInputDateDecending(List<Machine> machines) {
        machines.sort(new Comparator<Machine>() {
            @Override
            public int compare(Machine o1, Machine o2) {
                return o2.getInputDate().compareTo(o1.getInputDate());
            }
        });
    }

    /**
     * Get list of owners
     * find popular words
     *
     * @param machines
     * @return popular-words
     */
    protected List<String> getPopularWords(List<Machine> machines) {
        List<String> words = new ArrayList<>();
        for (Machine machine : machines) {
            String[] strs = machine.getOwner().split("\\s+");
            for (String str : strs) {
                words.add(str.trim());
            }
        }
        // count frequency
        Set<String> strs = new HashSet<>(words);
        Map<String, Integer> countMap = new HashMap<>();
        for (String str : strs) {
            countMap.put(str, Collections.frequency(words, str));
        }
        // get list of most popular words
        List<String> popular = new ArrayList<>();
        popular.add("###");
        int max = Collections.max(countMap.values());
        countMap.forEach((str, cnt) -> {
            if (cnt == max) {
                popular.add(str);
            }
        });
        return popular;
    }
}
