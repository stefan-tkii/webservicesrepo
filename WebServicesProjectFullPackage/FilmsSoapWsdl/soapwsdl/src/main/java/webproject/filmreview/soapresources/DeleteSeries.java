
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeleteSeries complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeleteSeries">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SeriesID" type="{http://SoapResources.filmreview.webproject/}seriesID" minOccurs="0"/>
 *         &lt;element name="TokenPojo" type="{http://SoapResources.filmreview.webproject/}tokenPojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteSeries", propOrder = {
    "seriesID",
    "tokenPojo"
})
public class DeleteSeries {

    @XmlElement(name = "SeriesID")
    protected SeriesID seriesID;
    @XmlElement(name = "TokenPojo")
    protected TokenPojo tokenPojo;

    /**
     * Gets the value of the seriesID property.
     * 
     * @return
     *     possible object is
     *     {@link SeriesID }
     *     
     */
    public SeriesID getSeriesID() {
        return seriesID;
    }

    /**
     * Sets the value of the seriesID property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeriesID }
     *     
     */
    public void setSeriesID(SeriesID value) {
        this.seriesID = value;
    }

    /**
     * Gets the value of the tokenPojo property.
     * 
     * @return
     *     possible object is
     *     {@link TokenPojo }
     *     
     */
    public TokenPojo getTokenPojo() {
        return tokenPojo;
    }

    /**
     * Sets the value of the tokenPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TokenPojo }
     *     
     */
    public void setTokenPojo(TokenPojo value) {
        this.tokenPojo = value;
    }

}
