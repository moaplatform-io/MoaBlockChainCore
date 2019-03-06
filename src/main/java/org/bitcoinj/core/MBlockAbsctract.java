package org.bitcoinj.core;

import org.bitcoinj.core.Block;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.ProtocolException;

public abstract class MBlockAbsctract extends Block{

	public MBlockAbsctract(NetworkParameters params, long version) throws ProtocolException {
		super(params, version);
	}

}
