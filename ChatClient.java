import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
   public ChatClient() {
   }

   public static void main(String[] var0) throws Exception {
      Socket var1 = new Socket("localhost", 1234);
      BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream()));
      PrintWriter var3 = new PrintWriter(var1.getOutputStream(), true);
      BufferedReader var4 = new BufferedReader(new InputStreamReader(System.in));
      (new Thread(() -> {
         while(true) {
            try {
               String var1;
               if ((var1 = var2.readLine()) != null) {
                  System.out.println(var1);
                  continue;
               }
            } catch (Exception var2x) {
            }

            return;
         }
      })).start();

      String var5;
      while((var5 = var4.readLine()) != null) {
         var3.println(var5);
      }

   }
}
