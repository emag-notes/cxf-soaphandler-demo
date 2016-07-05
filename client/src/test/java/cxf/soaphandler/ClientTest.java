package cxf.soaphandler;

import java.util.Iterator;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.SOAPMessage;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void test() throws Exception {
        Client client = new Client();
        SOAPMessage response = client.invoke("http://localhost:8080/cxf-soaphandler-demo-server/services/downloader");

        String content = null;
        Iterator it = response.getAttachments();
        while (it.hasNext()) {
            AttachmentPart part = (AttachmentPart) it.next();
            content = part.getContent().toString();
        }

        assertThat(content, is("SAAJ"));
    }

}