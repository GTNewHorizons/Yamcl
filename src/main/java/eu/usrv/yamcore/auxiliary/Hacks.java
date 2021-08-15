package eu.usrv.yamcore.auxiliary;


public class Hacks {
    /**
     * Can upcast client-side only stuff to common classes without throwing {@link ClassNotFoundException}
     */
    public static <R, T extends R> R safeCast(T obj) {
        return obj;
    }
}