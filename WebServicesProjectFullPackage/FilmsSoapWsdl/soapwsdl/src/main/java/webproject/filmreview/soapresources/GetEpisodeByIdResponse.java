
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetEpisodeByIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetEpisodeByIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EpisodePojo" type="{http://SoapResources.filmreview.webproject/}episodePojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetEpisodeByIdResponse", propOrder = {
    "episodePojo"
})
public class GetEpisodeByIdResponse {

    @XmlElement(name = "EpisodePojo")
    protected EpisodePojo episodePojo;

    /**
     * Gets the value of the episodePojo property.
     * 
     * @return
     *     possible object is
     *     {@link EpisodePojo }
     *     
     */
    public EpisodePojo getEpisodePojo() {
        return episodePojo;
    }

    /**
     * Sets the value of the episodePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EpisodePojo }
     *     
     */
    public void setEpisodePojo(EpisodePojo value) {
        this.episodePojo = value;
    }

}
