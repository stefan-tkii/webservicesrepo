
package webproject.filmreview.soapresources;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for moviesListPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="moviesListPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Movies" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Movie" type="{http://SoapResources.filmreview.webproject/}movie" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "moviesListPojo", propOrder = {
    "movies"
})
public class MoviesListPojo {

    @XmlElement(name = "Movies")
    protected MoviesListPojo.Movies movies;

    /**
     * Gets the value of the movies property.
     * 
     * @return
     *     possible object is
     *     {@link MoviesListPojo.Movies }
     *     
     */
    public MoviesListPojo.Movies getMovies() {
        return movies;
    }

    /**
     * Sets the value of the movies property.
     * 
     * @param value
     *     allowed object is
     *     {@link MoviesListPojo.Movies }
     *     
     */
    public void setMovies(MoviesListPojo.Movies value) {
        this.movies = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Movie" type="{http://SoapResources.filmreview.webproject/}movie" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "movie"
    })
    public static class Movies {

        @XmlElement(name = "Movie")
        protected List<Movie> movie;

        /**
         * Gets the value of the movie property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the movie property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMovie().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Movie }
         * 
         * 
         */
        public List<Movie> getMovie() {
            if (movie == null) {
                movie = new ArrayList<Movie>();
            }
            return this.movie;
        }

    }

}
