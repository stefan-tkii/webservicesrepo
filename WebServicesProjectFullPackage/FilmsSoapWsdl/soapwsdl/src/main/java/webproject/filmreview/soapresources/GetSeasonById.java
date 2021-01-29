
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetSeasonById complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetSeasonById">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SeriesID" type="{http://SoapResources.filmreview.webproject/}seriesID" minOccurs="0"/>
 *         &lt;element name="SeasonID" type="{http://SoapResources.filmreview.webproject/}seasonID" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetSeasonById", propOrder = {
    "seriesID",
    "seasonID"
})
public class GetSeasonById {

    @XmlElement(name = "SeriesID")
    protected SeriesID seriesID;
    @XmlElement(name = "SeasonID")
    protected SeasonID seasonID;

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
     * Gets the value of the seasonID property.
     * 
     * @return
     *     possible object is
     *     {@link SeasonID }
     *     
     */
    public SeasonID getSeasonID() {
        return seasonID;
    }

    /**
     * Sets the value of the seasonID property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeasonID }
     *     
     */
    public void setSeasonID(SeasonID value) {
        this.seasonID = value;
    }

}
