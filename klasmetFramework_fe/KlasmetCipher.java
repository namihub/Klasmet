/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klasmetFramework_fe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author nami
 */
public class KlasmetCipher {

    private static final String ALGORITHM = "AES";
    private static final String KEY = "c3X0$k31$0D3m$4b";
    private static SecretKeySpec myDesKey = null;
    private static final SecureRandom rnd = new SecureRandom();
    private static final IvParameterSpec iv = new IvParameterSpec(rnd.generateSeed(16));

    public static boolean encrypt(String data, String filePath) {
        try {
            initPublicKey();
            byte[] encryptedData;
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            encryptedData = cipher.doFinal(data.getBytes());
            File outFile = new File(filePath);
            if (outFile.exists()) {
                outFile.delete();
            } else {
                outFile.createNewFile();
            }
            FileOutputStream output = new FileOutputStream(outFile);
            output.write(encryptedData);
            ///jre1.7
            //Path path = Paths.get(FILE_PATH);
            //Files.delete(path);
            //Files.write(path,encryptedData,StandardOpenOption.CREATE_NEW);
            //
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String decrypt(String filePath) {
        try {
            initPublicKey();
            byte[] dectyptedData;
            byte[] encryptedData;
            //read data from file
            File outFile = new File(filePath);
            if (outFile.exists()) {
                FileInputStream input = new FileInputStream(outFile);
                encryptedData = new byte[(int) outFile.length()];
                input.read(encryptedData);
            } else {
                return "";
            }
            //jre1.7
            //Path path = Paths.get(FILE_PATH);
            //encryptedData=Files.readAllBytes(path);
            //decrypt data
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, myDesKey);
            dectyptedData = cipher.doFinal(encryptedData);
            String decoded = new String(dectyptedData, "UTF-8");
            return decoded;
        } catch (Exception e) {
            return "";
        }
    }

    private static SecretKey initPublicKey() {
        try {
            myDesKey = new SecretKeySpec(KEY.getBytes(), "AES");
            return myDesKey;
        } catch (Exception e) {
            //System.out.println("Error in generating Public Key \n " + e.toString());
            return null;
        }
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
