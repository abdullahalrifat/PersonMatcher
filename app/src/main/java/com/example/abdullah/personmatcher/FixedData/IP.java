package com.example.abdullah.personmatcher.FixedData;

/**
 * Created by abdullah on 3/2/17.
 */

public class IP
{
    /*
  * 10.0.3.2 is for genymotion
  *
  * 10.0.2.2 is for normal avd
  *
  * to check ip for mobile run ifconfig
  * */
    public static String ip="192.168.0.102";
    public static void setIp(String ip) {
        IP.ip = ip;
    }

    public static String getIp() {
        return ip;
    }


}
