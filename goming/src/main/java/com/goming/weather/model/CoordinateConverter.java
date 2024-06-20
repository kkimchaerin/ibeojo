package com.goming.weather.model;

public class CoordinateConverter {

    private double RE = 6371.00877; // 지구 반경(km)
    private double GRID = 5.0; // 격자 간격(km)
    private double SLAT1 = 30.0; // 투영 위도1(degree)
    private double SLAT2 = 60.0; // 투영 위도2(degree)
    private double OLON = 126.0; // 기준점 경도(degree)
    private double OLAT = 38.0; // 기준점 위도(degree)
    private double XO = 43; // 기준점 X좌표(GRID)
    private double YO = 136; // 기준점 Y좌표(GRID)



    public CoordinateDTO dfs_xy_conv(boolean code, double v1, double v2) {
        double DEGRAD = Math.PI / 180.0;
        double RADDEG = 180.0 / Math.PI;

        double re = RE / GRID;
        double slat1 = SLAT1 * DEGRAD;
        double slat2 = SLAT2 * DEGRAD;
        double olon = OLON * DEGRAD;
        double olat = OLAT * DEGRAD;

        double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
        double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
        double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
        ro = re * sf / Math.pow(ro, sn);
        CoordinateDTO rs = new CoordinateDTO();

        if (code) {
            rs.lat = v1;
            rs.lng = v2;
            double ra = Math.tan(Math.PI * 0.25 + (v1) * DEGRAD * 0.5);
            ra = re * sf / Math.pow(ra, sn);
            double theta = v2 * DEGRAD - olon;
            if (theta > Math.PI) theta -= 2.0 * Math.PI;
            if (theta < -Math.PI) theta += 2.0 * Math.PI;
            theta *= sn;
            rs.x = Math.floor(ra * Math.sin(theta) + XO + 0.5);
            rs.y = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
        } else {
            rs.x = v1;
            rs.y = v2;
            double xn = v1 - XO;
            double yn = ro - v2 + YO;
            double ra = Math.sqrt(xn * xn + yn * yn);
            if (sn < 0.0) ra = -ra;
            double alat = Math.pow((re * sf / ra), (1.0 / sn));
            alat = 2.0 * Math.atan(alat) - Math.PI * 0.5;

            double theta;
            if (Math.abs(xn) <= 0.0) {
                theta = 0.0;
            } else {
                if (Math.abs(yn) <= 0.0) {
                    theta = Math.PI * 0.5;
                    if (xn < 0.0) theta = -theta;
                } else {
                    theta = Math.atan2(xn, yn);
                }
            }
            double alon = theta / sn + olon;
            rs.lat = alat * RADDEG;
            rs.lng = alon * RADDEG;
        }
        rs.toString();
        return rs;
    }
}
