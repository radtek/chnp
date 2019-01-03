package chnp.common.udp.intf;

import java.net.DatagramPacket;

public interface Worker {

	/**<p>基于UDP数据报的业务逻辑。</p>
	 *
	 * @param packet UDP数据报
	 */
	void execute(DatagramPacket packet);

	void onNoMessage();

}