
package eu.usrv.yamcore.auxiliary;


import java.util.ArrayList;
import java.util.Formatter;

import eu.usrv.yamcore.YAMCore;
import eu.usrv.yamcore.auxiliary.classes.JSONChatText;

import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;


/**
 * Method to easily send chat-messages to EntityPlayer
 * 
 * @author Namikon
 * 
 */
public class PlayerChatHelper
{
  /**
   * Just plain white text without any format or color
   * 
   * @param pCommandSender
   * @param pMessage
   */
  public static void SendPlain( ICommandSender pCommandSender, String pMessage )
  {
    pCommandSender.addChatMessage( new ChatComponentText( pMessage ) );
  }

  /**
   * Just plain white text without any format or color
   * Just pass your args. No need for String.format()
   * 
   * @param pCommandSender
   * @param pMessage
   * @param pArgs
   */
  public static void SendPlain( ICommandSender pCommandSender, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendPlain( pCommandSender, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Just plain white text without any format or color
   * 
   * @param pPlayer
   * @param pMessage
   */

  public static void SendPlain( EntityPlayer pPlayer, String pMessage )
  {
    pPlayer.addChatMessage( new ChatComponentText( pMessage ) );
  }

  /**
   * Just plain white text without any format or color
   * Just pass your args. No need for String.format()
   * 
   * @param pPlayer
   * @param pMessage
   */
  public static void SendPlain( EntityPlayer pPlayer, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendPlain( pPlayer, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be GREEN
   * 
   * @param pCommandSender
   * @param pMessage
   */

  public static void SendInfo( ICommandSender pCommandSender, String pMessage )
  {
    pCommandSender.addChatMessage( new ChatComponentText( EnumChatFormatting.GREEN + pMessage ) );
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be GREEN
   * Just pass your args. No need for String.format()
   * 
   * @param pCommandSender
   * @param pMessage
   */
  public static void SendInfo( ICommandSender pCommandSender, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendInfo( pCommandSender, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be RED
   * 
   * @param pCommandSender
   * @param pMessage
   */

  public static void SendError( ICommandSender pCommandSender, String pMessage )
  {
    pCommandSender.addChatMessage( new ChatComponentText( EnumChatFormatting.RED + pMessage ) );
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be YELLOW
   * 
   * @param pCommandSender
   * @param pMessage
   */

  public static void SendWarn( ICommandSender pCommandSender, String pMessage )
  {
    pCommandSender.addChatMessage( new ChatComponentText( EnumChatFormatting.YELLOW + pMessage ) );
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be RED
   * Just pass your args. No need for String.format()
   * 
   * @param pCommandSender
   * @param pMessage
   */
  public static void SendError( ICommandSender pCommandSender, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendError( pCommandSender, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be YELLOW
   * Just pass your args. No need for String.format()
   * 
   * @param pCommandSender
   * @param pMessage
   */
  public static void SendWarn( ICommandSender pCommandSender, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendWarn( pCommandSender, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be GREEN
   * 
   * @param pPlayer
   * @param pMessage
   */

  public static void SendInfo( EntityPlayer pPlayer, String pMessage )
  {
    pPlayer.addChatMessage( new ChatComponentText( EnumChatFormatting.GREEN + pMessage ) );
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be RED
   * 
   * @param pPlayer
   * @param pMessage
   */

  public static void SendError( EntityPlayer pPlayer, String pMessage )
  {
    pPlayer.addChatMessage( new ChatComponentText( EnumChatFormatting.RED + pMessage ) );
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be YELLOW
   * 
   * @param pPlayer
   * @param pMessage
   */

  public static void SendWarn( EntityPlayer pPlayer, String pMessage )
  {
    pPlayer.addChatMessage( new ChatComponentText( EnumChatFormatting.YELLOW + pMessage ) );
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be GREEN
   * Just pass your args. No need for String.format()
   * 
   * @param pPlayer
   * @param pMessage
   */

  public static void SendInfo( EntityPlayer pPlayer, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendInfo( pPlayer, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be RED
   * Just pass your args. No need for String.format()
   * 
   * @param pPlayer
   * @param pMessage
   */

  public static void SendError( EntityPlayer pPlayer, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendError( pPlayer, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Meant for notifications that are being send to an admin/op
   * Color will be YELLOW
   * Just pass your args. No need for String.format()
   * 
   * @param pPlayer
   * @param pMessage
   */

  public static void SendWarn( EntityPlayer pPlayer, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendWarn( pPlayer, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Meant for ingame notifications that are being send to a player, not an admin/op
   * Color will be DARK_GREEN
   * 
   * @param pPlayer
   * @param pMessage
   */

  public static void SendNotifyPositive( EntityPlayer pPlayer, String pMessage )
  {
    pPlayer.addChatMessage( new ChatComponentText( EnumChatFormatting.DARK_GREEN + pMessage ) );
  }

  /**
   * Meant for ingame notifications that are being send to a player, not an admin/op
   * Color will be AQUA
   * 
   * @param pPlayer
   * @param pMessage
   */
  public static void SendNotifyNormal( EntityPlayer pPlayer, String pMessage )
  {
    pPlayer.addChatMessage( new ChatComponentText( EnumChatFormatting.AQUA + pMessage ) );
  }

  /**
   * Meant for ingame notifications that are being send to a player, not an admin/op
   * Color will be DARK_PURPLE
   * 
   * @param pPlayer
   * @param pMessage
   */
  public static void SendNotifyWarning( EntityPlayer pPlayer, String pMessage )
  {
    pPlayer.addChatMessage( new ChatComponentText( EnumChatFormatting.DARK_PURPLE + pMessage ) );
  }

  /**
   * Meant for ingame notifications that are being send to a player, not an admin/op
   * Color will be DARK_GREEN
   * Just pass your args. No need for String.format()
   * 
   * @param pPlayer
   * @param pMessage
   */
  public static void SendNotifyPositive( EntityPlayer pPlayer, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendNotifyPositive( pPlayer, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Meant for ingame notifications that are being send to a player, not an admin/op
   * Color will be AQUA
   * Just pass your args. No need for String.format()
   * 
   * @param pPlayer
   * @param pMessage
   */
  public static void SendNotifyNormal( EntityPlayer pPlayer, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendNotifyNormal( pPlayer, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Meant for ingame notifications that are being send to a player, not an admin/op
   * Color will be DARK_PURPLE
   * Just pass your args. No need for String.format()
   * 
   * @param pPlayer
   * @param pMessage
   */
  public static void SendNotifyWarning( EntityPlayer pPlayer, String pMessage, Object... pArgs )
  {
    try( Formatter f = new Formatter() )
    {
      SendNotifyWarning( pPlayer, f.format( pMessage, pArgs ).toString() );
    }
  }

  /**
   * Send any combination of links, colors, Tooltips like you would with String.format().
   * Use the following tokens to fill in the json chat objects:
   * "This is a {0} sample message that will {1} display elements based on {0} their index value {2}"
   * {0} refers to the first given object, {1} to the second, etc.
   * If you want to use { and } in your text, escape them like any other special char: \{
   * 
   * @param pPlayer The player to send this message to
   * @param pMessage The message template with tokens
   * @param pChatObjects
   * @throws Exception
   */
  public static void SendJsonFormatted( EntityPlayer pPlayer, String pMessage, JSONChatText... pChatObjects ) throws Exception
  {
    char[] tMessageArray = pMessage.toCharArray();
    ArrayList<JSONChatText> tMessageParts = new ArrayList<JSONChatText>();
    int tokenLastEnd = -1;
    int tokenStart = -1;
    int tokenEnd = -1;

    for( int i = 0; i < tMessageArray.length; i++ )
    {
      if( tMessageArray[i] == '{' ) // Token found
      {
        if( i > 0 ) // Can we check for -1?
        {
          if( tMessageArray[i - 1] != '\\' ) // Is this token escaped?
          {
            // Token not escaped. Let's see if we already have a start-token
            if( tokenStart > -1 )
              throw new Exception( String.format( "Unescaped token-start symbol found at position %d", i ) ); // Crash
            else
            {
              tokenStart = i; // we don't have a start-token yet. Store it
              //YAMCore.instance.getLogger().info( String.format( "Found opening token at %d", i ) );
            }
          }
          // Else: Skip this token. it is escaped and part of the original message
        }
        else
        {
          // We can't check. It's the first char, so it is definitely a token to be checked
          tokenStart = i;
          //YAMCore.instance.getLogger().info( String.format( "Found opening token at %d", i ) );
        }
      }

      if( tMessageArray[i] == '}' )
      {
        if( i > 0 ) // Can we check for -1?
        {
          if( tMessageArray[i - 1] != '\\' ) // Is this token escaped?
          {
            // Token not escaped. Let's see if we already have a start-token that this close one relates to
            if( tokenStart > -1 )
            {
              tokenEnd = i; // we do have a start-token
              //YAMCore.instance.getLogger().info( String.format( "Found end token at %d", i ) );
            }
            else
              // We don't have one
              throw new Exception( String.format( "Unescaped token-start symbol found at position %d", i ) ); // Crash
          }
          // Else: Skip this token. it is escaped and part of the original message
        }
        else
          // We can't check. It's the first char. And thus fails, there can't be a start token
          throw new Exception( String.format( "Unescaped token-start symbol found at position %d", i ) ); // Crash
      }

      // Both start&end is set. Analyse it
      if( tokenStart > -1 && tokenEnd > -1 )
      {
        //YAMCore.instance.getLogger().info( String.format( "Start: %d End: %d", tokenStart, tokenEnd ) );
        // Create an empty json body with the text and add it to the collection
        tMessageParts.add( extractSubStringToJSON( pMessage, tokenLastEnd + 1, tokenStart - 1 ) );

        // Extract the Arg-Nr. that is supposed to fill the gap
        String tArgNr = pMessage.substring( tokenStart + 1, tokenEnd );
        if( !IntHelper.tryParse( tArgNr ) )
          throw new IllegalArgumentException( String.format( "Token-Element at pos %d is not an integer (%s)", tokenStart + 1, tArgNr ) );

        int tArg = Integer.parseInt( tArgNr );

        if( pChatObjects.length < tArg )
          throw new IndexOutOfBoundsException( String.format( "Token-ID %d requested, but only given %d !", tArg, pChatObjects.length ) );

        // Add the messagepart from given ChatObjects
        tMessageParts.add( pChatObjects[tArg] );

        // Store last known end-token in var, reset start & end and continue loop
        tokenLastEnd = tokenEnd + 1;
        tokenStart = -1;
        tokenEnd = -1;
      }
    }

    // If there is more text after the last close-tag, copy that now
    if( tokenLastEnd < tMessageArray.length )
      tMessageParts.add( extractSubStringToJSON( pMessage, tokenLastEnd, tMessageArray.length - 1 ) );

    // Send assembled message
    JSONChatText[] tJsonMessages = new JSONChatText[tMessageParts.size()];
    tJsonMessages = tMessageParts.toArray( tJsonMessages );
    SendJsonRaw( pPlayer, tJsonMessages );
  }

  private static JSONChatText extractSubStringToJSON( String pMessage, int pStart, int pEnd )
  {
    // Exctact the text between the last known end-token and the new start one
    String tMessagePart = pMessage.substring( pStart, pEnd );

    // Get rid of the escape-chars
    tMessagePart = tMessagePart.replace( "\\{", "{" );
    tMessagePart = tMessagePart.replace( "\\}", "}" );

    // Create an empty json body with the text and add it to the collection
    // YAMCore.instance.getLogger().info( String.format("Extracted [%s] from message. Start: %d End: %d", tMessagePart,
    // pStart, pEnd) );
    return JSONChatText.simpleMessage( tMessagePart );
  }

  /**
   * Send variable number of JSON Objects to a player.
   * Note: It is recommended to use SendJsonFormatted() if you want to format a text
   * This function will just glue all elements together!
   * 
   * @param pPlayer
   * @param pChatObjects
   */
  public static void SendJsonRaw( EntityPlayer pPlayer, JSONChatText... pChatObjects )
  {
    MinecraftServer tMCServer = MinecraftServer.getServer();
    ICommandManager tCmdMngr = tMCServer.getCommandManager();

    if( tMCServer != null )
    {
      String tConstructedJSON = "";
      if( pChatObjects.length > 1 )
      {
        boolean tFirst = true;
        tConstructedJSON = "[";
        for( JSONChatText pChatObject : pChatObjects )
        {
          if( tFirst )
            tFirst = false;
          else
            tConstructedJSON += ", ";

          tConstructedJSON += pChatObject.getConstructed();
        }
        tConstructedJSON += "]";
      }
      else
        tConstructedJSON = pChatObjects[0].getConstructed();

      tCmdMngr.executeCommand( tMCServer, "tellraw " + pPlayer.getCommandSenderName() + " " + tConstructedJSON );
    }
  }
}
