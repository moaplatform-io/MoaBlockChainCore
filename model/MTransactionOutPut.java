package m.common.model;

import java.io.Serializable;
import java.math.BigInteger;

public class MTransactionOutPut implements Serializable {

	protected int type;
	protected int utxoId;

	private String address;
	private BigInteger val;
	private String script;

	public int getUtxoId() {
		return utxoId;
	}

	public void setUtxoId(int utxoId) {
		this.utxoId = utxoId;
	}

	public MTransactionOutPut(int type, String address, BigInteger val) {
		this.type = type;
		this.address = address;
		this.val = val;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	@Override
	public String toString() {
		return "CWTransactionOutPut [type=" + type + ", script=" + script + "]";
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigInteger getVal() {
		return val;
	}

	public void setVal(BigInteger val) {
		this.val = val;
	}

}
