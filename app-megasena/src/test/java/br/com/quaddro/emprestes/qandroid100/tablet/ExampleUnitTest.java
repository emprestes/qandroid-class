package br.com.quaddro.emprestes.qandroid100.tablet;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    private Context appContext;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void useAppContext() throws Exception {
        Mockito.when(appContext.getPackageName()).thenReturn("br.com.quaddro.emprestes.qandroid100.tablet");
        assertEquals("br.com.quaddro.emprestes.qandroid100.tablet", appContext.getPackageName());
    }
}