package chnp.common.udp.entity;

import chnp.common.udp.intf.impl.AbstractWorker;
import chnp.common.udp.intf.impl.DefaultReceiver;

import java.net.DatagramPacket;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Configurator {

	private String serviceName;

	private Class<? extends DefaultReceiver> receiverClass;
	private Integer recvPort;
	private Integer buffSize;
	private Integer queueMax;
	public ConcurrentLinkedQueue<DatagramPacket> messages = new ConcurrentLinkedQueue<>();

	private Class<? extends AbstractWorker> workerClass;
	private int workerMax;

	public Class<? extends DefaultReceiver> getReceiverClass() {
		return receiverClass;
	}

	public void setReceiverClass(Class<? extends DefaultReceiver> receiverClass) {
		if (null == receiverClass)
			throw new IllegalArgumentException("Argument 'receiverClass' cannot be null");
		this.receiverClass = receiverClass;
	}

	public Integer getBuffSize() {
		return buffSize;
	}

	public void setBuffSize(Integer buffSize) {
		if (null == buffSize)
			throw new IllegalArgumentException("Argument 'buffsize' cannot be null");
		this.buffSize = buffSize;
	}

	public Integer getRecvPort() {
		return recvPort;
	}

	public void setRecvPort(Integer recvPort) {
		if (null == recvPort)
			throw new IllegalArgumentException("Argument 'recvPort' cannot be null");
		this.recvPort = recvPort;
	}

	public Class<? extends AbstractWorker> getWorkerClass() {
		return workerClass;
	}

	public void setWorkerClass(Class<? extends AbstractWorker> workerClass) {
		if (null == workerClass)
			throw new IllegalArgumentException("Argument 'workerClass' cannot be null");
		this.workerClass = workerClass;
	}

	public Integer getWorkerMax() {
		return workerMax;
	}

	public void setWorkerMax(Integer workerMax) {
		if (null == workerMax)
			throw new IllegalArgumentException("Argument 'workerMax' cannot be null");
		this.workerMax = workerMax;
	}

	public Integer getQueueMax() {
		return queueMax;
	}

	public void setQueueMax(Integer queueMax) {
		if (null == queueMax)
			throw new IllegalArgumentException("Argument 'queueMax' cannot be null");
		this.queueMax = queueMax;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}