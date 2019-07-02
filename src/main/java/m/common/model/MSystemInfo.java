package m.common.model;

import java.io.Serializable;

public class MSystemInfo implements Serializable {
	/**
	 * cpu 사용량 
	 */
	private int cpu;
	/**
	 * ram 사용량 
	 */
	private long ram;
	/**
	 * uptime
	 */
	private long uptime;

	public int getCpu() {
		return cpu;
	}

	public void setCpu(int cpu) {
		this.cpu = cpu;
	}

	public long getRam() {
		return ram;
	}

	public void setRam(long ram) {
		this.ram = ram;
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	@Override
	public String toString() {
		return "CwSystemInfo [cpu=" + cpu + ", ram=" + ram + ", uptime=" + uptime + "]";
	}

	
	
}
