
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetSeasonByIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetSeasonByIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SeasonPojo" type="{http://SoapResources.filmreview.webproject/}seasonPojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetSeasonByIdResponse", propOrder = {
    "seasonPojo"
})
public class GetSeasonByIdResponse {

    @XmlElement(name = "SeasonPojo")
    protected SeasonPojo seasonPojo;

    /**
     * Gets the value of the seasonPojo property.
     * 
     * @return
     *     possible object is
     *     {@link SeasonPojo }
     *     
     */
    public SeasonPojo getSeasonPojo() {
        return seasonPojo;
    }

    /**
     * Sets the value of the seasonPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeasonPojo }
     *     
     */
    public void setSeasonPojo(SeasonPojo value) {
        this.seasonPojo = value;
    }

}
