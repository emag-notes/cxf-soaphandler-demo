package cxf.soaphandler;

import java.io.IOException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class Client {

    public SOAPMessage invoke(String url) throws SOAPException, IOException {
        SOAPConnection connection = SOAPConnectionFactory.newInstance().createConnection();

        SOAPMessage response = connection.call(createRequest(), url);

        connection.close();

        return response;
    }

    private static SOAPMessage createRequest() throws SOAPException, IOException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();
        SOAPPart part = message.getSOAPPart();

        SOAPEnvelope envelope = part.getEnvelope();
        SOAPBody body = envelope.getBody();
        body.addChildElement("download", null, "http://soaphandler.cxf/");

        MimeHeaders headers = message.getMimeHeaders();
        headers.addHeader("Content-Type", "text/xml");

        message.saveChanges();

        message.writeTo(System.out);

        return message;
    }

}
