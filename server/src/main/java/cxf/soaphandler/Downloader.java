package cxf.soaphandler;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "Downloader")
@HandlerChain(file = "/handlers.xml")
public class Downloader {

    @WebMethod
    public String download() {
        return "Download";
    }

}
