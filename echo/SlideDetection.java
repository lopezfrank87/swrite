package com.madpie.echo;

public class SlideDetection {
	// calculate the difference in values (second minus first)
	public static int getDifference(int valDown, int valUp) {
		int diffValue = valUp - valDown; 
		return diffValue;
	}
	
	public static int getDirection(int xDown, int yDown, int xUp, int yUp) {
		// how much displacement is allowed in a tap action
		int tapTreshold = 10;
		
		// return a 0 when the screen is tapped
		if (Math.abs(SlideDetection.getDifference(xDown, xUp)) < tapTreshold && Math.abs(SlideDetection.getDifference(yDown, yUp)) < tapTreshold) {
			// touch action (tap)
			return 0;
		}
		else {
			// x axis slides
			// return a 1 -- right
			// return a 3 -- left
			if (Math.abs(SlideDetection.getDifference(xDown, xUp)) > Math.abs(SlideDetection.getDifference(yDown, yUp))) {
				if (SlideDetection.getDifference(xDown, xUp) > 0) {
					return 1;
				}
				else {
					return 3;
				}
			}
			
			// y axis slides
			// return a 2 -- up
			// return a 4 -- down
			else {
				if (SlideDetection.getDifference(yDown, yUp) > 0) {
					return 4;
				}
				else {
					return 2;
				}
			}
		}
	}
}
