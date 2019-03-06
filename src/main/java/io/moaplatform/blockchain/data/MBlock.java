package io.moaplatform.blockchain.data;

import org.bitcoinj.core.MBlockAbsctract;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ProtocolException;

public class MBlock extends MBlockAbsctract{

	public MBlock(NetworkParameters params, long version) throws ProtocolException {
		super(params, version);
	}


}
