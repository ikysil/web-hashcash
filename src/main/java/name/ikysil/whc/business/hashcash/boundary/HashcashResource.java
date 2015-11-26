/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package name.ikysil.whc.business.hashcash.boundary;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;
import name.ikysil.whc.business.hashcash.entity.Hashcash;

/**
 *
 * @author Illya Kysil <ikysil@ikysil.name>
 */
@Path("hashcash")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class HashcashResource {
    
    public static final int DEFAULT_BITS = 20;

    @GET
    @Path("{resource}")
    public Hashcash hashcash(@PathParam("resource") String resource) {
        return hashcash(resource, DEFAULT_BITS);
    }
    
    @GET
    @Path("{resource}/{bits}")
    public Hashcash hashcash(@PathParam("resource") String resource, @PathParam("bits") int bits) {
        Hashcash result = new Hashcash();
        result.setResource(resource);
        result.setBits(bits);
        try {
            byte[] randBytes = new byte[16];
            SecureRandom.getInstance("SHA1PRNG").nextBytes(randBytes);
            result.setRand(DatatypeConverter.printBase64Binary(randBytes));
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            BigInteger counter = BigInteger.ZERO;
            counter = counter.setBit(bits + 1);
            do {
                result.setCounter(DatatypeConverter.printBase64Binary(counter.toByteArray()));
                byte[] hashcashCandidate = result.toString().getBytes(Charset.forName("UTF-8"));
                byte[] candidateDigest = md.digest(hashcashCandidate);
                BigInteger bi = new BigInteger(candidateDigest);
                if (bi.shiftRight(md.getDigestLength() * 8 - bits).compareTo(BigInteger.ZERO) == 0) {
                    Logger.getLogger(Hashcash.class.getName()).log(Level.INFO, DatatypeConverter.printHexBinary(candidateDigest));
                    break;
                }
                counter = counter.subtract(BigInteger.ONE);
                md.reset();
            }
            while (counter.compareTo(BigInteger.ZERO) >= 0);
        }
        catch (Exception ex) {
            Logger.getLogger(Hashcash.class.getName()).log(Level.SEVERE, null, ex);
        }
        result.setHashcash(result.toString());
        return result;
    }
    
}
