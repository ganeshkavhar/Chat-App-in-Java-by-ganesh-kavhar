Code :<br />
// To be run at server side and will act as a server<br />
// MainServer.java</p>
<p>import java.net.*;<br />
import java.io.*;</p>
<p>public class MainServer {<br />
    public static void main(String[] args) throws IOException {</p>
<p>        ServerSocket serverSocket = null;<br />
        try {<br />
            serverSocket = new ServerSocket(4445);<br />
        } catch (IOException e) {<br />
            System.err.println(“Could not listen on port: 4445.”);<br />
            System.exit(1);<br />
        }</p>
<p>        Socket clientSocket = null;<br />
        try {<br />
            clientSocket = serverSocket.accept();<br />
        } catch (IOException e) {<br />
            System.err.println(“Accept failed.”);<br />
            System.exit(1);<br />
        }</p>
<p>      PrintWriter out = new<br />
PrintWriter(clientSocket.getOutputStream(),true);<br />
        BufferedReader in = new BufferedReader(<br />
          new InputStreamReader(<br />
          clientSocket.getInputStream()));<br />
        String inputLine, outputLine;<br />
        MainProtocol kkp = new MainProtocol();</p>
<p>//        outputLine = kkp.processInput(null);<br />
//        out.println(outputLine);</p>
<p>        while ((inputLine = in.readLine()) != null) {<br />
          if (inputLine.equals(“bye”))<br />
                break;<br />
            outputLine = kkp.processInput(inputLine);<br />
            out.println(outputLine);<br />
           outputLine = null;</p>
<p>        }<br />
        out.close();<br />
        in.close();<br />
        clientSocket.close();<br />
        serverSocket.close();<br />
    }<br />
}</p>
<p>// Server side component used to execute commands and returning result<br />
// MainProtocol.java</p>
<p>import java.lang.*;<br />
import java.io.*;<br />
public class MainProtocol {<br />
     String sa,sb;<br />
//     String sb,sa;</p>
<p>     public String processInput(String args) {<br />
          try {<br />
          sb = new String();<br />
          sa  = new String();<br />
          Runtime rt=Runtime.getRuntime();<br />
          Process p=rt.exec(“cmd /c “+ args);<br />
          InputStream is = p.getInputStream();<br />
          BufferedReader br = new BufferedReader(new<br />
InputStreamReader(is));<br />
          while ((sb = br.readLine()) != null)<br />
          {<br />
          sa += sb ;<br />
          //     sa +=”<br />
“;<br />
          }</p>
<p>          p.waitFor();<br />
          System.out.println(“process “+p.exitValue());<br />
          } catch(Exception e) {<br />
          System.out.println(e.getMessage()+ “sumit”);<br />
          }</p>
<p>          return sa ;<br />
     }<br />
}</p>
<p>// Client side component. Commands are to fired from here only<br />
// MainClient.java</p>
<p>import java.io.*;<br />
import java.net.*;</p>
<p>public class MainClient {</p>
<p>    public static void main(String[] args) throws IOException {</p>
<p>          Socket kkSocket = null;<br />
        PrintWriter out = null;<br />
        BufferedReader in = null;<br />
        try {<br />
            kkSocket = new Socket(“10.37.32.74”, 4445);<br />
            out = new PrintWriter(kkSocket.getOutputStream(), true);<br />
            in = new BufferedReader(new<br />
InputStreamReader(kkSocket.getInputStream()));<br />
        } catch (UnknownHostException e) {<br />
            System.err.println(“Exception ” + e);<br />
            System.exit(1);<br />
        } catch (IOException e) {<br />
            System.err.println(“Couldn’t get I/O for the connection.”);<br />
            System.exit(1);<br />
        }</p>
<p>        BufferedReader stdIn = new BufferedReader(new<br />
InputStreamReader(System.in));<br />
        String fromServer;<br />
          String fromUser;</p>
<p>        while ((fromUser = stdIn.readLine()) != “hi”) {<br />
//            System.out.println(“Server: ” + fromServer);<br />
//            if (fromServer.equals(“Bye.”))<br />
//                break;</p>
<p>//            fromUser = stdIn.readLine();</p>
<p>         if (fromUser != null) {<br />
//                System.out.println(“Client: ” + fromUser);<br />
                out.println(fromUser);<br />
          fromServer = in.readLine();<br />
          System.out.println(“Server Response: “+ fromServer);<br />
          fromServer = null;<br />
          if (fromUser.equals(“bye”))<br />
                break;<br />
         }<br />
        }</p>
<p>        out.close();<br />
        in.close();<br />
        stdIn.close();<br />
        kkSocket.close();<br />
    }<br />
}</p>
<p>
