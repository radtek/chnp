package chnp.common.udp.intf;

import java.net.DatagramPacket;
import java.net.SocketException;

public interface Receiver {

	/**<p>监听指定端口并接收UDP数据报。</p>
	 *
	 * @param port UDP端口
	 * @param buffSize 读缓冲大小
	 * @throws SocketException 监听异常
	 */
	void listening(int port, int buffSize) throws SocketException;

	/**<p>每次接收到UDP数据报时调用。</p>
	 *
	 * @param packet UDP数据报
	 */
	void perReceive(DatagramPacket packet);

}