/**
 * Capstone Provisio Project
 * Green Team
 * 04/14/2022
 */

package Provisio;

/**
 * Helper class for IP handling
 */
import java.net.InetAddress;
import java.net.UnknownHostException;

class IPHelper {
    public static String getIP()
    	throws UnknownHostException 
    {
        return InetAddress.getLocalHost().getHostAddress();
    }
}