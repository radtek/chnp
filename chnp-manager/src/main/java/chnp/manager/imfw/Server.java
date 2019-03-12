package chnp.manager.imfw;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author chngzhen@outlook.com
 * @date 2019/3/3
 */
public class Server {


	public static void main(String[] args) {
		try {
			ServerSocketFactory ssf = ServerSocketFactory.getDefault();
			SSLServerSocket sslSocket = (SSLServerSocket) ssf.createServerSocket(29120);
			sslSocket.setNeedClientAuth(true);

			Socket socket = sslSocket.accept();

			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			System.out.println("Server Log:"+in.readLine());
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}