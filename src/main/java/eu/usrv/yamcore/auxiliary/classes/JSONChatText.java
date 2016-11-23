
package eu.usrv.yamcore.auxiliary.classes;


import java.util.ArrayList;

import net.minecraft.util.EnumChatFormatting;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


/**
 * Convenient class to construct Chat-messages that include Hover and/or Click-events
 */
public class JSONChatText
{
  public boolean Bold;
  public boolean Italic;
  public boolean Underlined;
  public boolean Strikethrough;
  public boolean Obfuscated;
  public EnumChatFormatting Color;
  public JSONHoverEvent HoverEvent;
  public JSONClickEvent ClickEvent;

  public static JSONChatText simpleMessage( String pMessage )
  {
    JSONChatText tJ = new JSONChatText();
    tJ.Message = pMessage;
    return tJ;
  }

  /**
   * The Message to be sent. This is just plain Text
   */
  public String Message;

  /**
   * Display text from the localized language. e.g. item.stone for the localized name for stone.
   * This string may also contain one or multiple placeholder (%s). For each placeholder,
   * you must create an entry in TranslateArray. The first %s is TranslateArray[0], the second [1],...
   */
  public String Translate;
  public ArrayList<String> TranslateArray;

  public JSONChatText()
  {
    Color = null;
    HoverEvent = null;
    ClickEvent = null;
    Message = "";
    Translate = "";
    TranslateArray = null;
  }

  public String toString()
  {
    return getConstructed();
  }

  /**
   * Construct the chat object into an useable JSON string for sending
   * 
   * @return
   */
  public String getConstructed()
  {
    JsonObject tResultObject = new JsonObject();

    if( Message != "" )
      tResultObject.addProperty( "text", Message );
    if( Translate != "" )
      tResultObject.addProperty( "translate", Translate );

    if( TranslateArray != null )
      tResultObject.add( "with", new Gson().toJsonTree( TranslateArray ) );

    if( Bold != false )
      tResultObject.addProperty( "bold", true );
    if( Italic != false )
      tResultObject.addProperty( "italic", true );
    if( Underlined != false )
      tResultObject.addProperty( "underlined", true );
    if( Strikethrough != false )
      tResultObject.addProperty( "strikethrough", true );
    if( Obfuscated != false )
      tResultObject.addProperty( "obfuscated", true );
    if( Color != null )
      tResultObject.addProperty( "color", Color.getFriendlyName() );

    if( HoverEvent != null )
      tResultObject.add( "hoverEvent", HoverEvent.getJson() );

    if( ClickEvent != null )
      tResultObject.add( "clickEvent", ClickEvent.getJson() );

    return tResultObject.toString();
  }
}
