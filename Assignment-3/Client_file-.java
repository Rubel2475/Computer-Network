import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        String path = "E:/3Y2S/networking/temp.JPG";
        // System.out.println(fileName);
        try {

            Socket socket = new Socket("localhost", 6666);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String[] patharray = path.split("/");
            String fileName = patharray[patharray.length - 1];
            File myFile = new File(path);
            byte[] mybytearray = new byte[(int) myFile.length()];

            String fileSize = Long.toString(myFile.length());

            out.writeUTF(fileName);
            out.flush();
            out.writeUTF(fileSize);
            out.flush();
            // Transfering File
            fis = new FileInputStream(myFile);
            bis = new BufferedInputStream(fis);
            bis.read(mybytearray, 0, mybytearray.length);
            os = socket.getOutputStream();
            System.out.println("Sending " + path + "(" + mybytearray.length + " bytes)");
            os.write(mybytearray, 0, mybytearray.length);
            os.flush();
            System.out.println("Done.");
            out.close();
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}