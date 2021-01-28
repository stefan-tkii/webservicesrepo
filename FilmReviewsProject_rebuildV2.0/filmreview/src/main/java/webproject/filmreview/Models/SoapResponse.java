package webproject.filmreview.Models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SoapResponse implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String details;

    public SoapResponse() {
    }

    public SoapResponse(String details) {
        this.details = details;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}