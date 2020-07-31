package com.bsnbase.sdk.entity.res.xuperChain;

import com.bsnbase.sdk.entity.base.IBody;
import com.bsnbase.sdk.entity.res.fiscobcos.BlockContractEvent;
import com.bsnbase.sdk.entity.res.fiscobcos.BlockEvent;
import lombok.Data;
import org.apache.commons.lang3.StringEscapeUtils;

@Data
public class ResGetTransaction implements IBody{
    String txId;
    String blockId;
	String version;
	ContractRequestData[] contractRequests;
	long receivedTimestamp;

	public ResGetTransaction(){
		this.contractRequests =new ContractRequestData[]{};
	}
	@Override
	public String getEncryptionValue() {
		String str= (this.txId == null?"":this.txId)
				+ (this.blockId == null?"":this.blockId)
				+ (this.version== null?"":this.version);
		for (int i = 0; i<this.contractRequests.length; i++) {
			ContractRequestData contractRequestData = this.contractRequests[i];
			str += (contractRequestData.contractName == null ? "" : contractRequestData.contractName);
			str += (contractRequestData.methodName == null ? "" : contractRequestData.methodName);
			str += (contractRequestData.args == null ? "" : contractRequestData.args);
		}
		str+=receivedTimestamp;
		return str;
	}
}
