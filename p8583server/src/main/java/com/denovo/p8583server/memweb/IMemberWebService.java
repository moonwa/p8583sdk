
package com.denovo.p8583server.memweb;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IMemberWebService", targetNamespace = "http://service.ws.crm.ce.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IMemberWebService {


    /**
     * 
     * @param encryptParams
     * @param action
     * @param params
     * @param version
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "execute", targetNamespace = "http://service.ws.crm.ce.com/", className = "com.denovo.p8583server.memweb.Execute")
    @ResponseWrapper(localName = "executeResponse", targetNamespace = "http://service.ws.crm.ce.com/", className = "com.denovo.p8583server.memweb.ExecuteResponse")
    public String execute(
        @WebParam(name = "action", targetNamespace = "")
        String action,
        @WebParam(name = "version", targetNamespace = "")
        String version,
        @WebParam(name = "params", targetNamespace = "")
        String params,
        @WebParam(name = "encryptParams", targetNamespace = "")
        String encryptParams);

}
