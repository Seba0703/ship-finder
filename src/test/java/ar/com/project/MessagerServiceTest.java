package ar.com.project;

import ar.com.project.dto.MessageDTO;
import ar.com.project.service.impl.MessageDecoderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {ShipFinderApplication.class})
@ExtendWith(SpringExtension.class)
public class MessagerServiceTest {

    @Autowired
    private MessageDecoderServiceImpl msnServ;

    @Test
    public void GIVEN_UnMensajeVacio_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList(""));
        List<String> mss2 =  new ArrayList<>(Arrays.asList( ""));
        List<String> mss3 =  new ArrayList<>(Arrays.asList(""));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("", pos.getMessage());
    }

    @Test
    public void GIVEN_UnMensajeMuyPequenioCon4Desfase_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList("ESTE"));
        List<String> mss2 =  new ArrayList<>(Arrays.asList("", "", "", "", "ESTE"));
        List<String> mss3 =  new ArrayList<>(Arrays.asList("", "", "", "", ""));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE", pos.getMessage());
    }

    @Test
    public void GIVEN_UnMensajeMuyPequenioCon4DesfaseAlFinal_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList("ESTE"));
        List<String> mss2 =  new ArrayList<>(Arrays.asList("ESTE", "", "", "", ""));
        List<String> mss3 =  new ArrayList<>(Arrays.asList("", "", "", "", ""));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE", pos.getMessage());
    }

    @Test
    public void GIVEN_UnMensajeMuyPequenioSinDesfase_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList("ESTE"));
        List<String> mss2 =  new ArrayList<>(Arrays.asList( "ESTE"));
        List<String> mss3 =  new ArrayList<>(Arrays.asList(""));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE", pos.getMessage());
    }

    @Test
    public void GIVEN_UnMensajePequenioSinDesfase_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList("ESTE", "ES", "UN", "MSS"));
        List<String> mss2 =  new ArrayList<>(Arrays.asList( "ESTE", "", "UN", "MSS"));
        List<String> mss3 =  new ArrayList<>(Arrays.asList("", "ES", "", "MSS"));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE ES UN MSS", pos.getMessage());
    }

    @Test
    public void GIVEN_DesfaseDobleEnElMedioSintresVacios_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList(    "", "ES","UN", "MENSAJE",       "SECRETO"));
        List<String> mss2 =  new ArrayList<>(Arrays.asList("ESTE",   "",  "",        "",            "UN",              "",       ""));
        List<String> mss3 =  new ArrayList<>(Arrays.asList(    "",   "",  "",        "",            "UN",       "MENSAJE", "SECRETO"));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE ES UN MENSAJE SECRETO", pos.getMessage());
    }

    @Test
    public void GIVEN_DesfaseEnElMedioSintresVacios_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList(    "", "ES","UN", "MENSAJE",       "SECRETO"));
        List<String> mss2 =  new ArrayList<>(Arrays.asList("ESTE",   "",  "",      "UN",              "",       ""));
        List<String> mss3 =  new ArrayList<>(Arrays.asList(    "",   "",  "",      "UN",       "MENSAJE", "SECRETO"));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE ES UN MENSAJE SECRETO", pos.getMessage());
    }

    @Test
    public void GIVEN_DesfaseAlPrincipio_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList("",   "ES",    "",        "",       ""));
        List<String> mss2 =  new ArrayList<>(Arrays.asList("", "ESTE",   "",  "UN",        "",       ""));
        List<String> mss3 =  new ArrayList<>(Arrays.asList("",     "",   "",  "UN", "MENSAJE", "SECRETO"));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE ES UN MENSAJE SECRETO", pos.getMessage());
    }

    @Test
    public void GIVEN_4DesfaseAlPrincipio_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList("", "ES",    "", "",     ""));
        List<String> mss2 =  new ArrayList<>(Arrays.asList("",   "",    "", "", "ESTE",   "",  "UN",        "",       ""));
        List<String> mss3 =  new ArrayList<>(Arrays.asList("",   "",    "", "",     "",   "",  "UN", "MENSAJE", "SECRETO"));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE ES UN MENSAJE SECRETO", pos.getMessage());
    }

    @Test
    public void GIVEN_4DesfaseAlFinal_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList("",     "ES",    "",        "",        ""));
        List<String> mss2 =  new ArrayList<>(Arrays.asList("ESTE",   "",  "UN",        "",        "", "",   "",    "", ""));
        List<String> mss3 =  new ArrayList<>(Arrays.asList(    "",   "",  "UN", "MENSAJE", "SECRETO", "",   "",    "", ""));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE ES UN MENSAJE SECRETO", pos.getMessage());
    }

    @Test
    public void GIVEN_DesfaseAlFinal_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList(    "",   "ES",    "",        "",        ""));
        List<String> mss2 =  new ArrayList<>(Arrays.asList("ESTE",     "",  "UN",        "",        "", ""));
        List<String> mss3 =  new ArrayList<>(Arrays.asList(    "",     "",  "UN", "MENSAJE", "SECRETO", ""));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE ES UN MENSAJE SECRETO", pos.getMessage());
    }

    @Test
    public void GIVEN_DesfaseEnElMedioContresVacios_WHEN_JuntandoElMensaje_THEN_SeEliminaDesfase() {
        List<List<String>> mss = new ArrayList<>();
        List<String> mss1 =  new ArrayList<>(Arrays.asList(    "", "ES", "",   "",        ""));
        List<String> mss2 =  new ArrayList<>(Arrays.asList("ESTE",   "", "", "UN",        "",       ""));
        List<String> mss3 =  new ArrayList<>(Arrays.asList(    "",   "", "", "UN", "MENSAJE", "SECRETO"));
        mss.add(mss1);
        mss.add(mss2);
        mss.add(mss3);

        MessageDTO pos = msnServ.getMessage(mss);

        assertEquals("ESTE ES UN MENSAJE SECRETO", pos.getMessage());
    }


}
