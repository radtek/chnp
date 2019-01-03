package chnp.common.udp;

import chnp.common.udp.entity.Configurator;
import chnp.common.udp.intf.impl.AbstractWorker;
import chnp.common.udp.intf.impl.DefaultReceiver;

import java.lang.reflect.InvocationTargetException;

/**<p>UDP接收器服务类。创建单个接收器线程及指定数量的工作线程。</p>
 *
 * <p>使用方式：</p>
 * <pre>
 * ReceiverService service = new ReceiverService.Builder()
 * 		.withName(String serviceName)
 * 		.withRecvPort(Integer recvPort)
 * 		.withBuffSize(Integer buffSize)
 * 		.withQueueMax(Integer queueMax)
 * 		.withWorkerType(Class workerClazz)
 * 		.withWorkerMax(Integer workerMax)
 * 		.build();
 * service.start();
 * </pre>
 * <p>开发者需要自己实现抽象处理器类AbstractWorker。</p>
 *
 * @author chngzhen@outpook.com
 * @createOn 2018-11-29
 * @updateOn
 */
public class ReceiverService extends Configurator {

	public static class Builder {

		private String serviceName = "UDPService";
		private Class<? extends DefaultReceiver> receiverClass = DefaultReceiver.class;
		private Integer recvPort = 24364;
		private Integer buffSize = 256;
		private Integer queueMax = 200000;
		private Class<? extends AbstractWorker> workerClass;
		private Integer workerMax = 5;

		/**<p>服务名称</p>
		 *
		 * @param serviceName 服务名称。默认：UDPService
		 * @return Builder
		 */
		public Builder withName(String serviceName) {
			if (null != serviceName && !"".equals(serviceName.trim()))
				this.serviceName = serviceName;
			return this;
		}

		/**<p>接收器实现类</p>
		 *
		 * @param clazz 接收器实现类。默认：DefaultReceiver。
		 * @return Builder
		 */
		public Builder withReceiverType(Class<? extends DefaultReceiver> clazz) {
			if (null != clazz)
				this.receiverClass = clazz;
			return this;
		}

		/**<p>接收器监听端口</p>
		 *
		 * @param port UDP接收端口。默认：24364
		 * @return Builder
		 */
		public Builder withRecvPort(Integer port) {
			if (null != port)
				this.recvPort = port;
			return this;
		}

		/**<p>接收器UDP读缓冲大小</p>
		 *
		 * @param size UDP读缓冲大小。默认：256
		 * @return Builder
		 */
		public Builder withBuffSize(Integer size) {
			if (null != size)
				this.buffSize = size;
			return this;
		}

		/**<p>报文队列大小</p>
		 *
		 * @param queueMax 报文队列大小。默认：200000
		 * @return Builder
		 */
		public Builder withQueueMax(Integer queueMax) {
			if (null != queueMax)
				this.queueMax = queueMax;
			return this;
		}

		/**<p>处理器实现类</p>
		 *
		 * @param clazz 处理器实现类
		 * @return Builder
		 */
		public Builder withWorkerType(Class<? extends AbstractWorker> clazz) {
			if (null != clazz)
				this.workerClass = clazz;
			return this;
		}

		/**<p>处理器最大数量</p>
		 *
		 * @param workerMax 处理器最大数量。默认：5
		 * @return Builder
		 */
		public Builder withWorkerMax(Integer workerMax) {
			if (null != workerMax)
				this.workerMax = workerMax;
			return this;
		}

		/**<p>构建UDP接收器服务。</p>
		 *
		 * @return 服务实例
		 * @throws NoSuchMethodException
		 * @throws IllegalAccessException
		 * @throws InvocationTargetException
		 * @throws InstantiationException
		 */
		public ReceiverService build() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
			ReceiverService receiverService = new ReceiverService();

			receiverService.setServiceName(serviceName);

			receiverService.setReceiverClass(receiverClass);
			receiverService.setRecvPort(recvPort);
			receiverService.setBuffSize(buffSize);
			receiverService.setQueueMax(queueMax);

			if (null == workerClass)
				throw new RuntimeException("未指定workerClass");
			receiverService.setWorkerClass(workerClass);
			receiverService.setWorkerMax(workerMax);

			return receiverService;
		}

	}



	private DefaultReceiver receiver;

	/**<p>启动服务</p>
	 *
	 * @throws Exception 接收线程或工作线程实例化失败
	 */
	public void start() throws Exception {
		initWorkers();

		receiver = getReceiverClass()
				.getDeclaredConstructor(Configurator.class)
				.newInstance(this);
		receiver.setDaemon(true);
		receiver.start();
	}

	/**<p>初始化工作线程池</p>
	 *
	 * @throws Exception 工作线程实例化失败
	 */
	private void initWorkers() throws Exception {
		for (int i = 0; i < getWorkerMax(); i++) {
			AbstractWorker worker = getWorkerClass()
					.getDeclaredConstructor(Configurator.class)
					.newInstance(this);
			worker.setDaemon(true);
			worker.setName(getServiceName() + worker.getName());
			worker.start();
		}
	}

	/**<p>结束服务</p>
	 *
	 */
	public void stop() {
		if (null != receiver) {
			receiver.shutdown();
		}
	}

}
