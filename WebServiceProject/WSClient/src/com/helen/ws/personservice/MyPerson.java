
package com.helen.ws.personservice;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MyPerson", targetNamespace = "personservice.ws.helen.com")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MyPerson {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addPerson", targetNamespace = "personservice.ws.helen.com", className = "com.helen.ws.personservice.AddPerson")
    @ResponseWrapper(localName = "addPersonResponse", targetNamespace = "personservice.ws.helen.com", className = "com.helen.ws.personservice.AddPersonResponse")
    @Action(input = "personservice.ws.helen.com/MyPerson/addPersonRequest", output = "personservice.ws.helen.com/MyPerson/addPersonResponse")
    public void addPerson(
            @WebParam(name = "arg0", targetNamespace = "")
                    Person arg0);

    /**
     * 
     * @return
     *     returns java.util.List<com.helen.ws.personservice.Person>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getPersonAll", targetNamespace = "personservice.ws.helen.com", className = "com.helen.ws.personservice.GetPersonAll")
    @ResponseWrapper(localName = "getPersonAllResponse", targetNamespace = "personservice.ws.helen.com", className = "com.helen.ws.personservice.GetPersonAllResponse")
    @Action(input = "personservice.ws.helen.com/MyPerson/getPersonAllRequest", output = "personservice.ws.helen.com/MyPerson/getPersonAllResponse")
    public List<Person> getPersonAll();

}
