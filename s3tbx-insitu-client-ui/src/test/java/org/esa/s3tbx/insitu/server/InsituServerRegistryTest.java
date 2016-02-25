package org.esa.s3tbx.insitu.server;

import org.esa.s3tbx.insitu.TestInsituServer;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;


/**
 * @author Marco Peters
 */
public class InsituServerRegistryTest {

    @Test
    public void testCreation() {
        final InsituServerRegistry registry = InsituServerRegistry.getInstance();
        final Set<InsituServerSpi> registeredServers = registry.getRegisteredServers();
        assertTrue(registeredServers.size() >= 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddingServerToReturnedList() {
        final InsituServerRegistry registry = InsituServerRegistry.getInstance();
        final Set<InsituServerSpi> registeredServers = registry.getRegisteredServers();
        registeredServers.add(new TestInsituServer.Spi());
    }

    @Test
    public void testAddingAndRemovingServer() {
        final InsituServerRegistry registry = InsituServerRegistry.getInstance();
        final TestInsituServer.Spi serverSpi = new TestInsituServer.Spi();
        final int serverCount = registry.getRegisteredServers().size();

        registry.addServer(serverSpi);
        assertEquals(serverCount + 1, registry.getRegisteredServers().size());
        registry.removeServer(serverSpi.getName());
        assertEquals(serverCount, registry.getRegisteredServers().size());

        registry.addServer(serverSpi);
        assertEquals(serverCount + 1, registry.getRegisteredServers().size());
        registry.removeServer(serverSpi);
        assertEquals(serverCount, registry.getRegisteredServers().size());
    }
}