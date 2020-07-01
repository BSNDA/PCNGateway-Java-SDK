package com.bsnbase.sdk.entity.req;

import lombok.Data;

import java.util.*;

import com.bsnbase.sdk.entity.base.IBody;

@Data
public class ReqKeyEscrow implements IBody  {
    String userName;
    String nonce;
    String chainCode;
    String funcName;
    String[]  args;
    Map<String,String> transientData;
	@Override
	public String getEncryptionValue() {

		String str = (this.userName == null? "":this.userName);
		str +=(this.nonce == null? "":this.nonce);
		str +=(this.chainCode == null? "":this.chainCode);
		str +=(this.funcName == null? "":this.funcName);

		for (int i =0 ;i<this.args.length;i++){
			str +=this.args[i];
		}

		if (this.transientData !=null) {
			for (Map.Entry<String, String> entry : this.transientData.entrySet()) {
				String mapKey = entry.getKey();
				String mapValue = entry.getValue();
				str = str +mapKey+mapValue;
			}
		}

		return str;
	}
}
