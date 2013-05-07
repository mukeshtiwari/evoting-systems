package edu.uconn.cse.adder;

/**
 * Thrown by the <tt>fromString</tt> method of a <code>PublicKey</code>
 * to indicate that there was a parse error.
 *
 * @author  David Walluck
 * @version $LastChangedRevision$ $LastChangedDate$
 * @see     PublicKey
 * @see     PublicKey#fromString(String)
 * @since   0.0.1
 */
public class InvalidPublicKeyException extends RuntimeException {
    private static final long serialVersionUID = 0L;

    /**
     * Constructs an <code>InvalidPublicKeyException</code> with
     * <tt>null</tt> as its error message string.
     */
    public InvalidPublicKeyException() {
        super();
    }

    /**
     * Constructs a <code>InvalidPublicKeyException</code>, saving a reference
     * to the error message string <tt>s</tt> for later retrieval by the
     * <tt>getMessage</tt> method.
     *
     * @param s the detail message
     */
    public InvalidPublicKeyException(String s) {
        super(s);
    }
}
