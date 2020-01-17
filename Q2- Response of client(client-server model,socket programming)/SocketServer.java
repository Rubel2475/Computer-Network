import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SocketServer {
	
	private static DataInputStream dataIn = null;
	private static DataOutputStream dataOut = null;
	
	//To Store Local Date and Time
	private static LocalDate date = null;
	private static LocalTime time = null;
	
	//To Format Date and Time
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-YYYY");
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("Waiting for client....");
			ServerSocket ss= new ServerSocket(3333);
			Socket soc = ss.accept();
			System.out.println("Connection established.");
			
			dataIn = new DataInputStream(soc.getInputStream());
			dataOut = new DataOutputStream(soc.getOutputStream());
				
			while(true) {
				
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				String str = in.readLine();
				System.out.println(str);
				
				if(str.equals("EXIT")){
					PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
					out.println("The reply of server: " + "Communication closed");
					break;
				}
				else if(str.equals("Hello")){
					PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
					out.println("The reply of server: " + "Welcome");
				}
				else if(str.equals("Date")){
					date = LocalDate.parse(LocalDate.now().toString()); //converting local date format to our given format
					PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
					out.println("The reply of server: " + dateFormatter.format(date));
				}
				else if(str.equals("Time")){
					time = LocalTime.parse(LocalTime.now().toString()); //converting local time format to our given format
					PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
					out.println("The reply of server: " + timeFormatter.format(time));
				}
				
				else if(str.equals("DateTime")) {
					time = LocalTime.parse(LocalTime.now().toString());
					date = LocalDate.parse(LocalDate.now().toString());
					PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
					out.println("The reply of server: " + "Date: "+dateFormatter.format(date) + "\t" + "Time: "+timeFormatter.format(time));
				}
				
				else {
					PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
					out.println("The reply of server: Unexpected input");
				}
			}	
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
