
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAllSeriesFilteredResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetAllSeriesFilteredResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SeriesListPojo" type="{http://SoapResources.filmreview.webproject/}seriesListPojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllSeriesFilteredResponse", propOrder = {
    "seriesListPojo"
})
public class GetAllSeriesFilteredResponse {

    @XmlElement(name = "SeriesListPojo")
    protected SeriesListPojo seriesListPojo;

    /**
     * Gets the value of the seriesListPojo property.
     * 
     * @return
     *     possible object is
     *     {@link SeriesListPojo }
     *     
     */
    public SeriesListPojo getSeriesListPojo() {
        return seriesListPojo;
    }

    /**
     * Sets the value of the seriesListPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeriesListPojo }
     *     
     */
    public void setSeriesListPojo(SeriesListPojo value) {
        this.seriesListPojo = value;
    }

}
