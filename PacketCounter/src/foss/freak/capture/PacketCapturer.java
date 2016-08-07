package foss.freak.capture;



import java.io.IOException;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.PacketReceiver;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;


public class PacketCapturer {
	
	public static void main(String args[]) throws IOException
	{
		
		NetworkInterface device[] = JpcapCaptor.getDeviceList();
		for(NetworkInterface ifc: device)
			System.out.println(ifc.datalink_description);
		JpcapCaptor jpcap = JpcapCaptor.openDevice(device[0], 128, true, 1000);
		jpcap.loopPacket(-1, new PacketReceiver() {
			
			@Override
			public void receivePacket(Packet packet) {				
			System.out.println(packet);					
			}
		});
		
		
	}

}
