package com.bsnbase.sdk.entity.res.fiscobcos;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;
import org.fisco.bcos.web3j.abi.datatypes.Int;

import java.math.BigInteger;

@Data
public class ResGetBlockInformation implements IBody{
	String blockHash;
	int blockNumber;
	String parentBlockHash;
	int blockSize;
	BigInteger blockTime;
	String author;
	ResGetBlockTranaction[] transactions;

	 public ResGetBlockInformation(){
	 	this.transactions = new  ResGetBlockTranaction[]{};
	 }
    
	@Override
	public String getEncryptionValue() {
		String str = "";

		str +=this.blockHash==null?"":this.blockHash;
		str +=this.blockNumber ;
		str +=this.parentBlockHash==null?"":this.parentBlockHash;
		str +=this.blockSize;
		str +=this.blockTime ;
		str +=this.author==null?"":this.author;
		for (int i=0;i<this.transactions.length;i++) {
			ResGetBlockTranaction trans = this.transactions[i];
			str += (trans.txId == null ? "" : trans.txId);
			str += (trans.blockHash == null ? "" : trans.blockHash);
			str +=  trans.blockNumber;
			str +=  trans.gasUsed;
			str += (trans.from == null ? "" : trans.from);
			str += (trans.to == null ? "" : trans.to);
			str +=  trans.value;
			str += (trans.input == null ? "" : trans.input);
		}

		return str;
	}
}
