package com.bsnbase.sdk.entity.req.cita;

import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

/**
 * Description： 秘钥上传
 * Author: wxq
 * Date: Created in 2020/12/9 16:48
 * Version: 0.0.1
 * Modified By:
 */
@Data
public class ReqKeyUpload implements IBody {

    /**
     * 合约名称
     */
    String contractName;
    /**
     * 合约地址
     */
    String contractAddr;
    /**
     * 方法名称
     */
    String funcName;
    /**
     * 方法参数
     */
    String[] args;


    @Override
    public String getEncryptionValue() {
        String str = (this.contractName == null ? "" : this.contractName);
        str += (this.contractAddr == null ? "" : this.contractAddr);
        str += (this.funcName == null ? "" : this.funcName);
        for (int i = 0; i < this.args.length; i++) {
            str += this.args[i];
        }
        return str;
    }
}
