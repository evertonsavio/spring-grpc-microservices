package dev.evertonsavio.app.client.loadbalancing;

import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;

import java.util.List;

public class TempNameResolverRegistry extends NameResolver {

    private final String service;

    public TempNameResolverRegistry(String service) {
        this.service = service;
    }

    @Override
    public String getServiceAuthority() {
        return "temp";
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void refresh() {
        super.refresh();
    }

    @Override
    public void start(Listener2 listener) {
        List<EquivalentAddressGroup> addressGroup = ServiceRegistry.getInstances(this.service);
        ResolutionResult resolutionResult = ResolutionResult.newBuilder().setAddresses(addressGroup).build();
        listener.onResult(resolutionResult);
    }
}
