package com.bsnbase.sdk.entity.res.fabric;
import com.bsnbase.sdk.entity.base.IBody;
import lombok.Data;

@Data
public class ResUserInfo implements IBody {
    String appName;
    String appType;
    Integer caType;
    Integer algorithmType;
    String mspId;
    String channelId;
   
	@Override
	public String getEncryptionValue() {
		return (this.appName == null? "":this.appName)
			+(this.appType == null? "":this.appType)
			+(this.caType == null? "":this.caType)
			+(this.algorithmType == null? "":this.algorithmType)
			+(this.mspId == null? "":this.mspId)
			+(this.channelId == null? "":this.channelId);
	}
}
