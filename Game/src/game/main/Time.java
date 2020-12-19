package game.main;

public class Time {

	private int updatesSinceStart;
	
	public Time() {
		updatesSinceStart = 0;
	}
	
	public int getUpdatesFromSeconds(int seconds) {
		return seconds * 60;
	}
	
}
