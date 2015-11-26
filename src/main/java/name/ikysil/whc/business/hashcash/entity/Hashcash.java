/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.ikysil.whc.business.hashcash.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Illya Kysil <ikysil@ikysil.name>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Hashcash {

    public static final int DEFAULT_VERSION = 1;

    public Hashcash() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        date = sdf.format(new Date());
    }

    private int version = DEFAULT_VERSION;

    /**
     * Get the value of version
     *
     * @return the value of version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Set the value of version
     *
     * @param version new value of version
     */
    public void setVersion(int version) {
        this.version = version;
    }

    private int bits;

    /**
     * Get the value of bits
     *
     * @return the value of bits
     */
    public int getBits() {
        return bits;
    }

    /**
     * Set the value of bits
     *
     * @param bits new value of bits
     */
    public void setBits(int bits) {
        this.bits = bits;
    }

    private String date;

    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the value of date
     *
     * @param date new value of date
     */
    public void setDate(String date) {
        this.date = date;
    }

    private String resource;

    /**
     * Get the value of resource
     *
     * @return the value of resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * Set the value of resource
     *
     * @param resource new value of resource
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    private String rand;

    /**
     * Get the value of rand
     *
     * @return the value of rand
     */
    public String getRand() {
        return rand;
    }

    /**
     * Set the value of rand
     *
     * @param rand new value of rand
     */
    public void setRand(String rand) {
        this.rand = rand;
    }

    private String ext = "";

    /**
     * Get the value of ext
     *
     * @return the value of ext
     */
    public String getExt() {
        return ext;
    }

    /**
     * Set the value of ext
     *
     * @param ext new value of ext
     */
    public void setExt(String ext) {
        this.ext = ext;
    }

    private String counter;

    /**
     * Get the value of counter
     *
     * @return the value of counter
     */
    public String getCounter() {
        return counter;
    }

    /**
     * Set the value of counter
     *
     * @param counter new value of counter
     */
    public void setCounter(String counter) {
        this.counter = counter;
    }

    private String hashcash;

    /**
     * Get the value of hashcash
     *
     * @return the value of hashcash
     */
    public String getHashcash() {
        return hashcash;
    }

    /**
     * Set the value of hashcash
     *
     * @param hashcash new value of hashcash
     */
    public void setHashcash(String hashcash) {
        this.hashcash = hashcash;
    }

    @Override
    public String toString() {
        if ((hashcash == null) || (hashcash.isEmpty())) {
            return String.format("%d:%d:%s:%s:%s:%s:%s", version, bits, date, resource, ext, rand, counter);
        }
        else {
            return hashcash;
        }
    }

}
