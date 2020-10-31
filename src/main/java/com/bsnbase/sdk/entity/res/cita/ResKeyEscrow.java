package com.bsnbase.sdk.entity.res.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResKeyEscrow implements IBody{
	/**
	 * 交易hash
	 */
    String txId;
	/**
	 * 交易状态
	 */
	String status;
	/**
	 * 查询数据
	 */
	Object data;


	@Override
	public String getEncryptionValue() {
		String str=(this.txId == null?"":this.txId)+(this.status == null?"":this.status)+(this.data == null?"":this.data);
		return str;

	}
}
