package m.common.model;

import java.math.BigInteger;

public class MAccount {
	private String address;
	private BigInteger amount;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

}
