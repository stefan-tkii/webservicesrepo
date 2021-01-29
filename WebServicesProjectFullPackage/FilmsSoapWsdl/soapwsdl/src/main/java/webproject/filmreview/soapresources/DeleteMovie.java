
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeleteMovie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeleteMovie">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MovieID" type="{http://SoapResources.filmreview.webproject/}movieID" minOccurs="0"/>
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
@XmlType(name = "DeleteMovie", propOrder = {
    "movieID",
    "tokenPojo"
})
public class DeleteMovie {

    @XmlElement(name = "MovieID")
    protected MovieID movieID;
    @XmlElement(name = "TokenPojo")
    protected TokenPojo tokenPojo;

    /**
     * Gets the value of the movieID property.
     * 
     * @return
     *     possible object is
     *     {@link MovieID }
     *     
     */
    public MovieID getMovieID() {
        return movieID;
    }

    /**
     * Sets the value of the movieID property.
     * 
     * @param value
     *     allowed object is
     *     {@link MovieID }
     *     
     */
    public void setMovieID(MovieID value) {
        this.movieID = value;
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
