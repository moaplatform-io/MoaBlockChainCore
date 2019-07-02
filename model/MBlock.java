package m.common.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.common.primitives.Bytes;

public class MBlock implements Serializable {

	public MBlock() {

	}

	private static final Logger LOGGER = LoggerFactory.getLogger( MBlock.class);
	public static final int MAX_BLOCK_BYTES = 1048576;
	public static final int VERSION_LENGTH = 4;
	public static final int REFERENCE_LENGTH = 128;
	public static final int TIMESTAMP_LENGTH = 8;
	public static final int GENERATING_BALANCE_LENGTH = 8;
	public static final int GENERATOR_LENGTH = 32;
	public static final int GENERATOR_SIGNATURE_LENGTH = 64;
	private static final int TRANSACTIONS_SIGNATURE_LENGTH = 64;
	private static final int TRANSACTIONS_COUNT_LENGTH = 4;
	private static final int TRANSACTION_SIZE_LENGTH = 4;
	public static final int AT_BYTES_LENGTH = 4;
	private static final int BASE_LENGTH = VERSION_LENGTH + REFERENCE_LENGTH + TIMESTAMP_LENGTH
			+ GENERATING_BALANCE_LENGTH + GENERATOR_LENGTH + TRANSACTIONS_SIGNATURE_LENGTH + GENERATOR_SIGNATURE_LENGTH
			+ TRANSACTIONS_COUNT_LENGTH;
	private static final int AT_FEES_LENGTH = 8;
	private static final int AT_LENGTH = AT_FEES_LENGTH + AT_BYTES_LENGTH;
	public static final int MAX_TRANSACTION_BYTES = MAX_BLOCK_BYTES - BASE_LENGTH;

	protected int version;
	protected long timestamp;
	protected long generatingBalance;
	protected byte[] generatorSignature;

	protected String mining_address;

	protected List<String> parentsHash;

	protected int height;

	protected List<String> transactionList;

	public void setHeight(int height) {
		this.height = height;
	}

	private int transactionCount;

	protected byte[] atBytes;
	protected Long atFees;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public List<String> getParentsHash() {
		return parentsHash;
	}

	public void setParentsHash(List<String> parentsHash) {
		this.parentsHash = parentsHash;
	}

	public String getMining_address() {
		return mining_address;
	}

	public void setMining_address(String mining_address) {
		this.mining_address = mining_address;
	}

	public List<String> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<String> transactionList) {
		this.transactionList = transactionList;
	}

	// VERSION 2 AND 3 BLOCKS, WITH AT AND MESSAGE
	public MBlock(int version, long timestamp, long generatingBalance
//			, PublicKeyAccount generator
			, byte[] generatorSignature, byte[] atBytes, long atFees) {
		this.version = version;
		this.timestamp = timestamp;
		this.generatingBalance = generatingBalance;
		this.generatorSignature = generatorSignature;

		this.transactionCount = 0;

		this.atBytes = atBytes;
		this.atFees = atFees;
	}

	private String hash;

	// VERSION 1 BLOCKS
	public MBlock(String hash, int version, long timestamp, long generatingBalance, byte[] generatorSignature) {
		this(version, timestamp, generatingBalance, generatorSignature, new byte[0], 0);
		this.hash = hash;
	}

	// GETTERS/SETTERS

	public int getVersion() {
		return version;
	}

	public byte[] getGeneratorSignature() {
		return this.generatorSignature;
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public long getGeneratingBalance() {
		return this.generatingBalance;
	}

	public BigInteger getATfee() {
		return new BigInteger(atFees + "");
	}

	public void setTransactionData(int transactionCount, List<String> transactionList) {
		this.transactionCount = transactionCount;
		this.transactionList = transactionList;
	}

	public int getTransactionCount() {
		return this.transactionCount;
	}

	public byte[] getBlockATs() {
		return this.atBytes;
	}

	@SuppressWarnings("unchecked")
	public JSONObject toJson() {
		JSONObject block = new JSONObject();

		block.put("version", this.version);
		block.put("timestamp", this.timestamp);
		block.put("generatingBalance", this.generatingBalance);

		// CREATE TRANSACTIONS
		JSONArray transactionsArray = new JSONArray();

		// ADD TRANSACTIONS TO BLOCK
		block.put("transactions", transactionsArray);

		// ADD AT BYTES
		if (atBytes != null) {
			block.put("atFees", this.atFees);
		}

		// RETURN
		return block;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}

	public int getDataLength() {

		int length = BASE_LENGTH;

		if (this.version >= 2) {
			length += AT_LENGTH;
			if (this.atBytes != null) {
				length += atBytes.length;
			}
		}

		return length;
	}

	public byte[] getProofHash() {
		return null;
	}

	// VALIDATE

	public boolean isValid() {
		return true;
	}

	@Override
	public boolean equals(Object otherObject) {
		if (otherObject instanceof MBlock) {
			MBlock otherBlock = (MBlock) otherObject;

		}

		return false;
	}

	public int getHeight() {
		return height;
	}

	public byte[] getSignature() {
		return generatorSignature;
	}

	@Override
	public String toString() {
		return "CWBlock [version=" + version + ", timestamp=" + timestamp + ", generatingBalance=" + generatingBalance
				+ ", generatorSignature=" + Arrays.toString(generatorSignature) + ", parentsHash=" + parentsHash
				+ ", height=" + height + ", transactionList=" + transactionList + ", transactionCount="
				+ transactionCount + ", atBytes=" + Arrays.toString(atBytes) + ", atFees=" + atFees + ", hash=" + hash
				+ "]";
	}

}
