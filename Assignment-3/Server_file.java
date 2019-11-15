import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        System.out.println("Server is Ready");
        String path = "E:/3Y2S/networking/star.JPG";
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept();
            System.out.println("Connected");
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String str = (String) dataInputStream.readUTF();
            path = path + str;
            System.out.println("file name: " + str);
            str = (String) dataInputStream.readUTF();
            int fileSize = Integer.parseInt(str);
            System.out.println("file size: " + str);

            // receive file
            byte[] mybytearray = new byte[fileSize];
            InputStream is = socket.getInputStream();
            fos = new FileOutputStream(path);
            bos = new BufferedOutputStream(fos);
            bytesRead = is.read(mybytearray, 0, mybytearray.length);
            current = bytesRead;
            do {
                bytesRead = is.read(mybytearray, current, mybytearray.length - current);
                if (bytesRead >= 0){
                    current += bytesRead;
                }
                    
            } while(bytesRead > 0);
            bos.write(mybytearray, 0, current);
            bos.flush();
            System.out.println("File " + path + " downloaded (" + current + " bytes read)");

            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}