package io.moaplatform.blockchain.data;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.params.MainNetParams;

import io.moaplatform.blockchain.MConfig;

public class MTransaction extends Transaction {

	public MTransaction() {
		super(MainNetParams.fromID(MConfig.NET_PARAM.MOA_TEST_ID));
	}
	
	public MTransaction(NetworkParameters params) {
		super(params);
	}
	
}
