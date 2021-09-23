import org.w3c.dom.Document;
import java.security.KeyStore;
import java.security.PublicKey;

public class ValidateSignature {

  //KEY STORE
  static String keyStoreName     = "/ClientKeyStore.jks";
  static String keyStorePassword = "mypassword";
  static String keyStoreType     = "JKS";
  static String keyAlias         = "clientkeys1";

  //XML FILE
  static String fileXMLInput1    = "/PersonSigned.xml";
  static String fileXMLInput2    = "/PersonSignedWithKeyInfo.xml";

  //================================================================================
  // MAIN
  //================================================================================
  public static void main(String[] args) throws Exception {

    //GET PUBLIC KEY
    KeyStore.PrivateKeyEntry keyPair   = UtilKeys.getKeyPair(keyStoreName, keyStorePassword, keyStoreType, keyAlias);
    PublicKey                publicKey = keyPair.getCertificate().getPublicKey();

    //VALIDATE SIGNATURE
    Document                 document  = UtilXML.fileToDocument(fileXMLInput2);
    boolean                  valid     = UtilSignature.validateSignatureUsingKey(document, publicKey);

    //DISPLAY RESULT
    System.out.println(valid);

  }

}
