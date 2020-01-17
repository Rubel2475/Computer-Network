import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("Client started..");
			Socket soc = new Socket("localhost",3333);
			System.out.println("Success");
			
			while(true) {
			
				BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Client says: ");
				String str = userInput.readLine();
				PrintWriter out = new PrintWriter(soc.getOutputStream(),true);
				out.println(str);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				System.out.println(in.readLine());
				
				if(str.equals("EXIT")) break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
