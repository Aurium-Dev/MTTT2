package com.elytraforce.mttt2.utils;

import org.bukkit.entity.Player;

public class ForceRespawnHandler
{
	
	//use sexy reflection thing to make cool sh*t happen
	public static void sendRespawnPacket(Player p) throws Exception
	{
		 Object nmsPlayer = p.getClass().getMethod("getHandle").invoke(p);
		 Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
		 Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");
		  
		 for(Object ob : enumClass.getEnumConstants())
	 	 {
			  if(ob.toString().equals("PERFORM_RESPAWN"))
	 	 	 {
		 	 	 packet = packet.getClass().getConstructor(enumClass).newInstance(ob);
			  }
		 }
		  
		 Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
		 con.getClass().getMethod("a", packet.getClass()).invoke(con, packet);
	}
}
