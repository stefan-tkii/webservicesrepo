
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetMovieByNameResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetMovieByNameResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MoviePojo" type="{http://SoapResources.filmreview.webproject/}moviePojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetMovieByNameResponse", propOrder = {
    "moviePojo"
})
public class GetMovieByNameResponse {

    @XmlElement(name = "MoviePojo")
    protected MoviePojo moviePojo;

    /**
     * Gets the value of the moviePojo property.
     * 
     * @return
     *     possible object is
     *     {@link MoviePojo }
     *     
     */
    public MoviePojo getMoviePojo() {
        return moviePojo;
    }

    /**
     * Sets the value of the moviePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MoviePojo }
     *     
     */
    public void setMoviePojo(MoviePojo value) {
        this.moviePojo = value;
    }

}
