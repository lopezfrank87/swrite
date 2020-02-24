package com.madpie.echo;

public class BrailleInput {
	public static void evaluateAction(int inputCounter, int performedAction) {
		boolean wasSlideUp = false;
		switch (performedAction) {
		case 0:
			EchoActivity.charArray[inputCounter][0] = true;
			EchoActivity.charArray[inputCounter][1] = true;
			EchoActivity.resetDeletionCounter();
			break;
		case 1:
			EchoActivity.charArray[inputCounter][0] = false;
			EchoActivity.charArray[inputCounter][1] = true;
			EchoActivity.resetDeletionCounter();
			break;
		case 2:
			EchoActivity.emptyCharArray();
			EchoActivity.resetInputCounter();
			wasSlideUp = true;
			break;
		case 3:
			EchoActivity.charArray[inputCounter][0] = true;
			EchoActivity.charArray[inputCounter][1] = false;
			EchoActivity.resetDeletionCounter();
			break;
		case 4:
			EchoActivity.charArray[inputCounter][0] = false;
			EchoActivity.charArray[inputCounter][1] = false;
			EchoActivity.resetDeletionCounter();
			break;
		}
		if (wasSlideUp) EchoActivity.incrementDeletionCounter();
		else EchoActivity.evaluateCharArray();
	}
	
	public static String getNormalizedText(String beforeNormalizing) {
		String normalizedText = beforeNormalizing;
		normalizedText = normalizedText.replace("#a", "1");
    	normalizedText = normalizedText.replace("#b", "2");
    	normalizedText = normalizedText.replace("#c", "3");
    	normalizedText = normalizedText.replace("#d", "4");
    	normalizedText = normalizedText.replace("#e", "5");
    	normalizedText = normalizedText.replace("#f", "6");
    	normalizedText = normalizedText.replace("#g", "7");
    	normalizedText = normalizedText.replace("#h", "8");
    	normalizedText = normalizedText.replace("#i", "9");
    	normalizedText = normalizedText.replace("#j", "0");
    	normalizedText = normalizedText.replace("^a", "A");
    	normalizedText = normalizedText.replace("^b", "B");
    	normalizedText = normalizedText.replace("^c", "C");
    	normalizedText = normalizedText.replace("^d", "D");
    	normalizedText = normalizedText.replace("^e", "E");
    	normalizedText = normalizedText.replace("^f", "F");
    	normalizedText = normalizedText.replace("^g", "G");
    	normalizedText = normalizedText.replace("^h", "H");
    	normalizedText = normalizedText.replace("^i", "I");
    	normalizedText = normalizedText.replace("^j", "J");
    	normalizedText = normalizedText.replace("^k", "K");
    	normalizedText = normalizedText.replace("^l", "L");
    	normalizedText = normalizedText.replace("^m", "M");
    	normalizedText = normalizedText.replace("^n", "N");
    	normalizedText = normalizedText.replace("^ñ", "Ñ");
    	normalizedText = normalizedText.replace("^o", "O");
    	normalizedText = normalizedText.replace("^p", "P");
    	normalizedText = normalizedText.replace("^q", "Q");
    	normalizedText = normalizedText.replace("^r", "R");
    	normalizedText = normalizedText.replace("^s", "S");
    	normalizedText = normalizedText.replace("^t", "T");
    	normalizedText = normalizedText.replace("^u", "U");
    	normalizedText = normalizedText.replace("^v", "V");
    	normalizedText = normalizedText.replace("^w", "W");
    	normalizedText = normalizedText.replace("^x", "X");
    	normalizedText = normalizedText.replace("^y", "Y");
    	normalizedText = normalizedText.replace("^z", "Z");
    	normalizedText = normalizedText.replace("^á", "Á");
    	normalizedText = normalizedText.replace("^é", "É");
    	normalizedText = normalizedText.replace("^í", "Í");
    	normalizedText = normalizedText.replace("^ó", "Ó");
    	normalizedText = normalizedText.replace("^ú", "Ú");
    	normalizedText = normalizedText.replace("&&", "@");
		return normalizedText;
	}
}
