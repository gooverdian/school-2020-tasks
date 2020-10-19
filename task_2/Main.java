import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static class TimePoint {
        private final int seconds;
        private final boolean isPeriodEnd;
        public TimePoint(int seconds, boolean isPeriodEnd) {
            this.seconds = seconds;
            this.isPeriodEnd = isPeriodEnd;
        }
        public int getSeconds() {
            return seconds;
        }
        public boolean isPeriodEnd() {
            return isPeriodEnd;
        }
    }

    public static void main(String[] args) {
        List<TimePoint> timePoints = getTimePoints();
        List<Integer> pointsIndexes = getMaxVacanciesTimePointsStartIndexes(timePoints);
        countAndOutputResult(timePoints, pointsIndexes);
    }

    private static List<TimePoint> getTimePoints() {
        Scanner scanner = new Scanner(System.in);
        int totalPeriods = scanner.nextInt();
        List<TimePoint> timePoints = new ArrayList<>();
        boolean isPeriodEnd = false;
        for (int i = 0; i < totalPeriods * 2; i++) {
            timePoints.add(new TimePoint(scanner.nextInt(), isPeriodEnd));
            isPeriodEnd = !isPeriodEnd;
        }
        return timePoints;
    }

    private static ArrayList<Integer> getMaxVacanciesTimePointsStartIndexes(List<TimePoint> timePoints) {
        timePoints.sort(Comparator.comparing(TimePoint::getSeconds).thenComparing(TimePoint::isPeriodEnd));
        int maxVacanciesCount = 0;
        int currentVacanciesCount = 0;
        ArrayList<Integer> pointsIndexes = new ArrayList<>();
        TimePoint currentTimePoint;
        for (int pointIndex = 0; pointIndex < timePoints.size(); pointIndex++) {
            currentTimePoint= timePoints.get(pointIndex);
            currentVacanciesCount = currentTimePoint.isPeriodEnd() ? --currentVacanciesCount : ++currentVacanciesCount;
            if (currentVacanciesCount > maxVacanciesCount) {
                maxVacanciesCount = currentVacanciesCount;
                pointsIndexes.clear();
                pointsIndexes.add(pointIndex);
            } else if (currentVacanciesCount == maxVacanciesCount) {
                pointsIndexes.add(pointIndex);
            }
        }
        return pointsIndexes;
    }

    private static void countAndOutputResult(List<TimePoint> timePoints, List<Integer> pointsIndexes) {
        int totalTime = 0;
        for (Integer pointIndex : pointsIndexes) {
            totalTime += 1 + timePoints.get(pointIndex + 1).getSeconds() - timePoints.get(pointIndex).getSeconds();
        }
        System.out.println(pointsIndexes.size() + " " + totalTime);
    }
}
