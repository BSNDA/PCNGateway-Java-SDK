package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Description： 公钥上传，请求体
 * Author: wxq
 * Date: Created in 2020/12/10 18:00
 * Version: 0.0.1
 * Modified By:
 */
@Data
public class ReqKeyUploadBody implements IBody {
    //合约名称
    private String contractName;
    //交易签名数据
    private String transData;

    @Override
    public String getEncryptionValue() {
        String str = (this.contractName == null ? "" : this.contractName);
        str += (this.transData == null ? "" : this.transData);
        return str;
    }
}
