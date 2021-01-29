
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAllMoviesFilteredResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetAllMoviesFilteredResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MoviesListPojo" type="{http://SoapResources.filmreview.webproject/}moviesListPojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllMoviesFilteredResponse", propOrder = {
    "moviesListPojo"
})
public class GetAllMoviesFilteredResponse {

    @XmlElement(name = "MoviesListPojo")
    protected MoviesListPojo moviesListPojo;

    /**
     * Gets the value of the moviesListPojo property.
     * 
     * @return
     *     possible object is
     *     {@link MoviesListPojo }
     *     
     */
    public MoviesListPojo getMoviesListPojo() {
        return moviesListPojo;
    }

    /**
     * Sets the value of the moviesListPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link MoviesListPojo }
     *     
     */
    public void setMoviesListPojo(MoviesListPojo value) {
        this.moviesListPojo = value;
    }

}
