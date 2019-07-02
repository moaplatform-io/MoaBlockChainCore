package m.common.model;

import java.io.Serializable;
import java.math.BigInteger;

public class MUTXO implements Serializable {
	private static final long serialVersionUID = -8393672331415179250L;

	public static final class UTXO_TYPE {
		public static final int STD = 1;
		public static final int COIN = 2;
		public static final int POINT = 3;
	}

	public static final class UTXO_ID {
		public static final int MOA_POINT = 101;
		public static final int MOA_COIN = 100;
	}

	private String p_address;

	private String hash;
	private BigInteger value;
	private int height;
	private int utxoType;
	private String address;
	private int utxoID;
	private String txid;
	private long lockTime;
	private boolean isConfirm;
	private int encrypt_type;

	public String getP_address() {
		return p_address;
	}

	public void setP_address(String p_address) {
		this.p_address = p_address;
	}

	private String use_transaction_hash;

	private int lockType;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public int getEncrypt_type() {
		return encrypt_type;
	}

	public void setEncrypt_type(int encrypt_type) {
		this.encrypt_type = encrypt_type;
	}

	public int getUtxoID() {
		return utxoID;
	}

	public String getUse_transaction_hash() {
		return use_transaction_hash;
	}

	public void setUse_transaction_hash(String use_transaction_hash) {
		this.use_transaction_hash = use_transaction_hash;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}

	public void setUtxoID(int utxoID) {
		this.utxoID = utxoID;
	}

	public boolean isConfirm() {
		return isConfirm;
	}

	public void setConfirm(boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public long getLockTime() {
		return lockTime;
	}

	public void setLockTime(long lockTime) {
		this.lockTime = lockTime;
	}

	public MUTXO(BigInteger value, int utxoType, String address, int utxtoID) {
		this.value = value;
		this.utxoType = utxoType;
		this.address = address;
		this.utxoID = utxtoID;
	}

	public MUTXO() {

	}

	public MUTXO(MUTXO mutxo) {
		this.address = mutxo.getAddress();
		this.encrypt_type = mutxo.getEncrypt_type();
		this.hash = mutxo.getHash();
		this.height = mutxo.getHeight();
		this.lockTime = mutxo.getLockTime();
		this.lockType = mutxo.getLockType();
		this.txid = mutxo.getTxid();
		this.utxoType = mutxo.getUtxoType();
		this.utxoID = mutxo.getUtxoID();
		this.value = mutxo.getValue();
		this.p_address=mutxo.p_address;
		this.encrypt_type=mutxo.encrypt_type;
		this.height=mutxo.height;
	}

	/** The value which this Transaction output holds. */
	public BigInteger getValue() {
		return value;
	}

	/** Gets the height of the block that created this output. */
	public int getHeight() {
		return height;
	}

	public int getUtxoType() {
		return utxoType;
	}

	public void setUtxoType(int utxoType) {
		this.utxoType = utxoType;
	}

	/**
	 * The address of this output, can be the empty string if none was provided at
	 * construction time or was deserialized
	 */
	public String getAddress() {
		return address;
	}

	public int getLockType() {
		return lockType;
	}

	public void setLockType(int lockType) {
		this.lockType = lockType;
	}

	public void setValue(BigInteger value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "MUTXO [p_address=" + p_address + ", hash=" + hash + ", value=" + value + ", height=" + height
				+ ", utxoType=" + utxoType + ", address=" + address + ", utxoID=" + utxoID + ", txid=" + txid
				+ ", lockTime=" + lockTime + ", isConfirm=" + isConfirm + ", encrypt_type=" + encrypt_type
				+ ", use_transaction_hash=" + use_transaction_hash + ", lockType=" + lockType + "]";
	}

}
