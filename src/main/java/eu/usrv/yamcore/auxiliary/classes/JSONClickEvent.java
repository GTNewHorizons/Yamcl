
package eu.usrv.yamcore.auxiliary.classes;


import com.google.gson.JsonObject;


public class JSONClickEvent
{
  public enum EventTypeEnum
  {
    RUN_COMMAND,
    SUGGEST_COMMAND,
    OPEN_URL,
  }

  public EventTypeEnum EventType;
  public String Text;

  /**
   * Makes the user run any specific command without asking further questions
   * 
   * @param pCommand
   * @return
   */
  public static JSONClickEvent runCommand( String pCommand )
  {
    return new JSONClickEvent( EventTypeEnum.RUN_COMMAND, pCommand );
  }

  /**
   * Will suggest a command. This will open chat, place your command but it will wait for the
   * player to hit return to send it
   * 
   * @param pCommand
   * @return
   */
  public static JSONClickEvent suggestCommand( String pCommand )
  {
    return new JSONClickEvent( EventTypeEnum.SUGGEST_COMMAND, pCommand );
  }

  /**
   * Will open the provided URL. use this to create short links
   * The player has to accept to open the URL via a popup, just like clicking a regular link
   * 
   * @param pURL The URL to open
   * @return
   */
  public static JSONClickEvent openURL( String pURL )
  {
    return new JSONClickEvent( EventTypeEnum.OPEN_URL, pURL );
  }

  private JSONClickEvent( EventTypeEnum pEventType, String pText )
  {
    EventType = pEventType;
    Text = pText;
  }

  /**
   * Return the constructed JSON Element. Use it in your own chat-handler, or pass it to JSONChatText class
   * 
   * @return
   */
  public JsonObject getJson()
  {
    JsonObject tJO = new JsonObject();
    tJO.addProperty( "action", EventType.toString().toLowerCase() );
    tJO.addProperty( "value", Text );
    return tJO;
  }
}
