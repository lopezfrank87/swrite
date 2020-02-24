package com.madpie.echo;

import java.util.Arrays;

public class CharacterConversionJapanese {
	public static String getCharacter(boolean[][] charArray) {
		// TODO define arrays
		// あ
		boolean[][] aArray = {{false, true},{false, false},{false, false}};
		boolean[][] iArray = {{false, true},{false, true},{false, false}};
		boolean[][] uArray = {{true, true},{false, false},{false, false}};
		boolean[][] eArray = {{true, true},{false, true},{false, false}};
		boolean[][] oArray = {{true, false},{false, true},{false, false}};
		// か
		boolean[][] kaArray = {{false, true},{false, false},{true, false}};
		boolean[][] kiArray = {{false, true},{false, true},{true, false}};
		boolean[][] kuArray = {{true, true},{false, false},{true, false}};
		boolean[][] keArray = {{true, true},{false, false},{true, false}};
		boolean[][] koArray = {{true, false},{false, true},{true, false}};
		// さ
		boolean[][] saArray = {{false, true},{true, false},{true, false}};
		boolean[][] siArray = {{false, true},{true, false},{true, false}};
		boolean[][] suArray = {{true, true},{true, false},{true, false}};
		boolean[][] seArray = {{true, true},{true, true},{true, false}};
		boolean[][] soArray = {{true, false},{true, true},{true, false}};
		// た
		boolean[][] taArray = {{false, true},{true, false},{false, true}};
		boolean[][] tiArray = {{false, true},{true, true},{false, true}};
		boolean[][] sokuonArray = {{false, false},{false, true},{false, false}};
		boolean[][] tuArray = {{true, true},{true, false},{false, true}};
		boolean[][] teArray = {{true, true},{true, true},{false, true}};
		boolean[][] toArray = {{true, false},{true, true},{false, true}};
		// な
		boolean[][] naArray = {{false, true},{false, false},{false, true}};
		boolean[][] niArray = {{false, true},{false, true},{false, true}};
		boolean[][] nuArray = {{true, true},{false, false},{false, true}};
		boolean[][] neArray = {{true, true},{false, true},{false, true}};
		boolean[][] noArray = {{true, false},{false, true},{false, true}};
		// は
		boolean[][] haArray = {{false, true},{false, false},{true, true}};
		boolean[][] hiArray = {{false, true},{false, true},{true, true}};
		boolean[][] huArray = {{true, true},{false, false},{true, true}};
		boolean[][] heArray = {{true, true},{false, true},{true, true}};
		boolean[][] hoArray = {{true, false},{false, true},{true, true}};
		// ま
		boolean[][] maArray ={{false, true},{true, false},{true, true}};
		boolean[][] miArray ={{false, true},{true, true},{true, true}};
		boolean[][] muArray ={{true, true},{true, false},{true, true}};
		
		// ??????????????????????
		// HOW DID I NOT SEE THIS
		// TODO FIX ASAP
		boolean[][] meArray ={{true, true},{true, true},{true, true}};
		// RANT ENDS THIS LINE. For your cooperation, many thanks.
		
		boolean[][] moArray ={{true, false},{true, true},{true, true}};
		// や
		boolean[][] yaArray ={{true, false},{false, false},{false, true}};
		boolean[][] yuArray ={{true, false},{false, false},{true, true}};
		boolean[][] yoArray ={{true, false},{true, false},{false, true}};
		// ら
		boolean[][] raArray ={{false, true},{true, false},{false, false}};
		boolean[][] riArray ={{false, true},{true, true},{false, false}};
		boolean[][] ruArray ={{true, true},{true, false},{false, false}};
		boolean[][] reArray ={{true, true},{true, true},{false, false}};
		boolean[][] roArray ={{true, false},{true, true},{false, false}};
		// わ
		boolean[][] waArray ={{false, false},{false, false},{false, true}};
		boolean[][] wiArray ={{false, false},{false, true},{false, true}};
		boolean[][] weArray ={{false, false},{true, true},{false, true}};
		boolean[][] woArray ={{false, false},{true, false},{false, true}};
		// ん
		boolean[][] nArray ={{false, false},{true, false},{true, true}};
		// dakuten
		boolean[][] dakutenArray ={{false, false},{true, false},{false, false}};
		boolean[][] handakutenArray ={{false, false},{false, false},{true, false}};
		boolean[][] choonArray ={{false, false},{true, true},{false, false}};
		// yoon
		boolean[][] yoonArray = {{true, false},{false, false},{false, false}};
		boolean[][] yoonDakutenArray = {{true, false},{true, false},{false, false}};
		boolean[][] yoonHandakutenArray = {{true, false},{false, false},{true, false}};
		// TODO return values comparing with Arrays.deepEquals
		
		if (Arrays.deepEquals(charArray, aArray)) return "あ";
		else if (Arrays.deepEquals(charArray, iArray)) return "い";
		else if (Arrays.deepEquals(charArray, uArray)) return "う";
		else if (Arrays.deepEquals(charArray, eArray)) return "え";
		else if (Arrays.deepEquals(charArray, oArray)) return "お";
		
		else if (Arrays.deepEquals(charArray, kaArray)) return "か";
		else if (Arrays.deepEquals(charArray, kiArray)) return "き";
		else if (Arrays.deepEquals(charArray, kuArray)) return "く";
		else if (Arrays.deepEquals(charArray, keArray)) return "け";
		else if (Arrays.deepEquals(charArray, koArray)) return "こ";
		
		else if (Arrays.deepEquals(charArray, saArray)) return "さ";
		else if (Arrays.deepEquals(charArray, siArray)) return "し";
		else if (Arrays.deepEquals(charArray, suArray)) return "す";
		else if (Arrays.deepEquals(charArray, seArray)) return "せ";
		else if (Arrays.deepEquals(charArray, soArray)) return "そ";
		
		else if (Arrays.deepEquals(charArray, taArray)) return "た";
		else if (Arrays.deepEquals(charArray, tiArray)) return "ち";
		else if (Arrays.deepEquals(charArray, sokuonArray)) return "っ";
		else if (Arrays.deepEquals(charArray, tuArray)) return "つ";
		else if (Arrays.deepEquals(charArray, teArray)) return "て";
		else if (Arrays.deepEquals(charArray, toArray)) return "と";
		
		else if (Arrays.deepEquals(charArray, naArray)) return "な";
		else if (Arrays.deepEquals(charArray, niArray)) return "に";
		else if (Arrays.deepEquals(charArray, nuArray)) return "ぬ";
		else if (Arrays.deepEquals(charArray, neArray)) return "ね";
		else if (Arrays.deepEquals(charArray, noArray)) return "の";
		
		else if (Arrays.deepEquals(charArray, haArray)) return "は";
		else if (Arrays.deepEquals(charArray, hiArray)) return "ひ";
		else if (Arrays.deepEquals(charArray, huArray)) return "ふ";
		else if (Arrays.deepEquals(charArray, heArray)) return "へ";
		else if (Arrays.deepEquals(charArray, hoArray)) return "ほ";
		
		else if (Arrays.deepEquals(charArray, maArray)) return "ま";
		else if (Arrays.deepEquals(charArray, miArray)) return "み";
		else if (Arrays.deepEquals(charArray, muArray)) return "む";
		else if (Arrays.deepEquals(charArray, meArray)) return "め";
		else if (Arrays.deepEquals(charArray, moArray)) return "も";
		
		else if (Arrays.deepEquals(charArray, yaArray)) return "や";
		else if (Arrays.deepEquals(charArray, yuArray)) return "ゆ";
		else if (Arrays.deepEquals(charArray, yoArray)) return "よ";
		
		else if (Arrays.deepEquals(charArray, raArray)) return "ら";
		else if (Arrays.deepEquals(charArray, riArray)) return "り";
		else if (Arrays.deepEquals(charArray, ruArray)) return "る";
		else if (Arrays.deepEquals(charArray, reArray)) return "れ";
		else if (Arrays.deepEquals(charArray, roArray)) return "ろ";
		
		else if (Arrays.deepEquals(charArray, waArray)) return "わ";
		else if (Arrays.deepEquals(charArray, wiArray)) return "ゐ";
		else if (Arrays.deepEquals(charArray, weArray)) return "ゑ";
		else if (Arrays.deepEquals(charArray, woArray)) return "を";
		
		else if (Arrays.deepEquals(charArray, nArray)) return "ん";
		
		else if (Arrays.deepEquals(charArray, dakutenArray)) return "゛";
		else if (Arrays.deepEquals(charArray, handakutenArray)) return "゜";
		else if (Arrays.deepEquals(charArray, choonArray)) return "ー";
		
		else if (Arrays.deepEquals(charArray, yoonArray)) return "";
		else if (Arrays.deepEquals(charArray, yoonDakutenArray)) return "";
		else if (Arrays.deepEquals(charArray, yoonHandakutenArray)) return "";
		
		else return "";
	}

}
