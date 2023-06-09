package shouty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class NetworkTest {
    private int range = 100;
    private Network network = new Network(range);
    private String message = "Free bagels!";

    @Test
    void broadcasts_a_message_to_a_listener_within_range() {
        Person sean = new Person("Sean", network, 0);
        Person lucy = mock(Person.class);
        network.subscribe(lucy);
        network.broadcast(message, sean);

        verify(lucy).hear(message);
    }

    @Test
    void does_not_broadcast_a_message_to_a_listener_out_of_range() {
        Person sean = new Person("Sean:", network, 0);
        Person laura = mock(Person.class);
        when(laura.getLocation()).thenReturn(101);
        network.subscribe(laura);
        network.broadcast(message, sean);

        verify(laura, never()).hear(message);
    }

    @Test
    void does_not_broadcast_a_message_to_a_listener_out_of_range_negative_distance() {
        Person sally = new Person("Sally", network, 101);
        Person lionel = mock(Person.class);
        when(lionel.getLocation()).thenReturn(0);
        network.subscribe(lionel);
        network.broadcast(message, sally);

        verify(lionel, never()).hear(message);
    }

    @Test
    void deducts_2_credits_for_a_shout_over_180_characters() {        
        char[] chars = new char[181];
        Arrays.fill(chars, 'x');
        String longMessage = String.valueOf(chars);
        
        Person sean = new Person("Sean", network, 0);
        Person laura = mock(Person.class);

        network.subscribe(laura);
        network.broadcast(longMessage, sean);

        verify(laura, never()).hear(longMessage);
    }

    @Test
    void deducts_5_credits_for_mentioning_the_word_buy(){
        String message = "Come buy these awesome croissants";

        Person sean = new Person("Sean", network, 0);
        sean.setCredits(100);
        Person laura = new Person("Laura", network, 0);

        network.subscribe(laura);
        network.broadcast(message, sean);

        assertEquals(95, sean.getCredits());
    }

    @Test
    void deducts_5_credits_for_mentioning_the_word_buy_several_times(){
        String message = "Come buy buy buy these awesome croissants";

        Person sean = new Person("Sean", network, 0);
        sean.setCredits(100);
        Person laura = new Person("Laura", network, 0);

        network.subscribe(laura);
        network.broadcast(message, sean);

        assertEquals(95, sean.getCredits());
    }
}