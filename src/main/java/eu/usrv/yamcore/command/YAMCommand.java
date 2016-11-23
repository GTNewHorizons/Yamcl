
package eu.usrv.yamcore.command;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import eu.usrv.yamcore.YAMCore;
import eu.usrv.yamcore.auxiliary.IntHelper;
import eu.usrv.yamcore.auxiliary.ItemDescriptor;
import eu.usrv.yamcore.auxiliary.PlayerChatHelper;
import eu.usrv.yamcore.auxiliary.classes.JSONChatText;
import eu.usrv.yamcore.auxiliary.classes.JSONClickEvent;
import eu.usrv.yamcore.auxiliary.classes.JSONHoverEvent;


public class YAMCommand implements ICommand, ICommandSender
{
  private List aliases;

  public YAMCommand()
  {
    this.aliases = new ArrayList();
  }

  @Override
  public int compareTo( Object arg0 )
  {
    return 0;
  }

  @Override
  public String getCommandName()
  {
    return "yamcore";
  }

  @Override
  public String getCommandUsage( ICommandSender p_71518_1_ )
  {
    return "derp";
  }

  @Override
  public List getCommandAliases()
  {

    return this.aliases;
  }

  @Override
  public void processCommand( ICommandSender pCmdSender, String[] pArgs )
  {
    int tFunc = 1;
    if( IntHelper.tryParse( pArgs[0] ) )
      tFunc = Integer.parseInt( pArgs[0] );

    JSONChatText jct = new JSONChatText();
    JSONChatText jct2 = new JSONChatText();
    JSONChatText jct3 = new JSONChatText();
    JSONChatText jct4 = new JSONChatText();

    jct.Message = "#1";
    jct2.Message = "#2";
    jct2.Color = EnumChatFormatting.GOLD;
    jct3.Message = "#3";
    jct3.HoverEvent = JSONHoverEvent.SimpleText( "Hover #3" );
    jct4.Message = "#4";
    jct4.HoverEvent = JSONHoverEvent.SimpleText( "Hover #4" );
    jct4.Color = EnumChatFormatting.AQUA;
    jct4.Italic = true;
    jct4.ClickEvent = JSONClickEvent.suggestCommand( "Command #4" );

    if( tFunc == 1 )
    {
      jct.Message = "Simple message";
    }
    else if( tFunc == 2 )
    {
      jct.Message = "Message with tooltip;";
      jct.HoverEvent = JSONHoverEvent.SimpleText( "A simple text" );
    }
    else if( tFunc == 3 )
    {
      jct.Message = "[Stone]";
      jct.HoverEvent = JSONHoverEvent.Item( ItemDescriptor.fromString( "minecraft:stone" ) );
    }
    else if( tFunc == 4 )
    {
      jct.Message = "Suggest command";
      jct.ClickEvent = JSONClickEvent.suggestCommand( "/suicide" );
    }
    else if( tFunc == 5 )
    {
      jct.Message = "Seed";
      jct.ClickEvent = JSONClickEvent.runCommand( "/suicide" );
    }
    else if( tFunc == 6 )
    {
      jct.Message = "Woo, golden!";
      jct.Color = EnumChatFormatting.GOLD;
    }
    else if( tFunc == 7 )
    {
      PlayerChatHelper.SendJsonRaw( (EntityPlayer) pCmdSender, jct, jct2, jct3, jct4 );
      return;
    }
    else if( tFunc == 8 )
    {
      try
      {
        PlayerChatHelper.SendJsonFormatted( (EntityPlayer) pCmdSender, "Json1: [%s], Json2: [%s], Json3: [%s], Json4: [%s]", jct, jct2, jct3, jct4 );
      }
      catch( Exception e )
      {
        e.printStackTrace();
      }
    }
    else if( tFunc == 9 )
    {
    }
    else if( tFunc == 10 )
    {
    }

    MinecraftServer minecraftserver = MinecraftServer.getServer();

    if( minecraftserver != null )
    {
      ICommandManager icommandmanager = minecraftserver.getCommandManager();
      String tJson = jct.getConstructed();
      YAMCore.instance.getLogger().info( tJson );
      icommandmanager.executeCommand( this, "tellraw " + pCmdSender.getCommandSenderName() + " " + jct.getConstructed() );
    }
  }

  @Override
  public boolean canCommandSenderUseCommand( ICommandSender pCommandSender )
  {
    return true;
  }

  @Override
  public List addTabCompletionOptions( ICommandSender p_71516_1_,
      String[] p_71516_2_ )
  {
    return null;
  }

  @Override
  public boolean isUsernameIndex( String[] p_82358_1_, int p_82358_2_ )
  {
    return false;
  }

  @Override
  public String getCommandSenderName()
  {
    return "YAMCoreDebug";
  }

  @Override
  public IChatComponent func_145748_c_()
  {
    return null;
  }

  @Override
  public void addChatMessage( IChatComponent p_145747_1_ )
  {
  }

  @Override
  public boolean canCommandSenderUseCommand( int p_70003_1_, String p_70003_2_ )
  {
    return true;
  }

  @Override
  public ChunkCoordinates getPlayerCoordinates()
  {
    return null;
  }

  @Override
  public World getEntityWorld()
  {
    return null;
  }

}
