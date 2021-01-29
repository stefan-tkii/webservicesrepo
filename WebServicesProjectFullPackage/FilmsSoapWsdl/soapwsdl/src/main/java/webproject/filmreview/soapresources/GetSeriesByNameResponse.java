
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetSeriesByNameResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetSeriesByNameResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SeriesPojo" type="{http://SoapResources.filmreview.webproject/}seriesPojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetSeriesByNameResponse", propOrder = {
    "seriesPojo"
})
public class GetSeriesByNameResponse {

    @XmlElement(name = "SeriesPojo")
    protected SeriesPojo seriesPojo;

    /**
     * Gets the value of the seriesPojo property.
     * 
     * @return
     *     possible object is
     *     {@link SeriesPojo }
     *     
     */
    public SeriesPojo getSeriesPojo() {
        return seriesPojo;
    }

    /**
     * Sets the value of the seriesPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeriesPojo }
     *     
     */
    public void setSeriesPojo(SeriesPojo value) {
        this.seriesPojo = value;
    }

}
