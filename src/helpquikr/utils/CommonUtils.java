package helpquikr.utils;

public class CommonUtils {

    public static double Deg2Rad(double deg)
    {
        return deg * Math.PI / 180.0;
    }

    public static double Rad2Deg(double rad)
    {
        return rad / Math.PI * 180.0;
    }

    public static double CalculateDistance(double lat1, double lon1, double lat2, double lon2)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(Deg2Rad(lat1)) * Math.sin(Deg2Rad(lat2)) + Math.cos(Deg2Rad(lat1)) * Math.cos(Deg2Rad(lat2)) * Math.cos(Deg2Rad(theta));
        dist = Math.acos(dist);
        dist = Rad2Deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344;
        return dist;
    }
}
