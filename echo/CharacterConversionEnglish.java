package com.madpie.echo;

import java.util.Arrays;

public class CharacterConversionEnglish {
	public static String getCharacter(boolean[][] charArray) {
		// PLEASE note that this implementation of Braille has
		// non-standard modifications to add Spanish characters
		// these arrays are as they'd be when writing
		boolean[][] aArray = {{false,true},{false,false},{false,false}};
		boolean[][] bArray = {{false,true},{false,true},{false,false}};
		boolean[][] cArray = {{true,true},{false,false},{false,false}};
		boolean[][] dArray = {{true,true},{true,false},{false,false}};
		boolean[][] eArray = {{false,true},{true,false},{false,false}};
		boolean[][] fArray = {{true,true},{false,true},{false,false}};
		boolean[][] gArray = {{true,true},{true,true},{false,false}};
		boolean[][] hArray = {{false,true},{true,true},{false,false}};
		boolean[][] iArray = {{true,false},{false,true},{false,false}};
		boolean[][] jArray = {{true,false},{true,true},{false,false}};
		boolean[][] kArray = {{false,true},{false,false},{false,true}};
		boolean[][] lArray = {{false,true},{false,true},{false,true}};
		boolean[][] mArray = {{true,true},{false,false},{false,true}};
		boolean[][] nArray = {{true,true},{true,false},{false,true}};
		// mod ñ
		boolean[][] enieArray = {{true,true},{true,true},{true,false}};
		boolean[][] oArray = {{false,true},{true,false},{false,true}};
		boolean[][] pArray = {{true,true},{false,true},{false,true}};
		boolean[][] qArray = {{true,true},{true,true},{false,true}};
		boolean[][] rArray = {{false,true},{true,true},{false,true}};
		boolean[][] sArray = {{true,false},{false,true},{false,true}};
		boolean[][] tArray = {{true,false},{true,true},{false,true}};
		boolean[][] uArray = {{false,true},{false,false},{true,true}};
		boolean[][] vArray = {{false,true},{false,true},{true,true}};
		boolean[][] wArray = {{true,false},{true,true},{true,false}};
		boolean[][] xArray = {{true,true},{false,false},{true,true}};
		boolean[][] yArray = {{true,true},{true,false},{true,true}};
		boolean[][] zArray = {{false,true},{true,false},{true,true}};
		// mod accents
		boolean[][] aAcuteArray = {{false,true},{true,true},{true,true}};
		boolean[][] eAcuteArray = {{true,false},{false,true},{true,true}};
		boolean[][] iAcuteArray = {{true,false},{false,false},{false,true}};
		boolean[][] oAcuteArray = {{true,false},{false,false},{true,true}};
		boolean[][] uAcuteArray = {{true,false},{true,true},{true,true}};
		boolean[][] uDiaeresisArray = {{false,true},{true,true},{true,false}};
		// mod symbols
		boolean[][] ampersandArray = {{true,true},{false,true},{true,true}};
		boolean[][] periodArray = {{false,false},{false,false},{false,true}};
		boolean[][] numberFollowsArray = {{true,false},{true,false},{true,true}};
		boolean[][] capitalFollowsArray = {{true,false},{false,false},{true,false}};
		boolean[][] commaArray = {{false,false},{false,true},{false,false}};
		boolean[][] questionMarkArray = {{false,false},{false,true},{true,false}};
		boolean[][] semiColonArray = {{false,false},{false,true},{false,true}};
		boolean[][] exclamationMarkArray = {{false,false},{true,true},{false,true}};
		boolean[][] quotationMarkArray = {{false,false},{false,true},{true,true}};
		boolean[][] parenthesisOpensArray = {{false,true},{false,true},{true,false}};
		boolean[][] parenthesisClosesArray = {{true,false},{true,false},{false,true}};
		boolean[][] dashArray = {{false,false},{false,false},{true,true}};
		boolean[][] asteriskArray = {{false,false},{true,false},{false,true}};
		boolean[][] emptyArray = {{false,false},{false,false},{false,false}};
		
		if (Arrays.deepEquals(charArray, aArray)) return "a";
		else if (Arrays.deepEquals(charArray, bArray)) return "b";
		else if (Arrays.deepEquals(charArray, cArray)) return "c";
		else if (Arrays.deepEquals(charArray, dArray)) return "d";
		else if (Arrays.deepEquals(charArray, eArray)) return "e";
		else if (Arrays.deepEquals(charArray, fArray)) return "f";
		else if (Arrays.deepEquals(charArray, gArray)) return "g";
		else if (Arrays.deepEquals(charArray, hArray)) return "h";
		else if (Arrays.deepEquals(charArray, iArray)) return "i";
		else if (Arrays.deepEquals(charArray, jArray)) return "j";
		else if (Arrays.deepEquals(charArray, kArray)) return "k";
		else if (Arrays.deepEquals(charArray, lArray)) return "l";
		else if (Arrays.deepEquals(charArray, mArray)) return "m";
		else if (Arrays.deepEquals(charArray, nArray)) return "n";
		// mod ñ
		else if (Arrays.deepEquals(charArray, enieArray)) return "ñ";
		else if (Arrays.deepEquals(charArray, oArray)) return "o";
		else if (Arrays.deepEquals(charArray, pArray)) return "p";
		else if (Arrays.deepEquals(charArray, qArray)) return "q";
		else if (Arrays.deepEquals(charArray, rArray)) return "r";
		else if (Arrays.deepEquals(charArray, sArray)) return "s";
		else if (Arrays.deepEquals(charArray, tArray)) return "t";
		else if (Arrays.deepEquals(charArray, uArray)) return "u";
		else if (Arrays.deepEquals(charArray, vArray)) return "v";
		else if (Arrays.deepEquals(charArray, wArray)) return "w";
		else if (Arrays.deepEquals(charArray, xArray)) return "x";
		else if (Arrays.deepEquals(charArray, yArray)) return "y";
		else if (Arrays.deepEquals(charArray, zArray)) return "z";
		// mod accents
		else if (Arrays.deepEquals(charArray, aAcuteArray)) return "á";
		else if (Arrays.deepEquals(charArray, eAcuteArray)) return "é";
		else if (Arrays.deepEquals(charArray, iAcuteArray)) return "í";
		else if (Arrays.deepEquals(charArray, oAcuteArray)) return "ó";
		else if (Arrays.deepEquals(charArray, uAcuteArray)) return "ú";
		else if (Arrays.deepEquals(charArray, uDiaeresisArray)) return "ü";
		// mod symbols
		else if (Arrays.deepEquals(charArray, ampersandArray)) return "&";
		else if (Arrays.deepEquals(charArray, periodArray)) return ".";
		else if (Arrays.deepEquals(charArray, numberFollowsArray)) return "#";
		else if (Arrays.deepEquals(charArray, capitalFollowsArray)) return "^";
		else if (Arrays.deepEquals(charArray, commaArray)) return ",";
		else if (Arrays.deepEquals(charArray, questionMarkArray)) return "?";
		else if (Arrays.deepEquals(charArray, semiColonArray)) return ";";
		else if (Arrays.deepEquals(charArray, exclamationMarkArray)) return "!";
		else if (Arrays.deepEquals(charArray, quotationMarkArray)) return "\"";
		else if (Arrays.deepEquals(charArray, parenthesisOpensArray)) return "(";
		else if (Arrays.deepEquals(charArray, parenthesisClosesArray)) return ")";
		else if (Arrays.deepEquals(charArray, dashArray)) return "-";
		else if (Arrays.deepEquals(charArray, asteriskArray)) return "*";
		else if (Arrays.deepEquals(charArray, emptyArray)) return " ";
		// unrecognized character
		else return "";
	}

}
