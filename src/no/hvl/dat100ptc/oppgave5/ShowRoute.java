package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon));

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {

		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat));

		return ystep;
		// TODO - START

		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT

	}

	public void showRouteMap(int ybase) {

		// TODO - START

		double xstep = xstep();
		double ystep = ystep();
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlong = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		int x, y, prevx, prevy;
		prevx = prevy = 0;
		setColor(0, 255, 0);
		for (int i = 0; i < gpspoints.length; i++) {
			x = MARGIN + (int) ((gpspoints[i].getLongitude() - minlong) * xstep);
			y = ybase - (int) ((gpspoints[i].getLatitude() - minlat) * ystep);

			if (i != 0)
				drawLine(prevx, prevy, x, y);

			if (i == gpspoints.length - 1) {
				setColor(0, 0, 255);
				fillCircle(x, y, 5);
			} else {
				fillCircle(x, y, 3);
			}

			prevx = x;
			prevy = y;
		}
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0);
		setFont("Courier", 12);

		// TODO - START
		String drawstring = "";
		String totaltime = GPSUtils.formatTime(gpscomputer.totalTime());
		drawstring = String.format("%-16s", "Total Time") + ": " + totaltime;
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE);

		String totdist = GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000);
		drawstring = String.format("%-16s", "Total Distance") + ": " + totdist + " km";
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE * 2);

		String totelev = GPSUtils.formatDouble(gpscomputer.totalElevation());
		drawstring = String.format("%-16s", "Total Elevation") + ": " + totelev + " m";
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE * 3);

		String fast = GPSUtils.formatDouble(gpscomputer.maxSpeed());
		drawstring = String.format("%-16s", "Max Speed") + ": " + fast + " km/t";
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE * 4);

		String gjenm = GPSUtils.formatDouble(gpscomputer.averageSpeed());
		drawstring = String.format("%-16s", "Average Speed") + ": " + gjenm + " km/t";
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE * 5);

		double weight = 44.77; //vet ikke hvor man finner weight som man trenger for å bruke totalkcal opprettet derfor double weight for at vi skulle få et resultat...
		String totkcal = GPSUtils.formatDouble(gpscomputer.totalKcal(weight));
		drawstring = String.format("%-16s", "Energy") + ": " + totkcal + " kcal";
		drawString(drawstring, TEXTDISTANCE, TEXTDISTANCE * 6);
		// throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT;
	}

}
