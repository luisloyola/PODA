/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.diinf.sessionBeans;

import cl.diinf.objetoAprendizaje.ObjetoAprendizaje;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author teban
 */
public class OA_TranslateHtmlTest {
    
    public OA_TranslateHtmlTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of writeHtml method, of class OA_TranslateHtml.
     */
    @Test
    public void testWriteHtml() throws Exception {
        /*
          Debido a el cómo trabajan todas estas funciones en conjunto,
          el resultado correcto sólo será obtenido si todas funcionan
          como deben.
        */
        /*
        OA_Reader test = new OA_Reader();
        test.setFileContent("<?xml version=\"1.0\"?>\n" +
"\n" +
"<begin>\n" +
"	<object title=\"PRUEBA\" author=\"\">\n" +
"		<scene title=\"PRUEBA_S1\" time=\"default\">\n" +
"			<text type=\"text\" font=\"default\">PRUEBA_TEXT</text>	\n" +
"			<voice>PRUEBA_VOZ</voice>	\n" +
"		</scene>\n" +
"	</object>\n" +
"</begin>");
        OA_TranslateHtml trans = new OA_TranslateHtml();
        String result = trans.writeHtml(test.readOA().get(0));
        String expected = "<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"  <meta charset=\"utf-8\">\n" +
"  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
"  <meta name=\"viewport\" content=\"width=1024, user-scalable=no\">\n" +
"\n" +
"  <title>PRUEBA</title>  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/core/deck.core.css\">\n" +
"\n" +
"  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/goto/deck.goto.css\">\n" +
"  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/menu/deck.menu.css\">\n" +
"  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/navigation/deck.navigation.css\">\n" +
"  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/status/deck.status.css\">\n" +
"  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/extensions/scale/deck.scale.css\">\n" +
"\n" +
"  <script src=\"resources/voice/AJAX_GOOGLE.js\"></script>\n" +
"  <script src=\"resources/voice/voice.js\"></script>\n" +
"\n" +
"  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/style/swiss.css\">\n" +
"\n" +
"  <link rel=\"stylesheet\" media=\"screen\" href=\"resources/themes/transition/horizontal-slide.css\">\n" +
"\n" +
"  <link rel=\"stylesheet\" media=\"print\" href=\"resources/core/print.css\">\n" +
"\n" +
"  <script src=\"resources/modernizr.custom.js\"></script>\n" +
"</head>\n" +
"\n" +
"<body><div class=\"deck-container\" >\n" +
"\n" +
"<section class=\"slide\" id=\"slide-1\">\n" +
"<h2></h2>\n" +
"\n" +
"<p>PRUEBA_TEXT</p>\n" +
"\n" +
"</section>\n" +
"\n" +
"    <!-- deck.navigation snippet -->\n" +
"    <div aria-role=\"navigation\">\n" +
"      <a href=\"#\" class=\"deck-prev-link\" title=\"Previous\">&#8592;</a>\n" +
"      <a href=\"#\" class=\"deck-next-link\" title=\"Next\">&#8594;</a>\n" +
"    </div>\n" +
"\n" +
"    <!-- deck.status snippet -->\n" +
"    <p class=\"deck-status\" aria-role=\"status\">\n" +
"      <span class=\"deck-status-current\"></span>\n" +
"/\n" +
"      <span class=\"deck-status-total\"></span>\n" +
"    </p>\n" +
"\n" +
"    <!-- deck.goto snippet -->\n" +
"    <form action=\".\" method=\"get\" class=\"goto-form\">\n" +
"      <label for=\"goto-slide\">Go to slide:</label>\n" +
"      <input type=\"text\" name=\"slidenum\" id=\"goto-slide\" list=\"goto-datalist\">\n" +
"      <datalist id=\"goto-datalist\"></datalist>\n" +
"      <input type=\"submit\" value=\"Go\">\n" +
"    </form>\n" +
"\n" +
"    <!-- End extension snippets. -->\n" +
"  </div>\n" +
"\n" +
"<!-- Archivos JS necesarios -->\n" +
"<script src=\"resources/jquery.min.js\"></script>\n" +
"<script src=\"resources/core/deck.core.js\"></script>\n" +
"\n" +
"<!-- Archivos JS de extensiones deckjs -->\n" +
"<script src=\"resources/extensions/menu/deck.menu.js\"></script>\n" +
"<script src=\"resources/extensions/goto/deck.goto.js\"></script>\n" +
"<script src=\"resources/extensions/status/deck.status.js\"></script>\n" +
"<script src=\"resources/extensions/navigation/deck.navigation.js\"></script>\n" +
"<script src=\"resources/extensions/scale/deck.scale.js\"></script>\n" +
"<script src=\"resources/extensions/deck.events/deck.events.js\"></script>\n" +
"\n" +
"<script>\n" +
"  $(function() {\n" +
"    $.deck('.slide');\n" +
"  });\n" +
"</script>\n" +
"\n" +
"<script>\n" +
"  document.addEventListener(\"TTS_LOADED\", function(event) {\n" +
"    $(\"#slide-1\").trigger('deck.becameCurrent');\n" +
"  });\n" +
"</script><script>\n" +
"  $(\"#slide-1\").bind('deck.becameCurrent', function(ev, direction) {\n" +
"    var texto_a_reproducir = 'PRUEBA_VOZ';\n" +
"    responsiveVoice.cancel();\n" +
"    if(texto_a_reproducir != ''){\n" +
"      responsiveVoice.speak(texto_a_reproducir,'Spanish Female');\n" +
"    }\n" +
"  });\n" +
"</script>\n" +
"\n" +
"</body>\n" +
"</html>";
        assertEquals(result, expected);

    */}
}
