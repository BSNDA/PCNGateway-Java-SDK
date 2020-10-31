package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * 密钥托管模式请求参数
 */
@Data
public class ReqKeyEscrow implements IBody  {
	/**
	 * 用户ID
	 */
    String userId;
	/**
	 * 合约名称
	 */
    String contractName;
	/**
	 * 方法名称
	 */
    String funcName;
	/**
	 * 方法参数
	 */
    String funcParam;
	@Override
	public String getEncryptionValue() {

		String str = (this.userId == null ? "" : this.userId);
		str += (this.contractName == null ? "" : this.contractName);
		str += (this.funcName == null ? "" : this.funcName);
		str += (this.funcParam == null ? "" : this.funcParam);
		return str;
	}
}
