package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		// TODO - START

		min = da[0];
		for (double d : da) {
			if (d < min) {
			min = d;	
			}
		}
		return min;
		// TODO - SLUT

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		
		double[] bredde = new double[gpspoints.length];
		for( int x = 0; x < bredde.length; x++) {
			bredde[x] = gpspoints[x].getLatitude();
		}
		return bredde;
		// TODO - SLUTT
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START

		double[] lengde = new double[gpspoints.length];
		for( int x = 0; x < lengde.length; x++) {
			lengde[x] = gpspoints[x].getLongitude();
		}
		return lengde;
		
		// TODO - SLUTT

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// TODO - START
		
		latitude1 = Math.toRadians(gpspoint1.getLatitude());
		longitude1 = Math.toRadians(gpspoint1.getLongitude());
		latitude2 = Math.toRadians(gpspoint2.getLatitude());
		longitude2 = Math.toRadians(gpspoint2.getLongitude());
		double dOmega = Math.toRadians(gpspoint2.getLatitude() - gpspoint1.getLatitude());
		double dLambda = Math.toRadians(gpspoint2.getLongitude() - gpspoint1.getLongitude());
		double a = Math.pow((Math.sin(dOmega/2)), 2) + (Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(dLambda/2), 2));
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		d = R * c;
		return d;
		// TODO - SLUTT

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// TODO - START

		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = ((distance(gpspoint1,gpspoint2) / secs) * 18 / 5);
		return speed;

		// TODO - SLUTT

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		
		int sec = secs % 60;
		int hrs = secs / 60;
		int min = hrs % 60;
		hrs = hrs / 60;
		
		timestr = (String.format("%02d", hrs)) + TIMESEP + (String.format("%02d", min)) + TIMESEP + (String.format("%02d", sec));
		timestr = String.format("%10s", timestr);
		return timestr;
		
		// TODO - SLUTT

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		str = String.format("%.2f", d);
		
		str = String.format("%10s", str);
		return str;
		// TODO - SLUTT
		
	}
}
