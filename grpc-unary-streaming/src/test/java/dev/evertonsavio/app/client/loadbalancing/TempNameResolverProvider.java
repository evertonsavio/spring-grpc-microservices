package dev.evertonsavio.app.client.loadbalancing;

import io.grpc.NameResolver;

import java.net.URI;

public class TempNameResolverProvider extends io.grpc.NameResolverProvider {

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 5;
    }

    @Override
    public String getDefaultScheme() {
        return "dns";
    }

    @Override
    public NameResolver newNameResolver(URI targetUri, NameResolver.Args args) {
        System.out.println("Loking for service: " + targetUri.toString());
        return new TempNameResolverRegistry(targetUri.toString());
    }
}
