package com.baseline;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
public class SocketManagerTest {
    @Test
    public void testTransmission() throws InterruptedException {
        SocketManager manager = new SocketManager();
        int port = 6001;
        String msg = "Test-CI-CD";
        Thread server = new Thread(() -> {
            try { assertEquals(msg, manager.receiveMessage(port)); } 
            catch (IOException e) { fail(e.getMessage()); }
        });
        server.start();
        Thread.sleep(1000);
        try { manager.sendMessage("localhost", port, msg); } 
        catch (IOException e) { fail(e.getMessage()); }
        server.join();
    }
}
