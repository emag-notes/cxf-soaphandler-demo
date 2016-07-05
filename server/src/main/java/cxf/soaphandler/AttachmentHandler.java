package cxf.soaphandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Set;

import javax.activation.DataHandler;
import javax.xml.namespace.QName;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class AttachmentHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        if (isOutbound(context)) {
            try {
                download(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {}

    private boolean isOutbound(SOAPMessageContext context) {
        return (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
    }

    private void download(SOAPMessageContext context) throws SOAPException, URISyntaxException, MalformedURLException {
        DataHandler dataHandler = new DataHandler(getClass().getResource("/saaj.txt").toURI().toURL());

        SOAPMessage message = context.getMessage();
        AttachmentPart attachmentPart = message.createAttachmentPart(dataHandler);
        attachmentPart.setContentId("SAAJ");
        message.addAttachmentPart(attachmentPart);

        /*
         * ------=_Part_0_573803785.1467713645565
         * Content-Type: text/xml; charset=utf-8
         *
         * <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><SOAP-ENV:Header xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"/><soap:Body><ns2:downloadResponse xmlns:ns2="http://soaphandler.cxf/"><return>Download</return></ns2:downloadResponse></soap:Body></soap:Envelope>
         * ------=_Part_0_573803785.1467713645565
         * Content-Type: text/plain
         * Content-ID: SAAJ
         *
         * SAAJ
         * ------=_Part_0_573803785.1467713645565--
         *
         */
//        try {
//            message.writeTo(System.out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
