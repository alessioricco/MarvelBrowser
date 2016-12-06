
package it.alessioricco.marvelbrowser.models.comics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Creators implements Serializable
{

    /**
     * 
     */
    @SerializedName("available")
    @Expose
    private Integer available;
    /**
     * 
     */
    @SerializedName("collectionURI")
    @Expose
    private String collectionURI;
    /**
     * 
     */
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
    /**
     * 
     */
    @SerializedName("returned")
    @Expose
    private Integer returned;
    private final static long serialVersionUID = 5621260767729398947L;

    /**
     * 
     * @return
     *     The available
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 
     * @param available
     *     The available
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * 
     * @return
     *     The collectionURI
     */
    public String getCollectionURI() {
        return collectionURI;
    }

    /**
     * 
     * @param collectionURI
     *     The collectionURI
     */
    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * 
     * @return
     *     The returned
     */
    public Integer getReturned() {
        return returned;
    }

    /**
     * 
     * @param returned
     *     The returned
     */
    public void setReturned(Integer returned) {
        this.returned = returned;
    }


}
