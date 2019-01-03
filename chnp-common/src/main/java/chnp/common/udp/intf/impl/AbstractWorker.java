package chnp.common.udp.intf.impl;

import chnp.common.udp.entity.Configurator;
import chnp.common.udp.intf.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.DatagramPacket;

public abstract class AbstractWorker extends Thread implements Worker {
	private final Logger log = LoggerFactory.getLogger(AbstractWorker.class);

	private boolean keep = true;
	private Configurator configurator;
	public AbstractWorker(Configurator configurator) {
		this.configurator = configurator;
	}

	@Override
	public void run() {
		while(keep) {
			try {
				if (configurator.messages.size() > 0) {
					DatagramPacket packet = configurator.messages.poll();
					if (null != packet) execute(packet);
				}else {
					onNoMessage();
				}
			}catch (Exception e) {
				log.error("UDP数据报操作错误", e);
			}
		}
	}

	@Override
	public void onNoMessage() {}

	public void shutdown() {
		this.keep = false;
	}

}