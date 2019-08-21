package vn.topica.itlab4.exercises.trung_trainer.thread;

import java.util.Random;

/**
 * Exercise 1: Bài 1(6 điểm):
 * - Viết một chương trình gầm 2 thread có chức năng như sau:
 * Thread 1(Sleep 100 ms): Tạo đèn
 * - Random ngẫu nhiên giá trị từ 1-20 để sinh ra số lượng đèn với status là ngẫu nhiên
 * Thread 2(Sleep 200 ms): Kiểm tra và loại bỏ toàn bộ những đèn có status là off được lưu trong class Store
 */
public class LampFactory {
    private static LampFactory instance = new LampFactory();
    private static Store store;
    private static Trash trash;
    private static StoreThread storeThread;
    private static TrashThread trashThread;

    private LampFactory() {
    }

    protected static LampFactory getInstance() {
        store = new Store();
        trash = new Trash();
        storeThread = new StoreThread();
        trashThread = new TrashThread();
        return instance;
    }

    public static void main(String[] args) {
        LampFactory.getInstance();
        storeThread.start();
        trashThread.start();
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
                for (int i = 0; i < random.nextInt(20)+1; i++) {
                    int temp = random.nextInt(100);
                    Lamp lamp;
                    if (temp % 2 == 0) {
                        lamp = new Lamp(Lamp.Status.ON.name());
                    } else {
                        lamp = new Lamp(Lamp.Status.OFF.name());
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
         * Remove all lamps which have status = OFF from store
         * Then add in trash
         */
        @Override
        public void run() {
            System.out.println("THREAD_2 trash starts running.");
            while (true) {
                for (int i = 0; i < store.size(); i++) {
                    Lamp lamp = store.get(i);
                    if (trash.add(lamp)) {
                        System.out.println(String.format("THREAD_2 trash: TRASH add new lamp: %s", lamp.toString()));
                    }
                    store.removeAll(trash);
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
}
