package eu.usrv.yamcore.auxiliary;

public class TextFormatHelper
{
	private static String addToken(String pToken, String pIdentifier)
	{
		return String.format("%s%s", pToken, pIdentifier);
	}
	
	public static String DecodeStringCodes(String pSource, String pToken)
	{
	      return pSource.replace(addToken(pToken, "0"), FontCodes.BLACK)
	    	        .replace(addToken(pToken, "1"), FontCodes.DARK_BLUE)
	    	        .replace(addToken(pToken, "2"), FontCodes.DARK_GREEN)
	    	        .replace(addToken(pToken, "3"), FontCodes.DARK_AQUA)
	    	        .replace(addToken(pToken, "4"), FontCodes.DARK_RED)
	    	        .replace(addToken(pToken, "5"), FontCodes.DARK_PURPLE)
	    	        .replace(addToken(pToken, "6"), FontCodes.GOLD)
	    	        .replace(addToken(pToken, "7"), FontCodes.GRAY)
	    	        .replace(addToken(pToken, "8"), FontCodes.DARK_GREY)
	    	        .replace(addToken(pToken, "9"), FontCodes.BLUE)
	    	        .replace(addToken(pToken, "a"), FontCodes.GREEN)
	    	        .replace(addToken(pToken, "b"), FontCodes.AQUA)
	    	        .replace(addToken(pToken, "c"), FontCodes.RED)
	    	        .replace(addToken(pToken, "d"), FontCodes.LIGHT_PURPLE)
	    	        .replace(addToken(pToken, "e"), FontCodes.YELLOW)
	    	        .replace(addToken(pToken, "f"), FontCodes.WHITE)
	    	        .replace(addToken(pToken, "k"), FontCodes.OBFUSCATED)
	    	        .replace(addToken(pToken, "l"), FontCodes.BOLD)
	    	        .replace(addToken(pToken, "m"), FontCodes.STRIKETHROUGH)
	    	        .replace(addToken(pToken, "n"), FontCodes.UNDERLINE)
	    	        .replace(addToken(pToken, "o"), FontCodes.ITALICS)
	    	        .replace(addToken(pToken, "r"), FontCodes.RESET);
	}
	
    /**
     * Decodes any font codes into something useable by the FontRenderer. The tokens (0-9, a-o are minecraft codes. Use token r to reset any format)
     * @param pSource A string with format-tokens. The default token identifier is "__"
     * @return "This is __lbold" would return a text where the word bold is actually bold
     */
    public static String DecodeStringCodes(String pSource)
    {
    	return DecodeStringCodes(pSource, "__");
    }
    
    public static class FontCodes
    {
         //color codes for rendered strings
         public static final String BLACK = "\2470";
         public static final String DARK_BLUE = "\2471";
         public static final String DARK_GREEN = "\2472";
         public static final String DARK_AQUA = "\2473";
         public static final String DARK_RED = "\2474";
         public static final String DARK_PURPLE = "\2475";
         public static final String GOLD = "\2476";
         public static final String GRAY = "\2477";
         public static final String DARK_GREY = "\2478";
         public static final String BLUE = "\2479";
         public static final String GREEN = "\247a";
         public static final String AQUA = "\247b";
         public static final String RED = "\247c";
         public static final String LIGHT_PURPLE = "\247d";
         public static final String YELLOW = "\247e";
         public static final String WHITE = "\247f";
           
         //font styles
         public static final String OBFUSCATED = "\247k";
         public static final String BOLD = "\247l";
         public static final String STRIKETHROUGH = "\247m";
         public static final String UNDERLINE = "\247n";
         public static final String ITALICS = "\247o";
           
         public static final String RESET = "\247r";
    }
}
