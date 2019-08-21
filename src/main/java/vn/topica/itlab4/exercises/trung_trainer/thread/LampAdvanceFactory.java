package vn.topica.itlab4.exercises.trung_trainer.thread;

import java.util.Random;

/**
 * Exercise 2:
 * Bài 2: (4 điểm)
 * Mở rộng trạng thái của class Lamp với thêm một status là repair
 * - Sửa lại Thread 1 với việc tạo ngâu nhiên các đèn trong đó có cả status repair
 * - Sửa lại thread 2 với việc loại bỏ thêm cả đèn có status là repair
 * <p>
 * Tạo Thread 3(Sleep 200 ms): Có nhiệm vụ kiểm tra Lamp trong Trash.
 * Với các class Lamp có status là repair remove khỏi class Trash và add lại vào class Store
 * Với các class Lamp có status là off thì random ngẫu nhiên 0-1:
 * - Nếu bằng 0 thì loại bỏ ra khỏi trash vào không add lại vào class Store
 * - Nếu bằng 1 thì chuyển trạng thái thành repair và add lại vào class Store
 * Link GITHUB: https://github.com/thuhuongtran/topica/tree/thuhuong/src/main/java/vn/topica/itlab4/exercises/trung_trainer/thread
 */
public class LampAdvanceFactory {
    private static LampAdvanceFactory instance = new LampAdvanceFactory();
    private static Store store;
    private static Trash trash;
    private static LampAdvanceFactory.StoreThread storeThread;
    private static LampAdvanceFactory.TrashThread trashThread;
    private static LampAdvanceFactory.RepairThread repairThread;

    private LampAdvanceFactory() {
    }

    protected static LampAdvanceFactory getInstance() {
        store = new Store();
        trash = new Trash();
        storeThread = new StoreThread();
        trashThread = new TrashThread();
        repairThread = new RepairThread();
        return instance;
    }

    public static void main(String[] args) {
        LampAdvanceFactory.getInstance();
        storeThread.start();
        trashThread.start();
        repairThread.start();
    }

    private static class StoreThread extends Thread {
        /**
         * Create 20 lamps with random status
         * Save in store
         */
        @Override
        public void run() {
            System.out.println("THREAD_1 STORE starts running.");
            Random random = new Random();
            while (true) {
                for (int i = 0; i < random.nextInt(20) + 1; i++) {
                    int temp = random.nextInt(10);
                    Lamp lamp;
                    if (temp == 0 || temp == 3 || temp == 4 || temp == 80) {
                        lamp = new Lamp(Lamp.Status.ON.name());
                    } else if (temp == 1 || temp == 5 || temp == 9) {
                        lamp = new Lamp(Lamp.Status.OFF.name());
                    } else {
                        lamp = new Lamp(Lamp.Status.REPAIR.name());
                    }
                    if (store.add(lamp)) {
                        System.out.println(String.format("THREAD_1 STORE: Add new lamp: %s", lamp.toString()));
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("THREAD_1 STORE: Store thread is interrupted");
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class TrashThread extends Thread {
        /**
         * Remove all lamps which have status = OFF & REPAIR from store
         * Then add in trash
         */
        @Override
        public void run() {
            System.out.println("THREAD_2 trash starts running.");
            while (true) {
                for (int i = 0; i < store.size(); i++) {
                    Lamp lamp = store.get(i);
                    if (lamp.getStatus().equals(Lamp.Status.OFF.name())
                            || lamp.getStatus().equals(Lamp.Status.REPAIR.name())) {
                        if (store.remove(lamp))
                            System.out.println(String.format("THREAD_2 trash: Remove lamp from STORE: %s", lamp.toString()));
                        if (trash.add(lamp))
                            System.out.println(String.format("THREAD_2 trash: TRASH add new lamp: %s", lamp.toString()));
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("THREAD_2 trash: is interrupted");
                    e.printStackTrace();
                }
            }
        }
    }

    private static class RepairThread extends Thread {
        /**
         * Check in trash:
         * If repair-lamp then remove from trash and add in store
         * If off-lamp then random 0 or 1:
         * If 0: remove from trash
         * If 1: update to repair then remove from trash and add in store
         */
        @Override
        public void run() {
            System.out.println("THREAD_3 Repair starts running.");
            while (true) {
                for (int i = 0; i < trash.size(); i++) {
                    Lamp lamp = trash.get(i);
                    if (lamp.getStatus().equals(Lamp.Status.REPAIR.name())) {
                        if (trash.remove(lamp))
                            System.out.println(String.format("THREAD_3 Repair: Remove repair-lamp from TRASH: %s", lamp.toString()));
                        if (store.add(lamp))
                            System.out.println(String.format("THREAD_3 Repair: Add old-repair-lamp to STORE: %s", lamp.toString()));
                    } else if (lamp.getStatus().equals(Lamp.Status.OFF.name())) {
                        int random = (new Random()).nextInt(2);
                        if (trash.remove(lamp))
                            System.out.println(String.format("THREAD_3 Repair: CASE = %d, Remove old-off-lamp from TRASH: %s"
                                    , random
                                    , lamp.toString()));
                        if (random == 1) {
                            lamp.setStatus(Lamp.Status.REPAIR.name());
                            if (store.add(lamp))
                                System.out.println(String.format("THREAD_3 Repair: CASE = %d, Add update-off_repair-lamp to STORE: %s"
                                        , random
                                        , lamp.toString()));
                        }
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("THREAD_3 Repair: is interrupted");
                    e.printStackTrace();
                }
            }
        }
    }
}
