import org.w3c.dom.Document;
import xmlutil.XMLUtil;

import java.security.KeyStore;
import java.security.PublicKey;

public class ValidateSignature {

  //KEY STORE
  static String keyStoreName     = "src/main/resources/ClientKeyStore.jks";
  static String keyStorePassword = "mypassword";
  static String keyStoreType     = "JKS";
  static String keyAlias         = "clientkeys1";

  //XML FILE
  static String fileXMLInput1    = "src/main/resources/PersonSigned.xml";
  static String fileXMLInput2    = "src/main/resources/PersonSignedWithKeyInfo.xml";

  //================================================================================
  // MAIN
  //================================================================================
  public static void main(String[] args) throws Exception {

    //GET PUBLIC KEY
    KeyStore.PrivateKeyEntry keyPair   = XMLUtil.getPrivateKeyPair(keyStoreName, keyStorePassword, keyStoreType, keyAlias);
    PublicKey                publicKey = keyPair.getCertificate().getPublicKey();

    //VALIDATE SIGNATURE
    Document                 document  = XMLUtil.readXMLFromFile(fileXMLInput2);
    boolean                  valid     = XMLUtil.validateSignatureUsingKey(document, publicKey);

    //DISPLAY RESULT
    System.out.println(valid);

  }

}
