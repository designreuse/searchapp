package org.niko;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0", "lucene.index=/luceneTest/index"})
public class ITTest {

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

    @BeforeClass
    public static void setUpTest() throws Exception {
        FileUtils.deleteDirectory(new File(System.getProperty("java.io.tmpdir") + "/luceneTest/index"));
    }

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        template = new TestRestTemplate();
    }

    @Test
    public void getMain() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertEquals(response.getStatusCode(), OK);
    }

    @Test
    public void getIndexForm() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "index", String.class);
        assertEquals(response.getStatusCode(), OK);
    }

    @Test
    public void getIndexer() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString() + "index", String.class);
        assertEquals(response.getStatusCode(), OK);
    }

    @Test
    public void search() throws Exception {
        ResponseEntity<String> response = template.postForEntity(base.toString() + "index?q=https://ru.wikipedia.org&depth=2", null, String.class);
        assertEquals(response.getStatusCode(), NO_CONTENT);
        Thread.sleep(30000);
        response = template.getForEntity(base.toString() + "search?q=Википедия", String.class);
        assertTrue(response.getBody().contains("<B>Википедия</B>"));
    }
}