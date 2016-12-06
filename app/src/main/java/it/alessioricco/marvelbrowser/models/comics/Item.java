
package it.alessioricco.marvelbrowser.models.comics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable
{

    /**
     * 
     */
    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    /**
     * 
     */
    @SerializedName("name")
    @Expose
    private String name;
    /**
     * 
     */
    @SerializedName("role")
    @Expose
    private String role;
    private final static long serialVersionUID = 5229349172497121398L;
    /**
     *
     */
    @SerializedName("type")
    @Expose
    private String type;
    /**
     * 
     * @return
     *     The resourceURI
     */
    public String getResourceURI() {
        return resourceURI;
    }

    /**
     * 
     * @param resourceURI
     *     The resourceURI
     */
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The role
     */
    public String getRole() {
        return role;
    }

    /**
     * 
     * @param role
     *     The role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }
}
