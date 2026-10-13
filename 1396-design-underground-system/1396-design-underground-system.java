import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    private static class CheckInData {
        String station;
        int time;

        CheckInData(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    private static class RouteData {
        double totalTime;
        int count;

        RouteData() {
            totalTime = 0;
            count = 0;
        }
    }

    private Map<Integer, CheckInData> checkIns;
    private Map<String, RouteData> routes;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        routes = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckInData(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckInData data = checkIns.get(id);

        int travelTime = t - data.time;

        String route = data.station + "#" + stationName;

        if (!routes.containsKey(route)) {
            routes.put(route, new RouteData());
        }

        RouteData routeData = routes.get(route);
        routeData.totalTime += travelTime;
        routeData.count++;

        checkIns.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "#" + endStation;

        RouteData routeData = routes.get(route);

        return routeData.totalTime / routeData.count;
    }
}