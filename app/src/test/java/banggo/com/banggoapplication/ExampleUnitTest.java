package banggo.com.banggoapplication;

import com.blankj.utilcode.util.EncryptUtils;

import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        System.out.println(new String(EncryptUtils.encryptAES("code12".getBytes(),
                "FAD4qYQXRB7Bx39D".getBytes(), "DES/CBC/PKCS5Padding", null)));
    }
}