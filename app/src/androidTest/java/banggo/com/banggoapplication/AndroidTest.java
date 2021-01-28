package banggo.com.banggoapplication;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class AndroidTest {

    @Test
    private Context getContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Test
    public void useAppContext() {
        assertEquals("com.example.mvpdb", getContext().getPackageName());
    }

}