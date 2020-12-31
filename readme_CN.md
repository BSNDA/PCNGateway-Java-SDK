# SDK调用参考


## Java语言SDK

## 框架及秘钥支持

> JAVA语言SDK目前支持官网所有的框架和秘钥组合应用的调用

具体如下：
<escape>
<table>
<tr>
<th rowspan=2>框架</th><th colspan=3>秘钥上传</th><th colspan=3>秘钥托管</th>
</tr>
<tr>
<th>secp256r1</th><th>secp256k1</th><th>SM2</th><th>secp256r1</th><th>secp256k1</th><th>SM2</th>
</tr>
<tr>
<td>Fabric</td><td>支持</td><td></td><td>支持</td><td>支持</td><td></td><td>支持</td>
</tr>
<tr>
<td>FISCO-BCOS</td><td></td><td>支持</td><td>支持</td><td></td><td>支持</td><td>支持</td>
</tr>
<tr>
<td>XuperChain</td><td></td><td></td><td>支持</td><td></td><td></td><td>支持</td>
</tr>
<tr>
<td>CITA</td><td></td><td></td><td></td><td></td><td></td><td>支持</td>
</tr>
</table>
</escape>

* fabric框架应用使用secp256r1、SM2 秘钥的秘钥托管和秘钥上传两种模式;
* FISCO-BCOS框架应用使用secp256k1、SM2 秘钥的秘钥托管和秘钥上传两种模式;
* XuperChain框架应用使用SM2 秘钥的秘钥托管和秘钥上传两种模式;
* CITA框架应用使用SM2 秘钥的秘钥托管模式;


### 1. 调用前准备

#### 应用参数
> 应用参数是用户在参与应用成功之后在应用详情页面获取，或者由本地设置的一些参数，具体包含以下参数
 * __节点网关接口地址：__ 参与的城市节点的节点网关的调用地址
 * __用户编号：__ 用户的编号
 * __应用编号：__ 参与的应用的编号
 * __应用公钥：__ 用户参与成功之后下载的应用公钥
 * __应用私钥：__ 托管类型应用再参与成功之后由BSN生成的应用公钥，非托管应用为在参与应用时上传的公钥所对应的私钥
 * __Https证书：__ 调用https网关接口时使用的https证书


### 2. 准备调用
#### 项目引用

将V1.2.0版本中bsn-sdk-java-jar-with-dependencies.jar引用到项目中

#### 导入sdk包
Fabric 需要引入下面的包
```
import com.bsnbase.sdk.client.fabric.FabricClient
import com.bsnbase.sdk.entity.config.Config
```
FISCO-BCOS 需要引入下面的包
```
import com.bsnbase.sdk.client.fiscobcos.FiscobcosClient
import com.bsnbase.sdk.entity.config.Config
```
XuperChain 需要引入下面的包
```
import com.bsnbase.sdk.client.xuperChain.XuperClient
import com.bsnbase.sdk.entity.config.Config
```
#### 初始化config
可以初始化一个存储所有配置的对象，这些具体的配置信息应当由调用者根据各自的项目配置或者读取之后，在调用时传入，  
在config的`Init`方法中实现了获取一个App基础信息的操作，该操作请不要频繁的调用，该接口将占用您的TPS和流量，可以在项目使用一个静态对象存储`config`在需要时使用。  
其中，应用私钥、节点网关公钥为pem中具体内容，
com.bsnbase.sdk.util.common.Common提供根据路径获取内容方法，
Common.readLocalFile参数为pem存储目录的绝对路径，
Common.readFile参数为pem存储目录的相对路径，
或者直接填入pem内容。
证书存储目录是磁盘的绝对路径。可以通过修改`util.keystore`中的实现修改子用户证书的存储方式。
```
	api:="" //节点网关地址
	userCode:="" //用户编号
	appCode :="" //应用编号
	puk :="" //应用应用内容
	prk :="" //应用私钥内容
	mspDir:="" //证书存储目录
```
#### 初始化Config
使用已经生成的配置对象，调用以下代码可以创建一个Config对象，用来调用节点网关
```
	Config config = new Config();
	config.setAppCode(appCode );
	config.setUserCode(userCode);
	config.setPrk(prkStr)
	config.setApi(api);
	config.setPuk(pukStr);
	config.setMspDir(cert);
	config.initConfig(config);
```

####   调用接口
每一个网关接口已经封装了请求和响应的参数对象，只需要赋值就可直接调用，方法内已经实现了签名和验签的操作。  
以下为注册子用户的调用操作，其他类似。
```	
//初始化config。
public void initConfig() throws IOException {
    Config config = new Config();
    config.setAppCode("app0001202004161020152918451");
    config.setUserCode("USER0001202004151958010871292");
    config.setApi("http://192.168.1.43:17502");
    config.setPrk(Common.readFile("cert/private_key.pem"));
    config.setPuk(Common.readFile("cert/public_Key.pem"));
    config.setMspDir("D:/test");
    config.initConfig(config);
}
//调用用户注册接口
public void userRegister() {
    try {
        initConfig(); //这里为示例，实际使用中，值需在程序生命周期内调用一次即可
    } catch (IOException e) {
        e.printStackTrace();
        return ;
    }
    ReqUserRegister register = new ReqUserRegister();
    register.setName("test19");
    register.setSecret("123456");
    try {
        UserService.userRegister(register);
    } catch(GlobalException g) {
        g.printStackTrace();
    }catch (IOException e) {
        e.printStackTrace();
        return;
    }
}
```

### 3.一些其他说明

#### 非托管应用的用户身份证书的说明
由于`Fabric`框架的非托管的应用在调用网关进行交易的时候所需要的用户证书需要用户自己生成，其流程是：注册用户->登记用户证书 。在登记用户证书的操作中，会由本地生成一对秘钥，然后通过秘钥导出证书的CSR文件（证书申请文件），调用用户证书
登记接口获取一个有效的证书，使用该证书才能在通过托管应用交易处理接口中正常的发起交易。
需要注意的是在CSR文件中设置CN时，并不直接是注册的Name，而是由Name和AppCode拼接的名称，格式为`Name@AppCode` 。
该操作是在`KeyEscrowFabric`的`keyEscrowNoRegister`方法中实现的。
而`FISCO-BCOS`框架的非托管应用在进行交易时只需要在本地生成一对符合框架算法的密钥对即可，无需其他操作。

__证书的存储__ 是通过 `util.keystore`的方法中实现的，该方法只存储本地文件形式的证书，如果需要其
他形式的证书存储方式。是需要实现具体的接口即可，详细请参考具体的代码。

#### 关于加密
为方便在进行数据交易的上链操作中对数据进行加密解密，SDK中实现了对称加密`AES`，`DES`和一种非对称加密`SM2`算法  
其中对称加密为`AES`具体调用如下
```

		String prk="";//私钥
        String content="";//加密内容
        System.out.println("加密后的密文为："+AESEncode(encodeRules,content));
        System.out.println("解密后的明文为："+AESDncode(encodeRules,AESEncode(encodeRules,content)));
    /*
     * 加密
     * 1.构造密钥生成器KeyGenerator
     * 2.根据ecnodeRules规则初始化密钥生成器
     * 3.产生密钥
     * 4.创建和初始化密码器
     * 5.内容加密
     * 6.返回字符串
     */
    public static String AESEncode(String encodeRules,String content){
        try {
            
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
           
            SecretKey original_key=keygen.generateKey();
            byte [] raw=original_key.getEncoded();
           
            SecretKey key=new SecretKeySpec(raw, "AES");
            
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte [] byte_encode=content.getBytes("utf-8");
            byte [] byte_AES=cipher.doFinal(byte_encode);
            String AES_encode=new String(new BASE64Encoder().encode(byte_AES));
            return AES_encode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * 解密
     * 解密过程：
     * 1.同加密1-4步
     * 2.将加密后的字符串反纺成byte[]数组
     * 3.将加密内容解密
     */
    public static String AESDncode(String encodeRules,String content){
        try {
            KeyGenerator keygen=KeyGenerator.getInstance("AES");
            keygen.init(128, new SecureRandom(encodeRules.getBytes()));
            SecretKey original_key=keygen.generateKey();
            byte [] raw=original_key.getEncoded();
            SecretKey key=new SecretKeySpec(raw, "AES");
            Cipher cipher=Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte [] byte_content= new BASE64Decoder().decodeBuffer(content);
            /*
             * 解密
             */
            byte [] byte_decode=cipher.doFinal(byte_content);
            String AES_decode=new String(byte_decode,"utf-8");
            return AES_decode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

```
非对称加密`SM2`，具体如下,在该方法中同时实现了SM2的签名和验签
>非对称加密中由公钥加密，私钥进行解密
```
	puk := ``//公钥
	prik := ``//私钥
	String src = "加密字符串";
    System.out.println("原文UTF-8转hex:" + Util.byteToHex(src.getBytes()));
    String SM2Enc = SM2Enc(puk, src);//加密
    String SM2Dec = SM2Dec(prik, SM2Enc);//私钥解密
	
```


#### 关于秘钥生成
在BSN中，`fabric`框架的密钥格式为`ECDSA`的`secp256r1`曲线，而`fisco-bcos`框架的密钥格式为`SM2`
在用户参与非托管应用时需要生成对应格式的密钥并上传。  
下面介绍这两种密钥的生成，秘钥的生成是使用`openssl`生成的，其中`SM2`秘钥的生成需要`openssl`的`1.1.1`及以上版本
> 注：以下命令是在linux环境下执行的
##### 1. ECDSA(secp256r1)的密钥生成
- 生成私钥
```
openssl ecparam -name prime256v1 -genkey -out key.pem
```
- 导出公钥
```
openssl ec -in key.pem -pubout -out pub.pem
```
- 导出pkcs8格式私钥
> 由于部分语言中使用pkcs8格式的密钥比较方便，可以使用下面的命令导出pkcs8格式私钥  
> 在本sdk中使用的私钥即为pkcs8格式
```
openssl pkcs8 -topk8 -inform PEM -in key.pem -outform PEM -nocrypt -out key_pkcs8.pem
```
通过以上命令可以生成三个文件  
__`key.pem`__ :私钥  
__`pub.pem`__ :公钥  
__`key_pkcs8.pem`__ :pkcs8格式私钥

##### 2. ECDSA(secp256k1)的密钥生成
- 生成私钥
```
openssl ecparam -name secp256k1 -genkey -out key.pem
```
- 导出公钥
```
openssl ec -in key.pem -pubout -out pub.pem
```
- 导出pkcs8格式私钥
> 由于部分语言中使用pkcs8格式的密钥比较方便，可以使用下面的命令导出pkcs8格式私钥
> 在本sdk中使用的私钥即为pkcs8格式
```
openssl pkcs8 -topk8 -inform PEM -in key.pem -outform PEM -nocrypt -out key_pkcs8.pem
```
通过以上命令可以生成三个文件
__`key.pem`__ :私钥
__`pub.pem`__ :公钥
__`key_pkcs8.pem`__ :pkcs8格式私钥

##### 3.`SM2`格式秘钥生成  
首先需要检查`openssl`的版本是否支持`SM2`格式秘钥生成，可以使用下面的命令
```
openssl ecparam -list_curves | grep SM2
```
如果输出以下内容，则表示支持，
```
SM2       : SM2 curve over a 256 bit prime field
```
否则需要去官网下载`1.1.1`或者以上版本，
这是使用的为`1.1.1d`版本，  
官网下载地址：[https://www.openssl.org/source/openssl-1.1.1d.tar.gz](https://www.openssl.org/source/openssl-1.1.1d.tar.gz])  

- 生成私钥
```
openssl ecparam -genkey -name SM2 -out sm2PriKey.pem
```
- 导出公钥
```
openssl ec -in sm2PriKey.pem -pubout -out sm2PubKey.pem
```
- 导出pkcs8格式私钥
> 由于部分语言中使用pkcs8格式的密钥比较方便，可以使用下面的命令导出pkcs8格式私钥  
> 在本sdk中使用的私钥即为pkcs8格式
```
openssl pkcs8 -topk8 -inform PEM -in sm2PriKey.pem -outform pem -nocrypt -out sm2PriKeyPkcs8.pem
```
通过以上命令可以生成三个文件  
__`sm2PriKey.pem`__ :私钥  
__`sm2PubKey.pem`__ :公钥  
__`sm2PriKeyPkcs8.pem`__ :pkcs8格式私钥




























