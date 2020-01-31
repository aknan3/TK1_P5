import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class Airport {
	private static int PORT = 27780;
	private ServerSocket serverSocket;
	Hangar H1;
	Hangar H2;
	Hangar H3;
	private DatagramSocket AirSocket;
	
	public Airport() {
		
		try {
			AirSocket = new DatagramSocket(PORT);
			serverSocket = new ServerSocket(PORT);
			System.out.println("Server started on port: " + PORT);
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		try {
//			H1 = new Hangar(serverSocket.accept(), 0);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		new Thread(H1).start();
		
		new Thread(H2).start();
		new Thread(H3).start();
	}
	
	public static void main(String[] args) {
		
		new Airport();
		
	
	}
	
	
	private class Hangar implements Runnable {
		private DatagramSocket hangarSocket;
		
		private ArrayList<Airplane> airplanes;
		Queue<Airplane> queueOne =new LinkedList<Airplane>();
		Queue<Airplane> queueTwo =new LinkedList<Airplane>();
		public Hangar(DatagramSocket hangar, int numberOfairplanes) {
			this.hangarSocket = hangar;
			
			
			for(int x = numberOfairplanes; x <= numberOfairplanes + 10; x++)
				airplanes.add(new Airplane(x));
			
//			try {
//				//socket = new DatagramSocket();
//			} catch (SocketException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
		
		
		
		public void addAirplane(int number) {
			
			airplanes.add(new Airplane(number));
			
			
		}
		
		
		public void sendAirplane(Queue<Airplane> queue) {
			
			
			/*
			 * generates a FIFO structure and fills it with a random among of airplanes
			 */
			long rnd = ThreadLocalRandom.current().nextLong(1, 5 + 1);
			for(int i=0; i < rnd; i++) {
				queue.add(airplanes.get(i));
				airplanes.remove(i);
			}
			byte[] q = (queue+"").getBytes();
			
			
			
			int i = 8;
			InetAddress ia;
			byte[] b = (i+"").getBytes();
			try {
				 ia = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DatagramPacket dp = new DatagramPacket(b, b.length, ia,9999);
			hangarSocket.send(dp);
			
		}
		
		@Override
		public void run() {
			
			
			// send airplanes
			
		}
		
		
	
	}

}
