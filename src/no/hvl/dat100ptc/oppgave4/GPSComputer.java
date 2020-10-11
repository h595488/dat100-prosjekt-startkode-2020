package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START

		for (int x = 0; x < gpspoints.length - 1; x++) {
			distance += GPSUtils.distance(gpspoints[x], gpspoints[x + 1]);
		}
		return distance;
		// TODO - SLUTT

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START

		for (int x = 0; x < gpspoints.length - 1; x++) {
			
			if (gpspoints[x].getElevation() < gpspoints[x + 1].getElevation()) {
				elevation += (gpspoints[x + 1].getElevation() - gpspoints[x].getElevation());
			}
			
		}
		return elevation;
		// TODO - SLUTT

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
	
		return gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {

		// TODO - START // OPPGAVE - START

		double[] average = new double [gpspoints.length - 1];
		for (int x = 0; x < average.length; x++) {
			average[x] = GPSUtils.speed(gpspoints[x], gpspoints[x + 1]);
		}
		return average;

		// TODO - SLUTT

	}

	public double maxSpeed() {

		double maxspeed = 0;

		// TODO - START

		double[] fart = speeds();
		for (int x = 0; x < fart.length; x++) {
			if (maxspeed < fart[x]) {
				maxspeed = fart[x];
			}
		}
		// TODO - SLUTT
		return maxspeed;
	}

	public double averageSpeed() {

		double average = 0;
		
		// TODO - START
		average = ((totalDistance() / totalTime() * 18) / 5);
		
		return average;
		
		// TODO - SLUTT
		
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling, general
	 * 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0 bicycling,
	 * 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9 mph, racing or
	 * leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph, racing/not drafting
	 * or >19 mph drafting, very fast, racing general 12.0 bicycling, >20 mph,
	 * racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;
		double speedmph = speed * MS;

		// TODO - START

		if (speedmph < 10) {
			met = 4.0;
		}	else if (speedmph < 12) {
			met = 6.0;
		}	else if (speedmph < 14) {
			met = 8.0;
		}	else if (speedmph < 16) {
			met = 10.0;
		} 	else if (speedmph < 20) {
			met = 12.0;
		}	else  
			met = 16.0;
		
		kcal = met * weight * secs / 3600;
		// TODO - SLUTT
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		
			
		totalkcal = kcal(weight, totalTime(), averageSpeed());
	
		
		return totalkcal;
		
		// TODO - SLUTT
		
	}
		
	
	private static double WEIGHT = 80.0;

	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START

		System.out.println(String.format("%-16s", "Total Time") + ":" + GPSUtils.formatTime(totalTime()));
		System.out.println(String.format("%-16s", "Total distance") + ":  " + String.format("%.2f", (totalDistance() / 1000)) + " km");
		System.out.println(String.format("%-16s", "Total elevation") + ":  " + String.format("%.2f",totalElevation()) + " m");
		System.out.println(String.format("%-16s", "Max speed") + ":  " + String.format("%.2f",maxSpeed()) + " km/t");
		System.out.println(String.format("%-16s", "Average speed") + ":  " + String.format("%.2f",averageSpeed()) + " km/t");
		System.out.println(String.format("%-16s", "Energy") + ":  " + String.format("%.2f", totalKcal(WEIGHT)) + " kcal");

		// TODO - SLUTT

	}

}
