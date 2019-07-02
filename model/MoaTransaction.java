package m.common.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.json.simple.JSONObject;

import m.common.ConstTransaction;
import m.common.crypto.Base58;
import m.common.util.sign.MoaSigner;

public class MoaTransaction implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger( MoaTransaction.class);

	protected BigInteger fee;
	protected int type;
	protected byte[] signature;
	protected byte[] reward_signature;
	protected long timestamp;
	private boolean isConfirmed;
	private int confirmCount;
	private long lockTime;

	private int encrypt_type;
	private List<MUTXO> inputUtxoReward;

	private List<MUTXO> inputUtxo;
	private List<MUTXO> outputTransactions;
	private List<MUTXO> outputTransactionsReward;

	private boolean isInBlock;
	private BigInteger amount;

	private BigInteger rewardAmount;

	private String fromAddress;
	private String toAddress;
	private int lockType;
	private String targetTxid;
	private String reward_hash;

	public String getReward_hash() {
		return reward_hash;
	}

	public void setReward_hash(String reward_hash) {
		this.reward_hash = reward_hash;
	}

	/**
	 * 주소검증및 트랜젝션 검증 방식에 대한 정의
	 * 
	 * @author moa
	 *
	 */
	public static final class ENCRYPT_TYPE {
		public static int nomal = 0;
		public static int moa = 2;
	}

	public static final class TRANSACTION_TYPE {
		public final static int TRADE = 1;
		public final static int CODE = 2;
		public final static int STORAGE = 3;
		public final static int MINT = 4;
		public final static int SHARE_AUTHORITY = 10;
		public final static int SHARE_AUTHORITY_CANCLE = 11;
		public final static int CANCLE = 7;

	}

	public List<MUTXO> getInputUtxoReward() {
		return inputUtxoReward;
	}

	public void setInputUtxoReward(List<MUTXO> inputUtxoReward) {
		this.inputUtxoReward = inputUtxoReward;
	}

	public List<MUTXO> getOutputTransactionsReward() {
		return outputTransactionsReward;
	}

	public void setOutputTransactionsReward(List<MUTXO> outputTransactionsReward) {
		this.outputTransactionsReward = outputTransactionsReward;
	}

	/**
	 * 
	 *
	 */
	public static final class LOCK_TYPE {
		public final static int NO_WAIT = 0;
		public final static int WAIT_AND_CONFIRM = 1;
		public final static int WAIT_AND_CANCLE = 2;

		/**
		 * 멀티 사인
		 */
		public static final int MULTISIGN = 100;

		/**
		 * 리워드 싸인이 필요
		 */
		public static final int MULTISIGN_REWORD = 200;

		/**
		 * 리워드 트랜젝션을 최종적으로 락타임 안에 트랜젝션 양 자가 취소할 수 있음
		 */
		public static final int MULTISIGN_REWORD_CANCLE = 203;

		/**
		 * 리워드 트랜젝션과 리워드트랜젝션 검증하는 트랜젝션 소유주의 사인이 필요함
		 */
		public static final int MULTISIGN_REWORD_SIGN = 201;

	}

	public long getLockTime() {
		return lockTime;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setLockTime(long lockTime) {
		this.lockTime = lockTime;
	}

	public int getEncrypt_type() {
		return encrypt_type;
	}

	public void setEncrypt_type(int encrypt_type) {
		this.encrypt_type = encrypt_type;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public String getTargetTxid() {
		return targetTxid;
	}

	public void setTargetTxid(String targetTxid) {
		this.targetTxid = targetTxid;
	}

	public byte[] getBody() {
		byte[] arrayBody = null;
		try {
			this.signature = null;
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
			objectOutputStream.writeObject(this);
			arrayBody = arrayOutputStream.toByteArray();
		} catch (Exception e) {
		}
		return arrayBody;
	}

	static public MoaTransaction ByteToTrnansaction(byte[] body) {
		MoaTransaction moaTransaction = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(body);
			ObjectInputStream inputStream = new ObjectInputStream(bis);
			moaTransaction = (MoaTransaction) inputStream.readObject();
		} catch (Exception e) {
		}
		return moaTransaction;
	}

	public byte[] getIncludeSignBody() {
		byte[] arrayBody = null;
		try {
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
			objectOutputStream.writeObject(this);
			arrayBody = arrayOutputStream.toByteArray();
		} catch (Exception e) {
		}
		return arrayBody;
	}

	public List<MUTXO> getInputUtxo() {
		return inputUtxo;
	}

	public void setInputUtxo(List<MUTXO> inputUtxo) {
		this.inputUtxo = inputUtxo;
	}

	public boolean isInBlock() {
		return isInBlock;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public void setInBlock(boolean isInBlock) {
		this.isInBlock = isInBlock;
	}

	private String hash;

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public MoaTransaction() {

	}

	public List<MUTXO> getOutputTransactions() {
		return this.outputTransactions;
	}

	public void setOutputTransactions(List<MUTXO> outputTransactions) {
		this.outputTransactions = outputTransactions;
	}

	public String getHash() {
		return hash;
	}

	public int getLockType() {
		return lockType;
	}

	public void setLockType(int lockType) {
		this.lockType = lockType;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * 
	 * @param type
	 * @param fee
	 * @param timestamp
	 * @param reference
	 * @param signature
	 * @param input
	 * @param output
	 */
	public MoaTransaction(String hash, int type, BigInteger fee, long timestamp, byte[] signature, List<MUTXO> output) {
		this.hash = hash;
		this.fee = fee;
		this.type = type;
		this.signature = signature;
		this.timestamp = timestamp;
		this.outputTransactions = output;
	}

	// GETTERS/SETTERS

	public int getType() {
		return this.type;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public long getDeadline() {
		// 24HOUR DEADLINE TO INCLUDE TRANSACTION IN BLOCK
		return this.timestamp + (1000 * 60 * 60 * 24);
	}

	public BigInteger getFee() {
		return this.fee;
	}

	public byte[] getSignature() {
		return this.signature;
	}

	public BigInteger feePerByte() {
		return this.fee.divide(new BigInteger(this.getDataLength() + ""));
	}

	private char[] getDataLength() {
		return new char[10];
	}

	public boolean hasMinimumFee() {
		return this.fee.compareTo(ConstTransaction.MINIMUM_FEE) >= 0;
	}

	public boolean hasMinimumFeePerByte() {
		return true;
	}

	@SuppressWarnings("unchecked")
	protected JSONObject getJsonBase() {
		JSONObject transaction = new JSONObject();

		transaction.put("type", this.type);
		transaction.put("fee", this.fee);
		transaction.put("timestamp", this.timestamp);
		transaction.put("signature", Base58.encode(this.signature));
		transaction.put("confirmations", this.getConfirmations());

		return transaction;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof MoaTransaction) {
			MoaTransaction transaction = (MoaTransaction) object;

			return Arrays.equals(this.getSignature(), transaction.getSignature());
		}

		return false;
	}

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public int getConfirmations() {
		return confirmCount;
	}

	public static Map<String, Map<Long, BigInteger>> subAssetAmount(Map<String, Map<Long, BigInteger>> allAssetAmount,
			String address, Long assetKey, BigInteger amount) {
		return addAssetAmount(allAssetAmount, address, assetKey, BigInteger.ZERO.subtract(amount));
	}

	public static Map<String, Map<Long, BigInteger>> addAssetAmount(Map<String, Map<Long, BigInteger>> allAssetAmount,
			String address, Long assetKey, BigInteger amount) {
		Map<String, Map<Long, BigInteger>> newAllAssetAmount;
		if (allAssetAmount != null) {
			newAllAssetAmount = new LinkedHashMap<String, Map<Long, BigInteger>>(allAssetAmount);
		} else {
			newAllAssetAmount = new LinkedHashMap<String, Map<Long, BigInteger>>();
		}

		Map<Long, BigInteger> newAssetAmountOfAddress;

		if (!newAllAssetAmount.containsKey(address)) {
			newAssetAmountOfAddress = new LinkedHashMap<Long, BigInteger>();
			newAssetAmountOfAddress.put(assetKey, amount);

			newAllAssetAmount.put(address, newAssetAmountOfAddress);
		} else {
			if (!newAllAssetAmount.get(address).containsKey(assetKey)) {
				newAssetAmountOfAddress = new LinkedHashMap<Long, BigInteger>(newAllAssetAmount.get(address));
				newAssetAmountOfAddress.put(assetKey, amount);

				newAllAssetAmount.put(address, newAssetAmountOfAddress);
			} else {
				newAssetAmountOfAddress = new LinkedHashMap<Long, BigInteger>(newAllAssetAmount.get(address));
				BigInteger newAmount = newAllAssetAmount.get(address).get(assetKey).add(amount);
				newAssetAmountOfAddress.put(assetKey, newAmount);

				newAllAssetAmount.put(address, newAssetAmountOfAddress);
			}
		}

		return newAllAssetAmount;
	}

	public BigInteger getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(BigInteger rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public static MoaTransaction AddReward(MoaTransaction moaTransaction, BigInteger rewardAmount,
			List<MUTXO> utxoList) {
		moaTransaction.setRewardAmount(rewardAmount);
		moaTransaction.setInputUtxoReward(utxoList);
		MoaSigner.getRewardHash(moaTransaction);
		return moaTransaction;
	}

	public byte[] getReward_signature() {
		return reward_signature;
	}

	public void setReward_signature(byte[] reward_signature) {
		this.reward_signature = reward_signature;
	}

	@Override
	public String toString() {
		return "MoaTransaction [fee=" + fee + ", type=" + type + ", signature=" + Arrays.toString(signature)
				+ ", reward_signature=" + Arrays.toString(reward_signature) + ", timestamp=" + timestamp
				+ ", isConfirmed=" + isConfirmed + ", confirmCount=" + confirmCount + ", lockTime=" + lockTime
				+ ", encrypt_type=" + encrypt_type + ", inputUtxo=" + inputUtxo + ", outputTransactions="
				+ outputTransactions + ", isInBlock=" + isInBlock + ", amount=" + amount + ", rewardAmount="
				+ rewardAmount + ", fromAddress=" + fromAddress + ", toAddress=" + toAddress + ", lockType=" + lockType
				+ ", hash=" + hash + "]";
	}

}
