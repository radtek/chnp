package chnp.common.udp.intf.impl;

import chnp.common.udp.entity.Configurator;
import chnp.common.udp.intf.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class DefaultReceiver extends Thread implements Receiver {
	private final Logger log = LoggerFactory.getLogger(DefaultReceiver.class);

	private DatagramSocket receiverSocket;
	protected Configurator configurator;
	public DefaultReceiver(Configurator configurator) {
		this.configurator = configurator;
	}

	@Override
	public void run() {
		try {
			listening(configurator.getRecvPort(), configurator.getBuffSize());
		} catch (Exception e) {
			log.error("********** UDP接收器启动失败 **********", e);
		}
	}

	@Override
	public void listening(int port, int buffSize) throws SocketException {
		receiverSocket = new DatagramSocket(port);
		log.info("********** UDP接收器已启动。端口 " + port + " **********");

		while(!receiverSocket.isClosed()) {
			try {
				// 监听端口并接受消息
				byte[] bytes = new byte[buffSize];
				DatagramPacket packet = new DatagramPacket(bytes, buffSize);
				receiverSocket.receive(packet);

				perReceive(packet);
			}catch (Exception ignore) {
				log.error("UDP数据包接收失败", ignore);
			}
		}
	}

	@Override
	public void perReceive(DatagramPacket packet) {
		if (configurator.getQueueMax() > configurator.messages.size()) {
			// 消息入队列
			configurator.messages.offer(packet);
		}else log.info("待处理消息队列已满，考虑增加工作线程或增大队列上限");
	}

	public void shutdown() {
		if (null != receiverSocket && !receiverSocket.isClosed()) {
			receiverSocket.close();
		}
	}

}