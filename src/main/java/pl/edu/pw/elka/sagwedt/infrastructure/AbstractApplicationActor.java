package pl.edu.pw.elka.sagwedt.infrastructure;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import pl.edu.pw.elka.sagwedt.printer.PrintRequest;

/**
 * Abstract actor of this application.
 */
public abstract class AbstractApplicationActor extends AbstractActor
{
    private final ActorRef printer;

    protected AbstractApplicationActor(final ActorRef printer)
    {
        this.printer = printer;
    }

    protected void log(final String msg)
    {
        printer.tell(new PrintRequest("# " + getName(getSelf()) + " says: " + msg), getSelf());
    }

    protected static String getName(final ActorRef actorRef)
    {
        return actorRef.path().name();
    }
}
