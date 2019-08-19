package vn.topica.itlab4.exercises.song_trainer.collection;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class CircleFactory {
    private static final CircleFactory instance = new CircleFactory();

    private CircleFactory() {
    }

    public static CircleFactory getInstance() {
        return instance;
    }

    /**
     * Create 1000 circles which has random positive radius then add in a list
     * Then sort the list by radius ascending
     *
     * @return
     */
    public List<Circle> sort1000Circle() {
        List<Circle> circles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            Circle circle = new Circle(random.nextInt(1000));
            circles.add(circle);
        }
        Collections.sort(circles, new Comparator<Circle>() {
            @Override
            public int compare(Circle o1, Circle o2) {
                return o1.getRadius() - o2.getRadius();
            }
        });
        return circles;
    }

    /**
     * Input a number
     * Find the circle which has the nearest area to n
     * Use binary search
     *
     * @param n
     * @return index of that circle in sorted circle list
     */
    public int getNearestArea(int n) {
        double radius = Math.sqrt((double) n / Math.PI);
        System.out.println("Need to find the circle which has radius nearest to " + radius);
        List<Circle> circles = sort1000Circle();
        int low = 0;
        int high = 999;
        int mid = circles.size() / 2;
        while (low <= high) {
            mid = (low + high) / 2;
            int midRadius = circles.get(mid).getRadius();
            int tmpDiff = midRadius - (int) radius;
            if (tmpDiff < 0) {
                low = mid + 1;
            } else if (tmpDiff > 0) {
                high = mid - 1;
            } else if (tmpDiff == 0) {
                break;
            }
        }
        System.out.printf("We find the best Circle with the radius = %d. And the index of the circle in list = %d", circles.get(mid).getRadius(), mid);
        return mid;
    }

    public static void main(String[] args) {
        List<Circle> circles = CircleFactory.getInstance().sort1000Circle();
        List<Integer> radius = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            radius.add(circles.get(i).getRadius());
        }
        System.out.println("List of sorted 1000 circle radius: \n" + StringUtils.join(radius, ","));
        Scanner sc = new Scanner(System.in);
        System.out.println("n = ");
        int n = sc.nextInt();
        CircleFactory.getInstance().getNearestArea(n);
    }
}
